package src;
import java.util.HashMap;
import java.util.Map;

import src.Exceptions.ImpossibleToRun;
import src.Exceptions.SomethingGotWrong;

public class Emulator {
    private byte[][] P0 = new byte[2][1];
    private byte[][] P1 = new byte[2][1];
    private byte[][] P2 = new byte[2][1];
    private byte[][] P3 = new byte[2][1];
    private byte[][] P4 = new byte[2][1];
    private byte[][] P5 = new byte[2][1];
    private byte[][] P6 = new byte[2][1];
    private byte[][] P7 = new byte[2][1];

    private Block[] Stack = new Block[4];
    private int RomPointer = 0;
    private byte Accumulator = 0;
    private boolean carry = false;
    private Map<String, Runnable> MnemonicsTable;
    private Map<Integer, byte[][]> registerMap;
    private DataRom ROM;
    private Conversor conversor;

    private boolean running;

    Emulator(DataRom ROM) {
        this.ROM = ROM;
        registerMap = new HashMap<>();
        MnemonicsTable = new HashMap<>();
        this.conversor = new Conversor();
        registerMap.put(0, this.P0);
        registerMap.put(1, this.P1);
        registerMap.put(2, this.P2);
        registerMap.put(3, this.P3);
        registerMap.put(4, this.P4);
        registerMap.put(5, this.P5);
        registerMap.put(6, this.P6);
        registerMap.put(7, this.P7);

        //
        MnemonicsTable.put("NOP", () -> this.NOP());
        MnemonicsTable.put("JCN", () -> this.JCN());
        MnemonicsTable.put("FIM", () -> {
            try {
                this.FIM();
            } catch (SomethingGotWrong e) {
                e.printStackTrace();
            }
        });
        MnemonicsTable.put("FIN", () -> this.FIN());
        MnemonicsTable.put("JIN", () -> this.JIN());
        MnemonicsTable.put("JUN", () -> this.JUN());
        MnemonicsTable.put("JMS", () -> this.JMS());
        MnemonicsTable.put("INC", () -> {
            try {
                this.INC();
            } catch (SomethingGotWrong e3) {
                e3.printStackTrace();
            }
        });
        MnemonicsTable.put("ISZ", () -> {
            try {
                this.ISZ();
            } catch (SomethingGotWrong e2) {
                e2.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MnemonicsTable.put("ADD", () -> this.ADD());
        MnemonicsTable.put("SUB", () -> this.SUB());
        MnemonicsTable.put("XCH", () -> this.XCH());
        MnemonicsTable.put("BBL", () -> {
            try {
                this.BBL();
            } catch (SomethingGotWrong e1) {
                e1.printStackTrace();
            }
        });
        MnemonicsTable.put("LDM", () -> this.LDM());
        MnemonicsTable.put("CLB", () -> this.CLB());
        MnemonicsTable.put("CLC", () -> this.CLC());
        MnemonicsTable.put("IAC", () -> {
            try {
                this.IAC();
            } catch (SomethingGotWrong e1) {
                e1.printStackTrace();
            }
        });
        MnemonicsTable.put("CMC", () -> this.CMC());
        MnemonicsTable.put("CMA", () -> this.CMA());
        MnemonicsTable.put("RAL", () -> this.RAL());
        MnemonicsTable.put("RAR", () -> this.RAR());
        MnemonicsTable.put("TCC", () -> this.TCC());
        MnemonicsTable.put("DAC", () -> this.DAC());
        MnemonicsTable.put("TCS", () -> {
            try {
                this.TCS();
            } catch (SomethingGotWrong e1) {
                e1.printStackTrace();
            }
        });
        MnemonicsTable.put("STC", () -> this.STC());
        MnemonicsTable.put("DAA", () -> this.DAA());
        MnemonicsTable.put("KBP", () -> this.KBP());
        MnemonicsTable.put("DCL", () -> this.DCL());
        MnemonicsTable.put("LD", () -> {
            try {
                this.LD();
            } catch (SomethingGotWrong e) {
                e.printStackTrace();
            }
        });
        this.RegisterSetup();
        this.running = true;
    }

    public void RegisterSetup() {
        this.Stack[0] = this.getBlockFromRom(this.RomPointer);
        this.RomPointer++;
    }

    public void RegisterUpdate() throws SomethingGotWrong {
        try {
            this.Stack[0] = this.getBlockFromRom(this.RomPointer);
            this.RomPointer++;
        } catch (Exception e) {
            throw new SomethingGotWrong("Something got wrong during register update");
        }
    }

    public String TransformOperation(Block InstructionToConvert) throws Exception {
        return this.conversor.ConvertOperation(InstructionToConvert);
    }

    private byte FourBitMask(byte num){
        return (byte) (num&0b00001111);
    }

    public void ExecuteOperation() throws Exception {
        while (running) {
            Block ActualInstruction = this.Stack[0];
            String Mnemonic = this.TransformOperation(ActualInstruction);
            Runnable RunMethod = this.MnemonicsTable.get(Mnemonic);
            if (RunMethod != null) {
                RunMethod.run();
                this.RegisterUpdate();

            } else {
                throw new ImpossibleToRun("Cannot RUN the block");
            }
        }
        System.out.println("         FINAL RESULT             ");
        System.out.println("------------------------------- \n");
        System.out.println("ROM BLOCKS: "+this.ROM.listHex() +"\n");
        System.out.println("Accumulator: " + this.Accumulator);
        System.out.println("Carry: " + this.carry);
        System.out.println("PC: "+ this.RomPointer);

        System.out.println("R0R1: "+this.registerMap.get(0)[0][0]+" "+this.registerMap.get(0)[1][0]);
        System.out.println("R2R3: "+this.registerMap.get(1)[0][0]+" "+this.registerMap.get(1)[1][0]);
        System.out.println("R4R5: "+this.registerMap.get(2)[0][0]+" "+this.registerMap.get(2)[1][0]);
        System.out.println("R6R7: "+this.registerMap.get(3)[0][0]+" "+this.registerMap.get(3)[1][0]);
        System.out.println("R8R9: "+this.registerMap.get(4)[0][0]+" "+this.registerMap.get(4)[1][0]);
        System.out.println("RARB: "+this.registerMap.get(5)[0][0]+" "+this.registerMap.get(5)[1][0]);
        System.out.println("RCRD: "+this.registerMap.get(6)[0][0]+" "+this.registerMap.get(6)[1][0]);
        System.out.println("RERF: "+this.registerMap.get(7)[0][0]+" "+this.registerMap.get(7)[1][0]+"\n");
        System.out.println("------------------------------- \n");

    }

    public void ExecuteOperation(Block instruction) throws Exception {
        String Mnemonic = this.TransformOperation(instruction);
        Runnable RunMethod = this.MnemonicsTable.get(Mnemonic);
        if (RunMethod != null) {
            RunMethod.run();
            this.RegisterUpdate();
        } else {
            throw new ImpossibleToRun("Cannot RUN the block");
        }
    }

    private Block getBlockFromRom(int pointer) {
        return this.ROM.ReturnBlock(pointer);
    }

    public void NOP() {
        this.running = false;
    }

    public void JCN() {
        System.out.println("JUMP to ROM address");
    }

    public void FIM() throws SomethingGotWrong {
        Block instruction = this.Stack[0];
        if (instruction.hasAssociatedValue()) {
            byte v1 = Byte.parseByte(instruction.getAssociatedValue().split("")[0], 16);
            byte v2 = Byte.parseByte(instruction.getAssociatedValue().split("")[1], 16);
            int registerId = (instruction.getLast4Bits()) / 2;
            byte[][] registerPair = this.registerMap.get(registerId);
            registerPair[0][0] = v1;
            registerPair[1][0] = v2;
        } else {
            throw new SomethingGotWrong("You are trying use: FIM. But without a value");
        }
    }

    public void FIN() {
        Block instruction = this.Stack[0];
        Block firstBlock = this.getBlockFromRom(0);
        byte FirstWord = firstBlock.getFirst4Bits();
        byte SecondWord = firstBlock.getLast4Bits();
        int key = instruction.getLast4Bits() / 2;
        byte[][] pair = this.registerMap.get(key);
        pair[0][0] = FirstWord;
        pair[1][0] = SecondWord;
    }

    public void JIN() {
        Block instruction = this.Stack[0];
        byte SecondWord = instruction.getLast4Bits();
        int key = SecondWord / 2;
        byte[][] pair = this.registerMap.get(key);
        int pointer = Integer.parseInt((String.valueOf(pair[0][0]) + String.valueOf(pair[1][0])), 16);
        this.Stack[0] = this.getBlockFromRom(pointer);
    }

    public void JUN() {
        Block instruction = this.Stack[0];
        int toPoint = Integer.parseInt(instruction.getLast4Bits() + instruction.getAssociatedValue(), 16);
        this.RomPointer = toPoint;
        this.Stack[0] = getBlockFromRom(this.RomPointer);
    }

    public void JMS() {
        Block instruction = this.Stack[0];
        int toPoint = Integer.parseInt(instruction.getLast4Bits() + instruction.getAssociatedValue(), 16);
        this.Stack[3] = this.Stack[2];
        this.Stack[2] = this.Stack[1];
        this.Stack[1] = this.Stack[0];
        //I will fix this . . . someday, i think
        this.RomPointer = toPoint;
        this.Stack[0] = getBlockFromRom(this.RomPointer);
    }

    public void INC() throws SomethingGotWrong {
        System.out.println("Increment register of register");
        Block instruction = this.Stack[0];
        int registerId = instruction.getLast4Bits();
        byte x;
        if (registerId % 2 == 0) {
            x = this.registerMap.get(registerId / 2)[0][0];
            if(x<=15) this.registerMap.get(registerId / 2)[0][0] += 1;
            else throw new SomethingGotWrong("Overflow");
        } else {
            x = this.registerMap.get(registerId / 2)[1][0];
            if(x<15) this.registerMap.get(registerId / 2)[1][0] += 1;
            else throw new SomethingGotWrong("Overflow");
        }
    }

    public void ISZ() throws Exception {
        System.out.println("ISZ");
        Block instruction = this.Stack[0];
        if (instruction.hasAssociatedValue()) {
            Block repeatInstruction = getBlockFromRom(Integer.parseInt(instruction.getAssociatedValue(), 16));
            byte registerId = instruction.getLast4Bits();
            int key = registerId / 2;
            byte[][] pair = this.registerMap.get(key);
            byte value = registerId % 2 == 0 ? pair[0][0] : pair[1][0];
            while (value < 15) {
                value++;
                if (instruction.getFirst4Bits() != repeatInstruction.getFirst4Bits()) {
                    this.ExecuteOperation(repeatInstruction);
                } else {
                    throw new SomethingGotWrong("The program stops because you made a previsible infinite loop");
                }
            }
        } else {
            throw new SomethingGotWrong("You are trying use ISZ but without a associated value to loop");
        }

    }

    public void ADD() {
        System.out.println("ADD contents of register to accumulador with a carry");
        Block instruction = this.Stack[0];
        byte SecondWord = instruction.getLast4Bits();
        int key = SecondWord/2;
        byte[][] pair = this.registerMap.get(key);
        this.Accumulator= (byte) (SecondWord%2 == 0 ? this.Accumulator+pair[0][0] : this.Accumulator+pair[1][0]);
    }

    public void SUB() {
        Block instruction = this.Stack[0];
        int SecondWord = instruction.getLast4Bits();
        int key = SecondWord / 2;
        byte[][] pair = this.registerMap.get(key);
        int value = SecondWord%2 == 0 ? pair[0][0] : pair[1][0];
        this.Accumulator = (byte) ((this.Accumulator - value) > 0 ?  this.Accumulator - value : 0);
        //I have no idea to make the program know if operation has used borrow, so for default always the SUB will set carry
        this.carry = true;
    }

    public void LD() throws SomethingGotWrong {
        try {
            Block instruction = this.Stack[0];
            int registerId = instruction.getLast4Bits();
            byte[][] pair = this.registerMap.get(registerId/2);
            int value = registerId%2 == 0 ? pair[0][0] : pair[1][0];
            this.Accumulator+=value;
        } catch (Exception e) {
            throw e;
        }

    }

    public void XCH() {
        Block instruction = this.Stack[0];
        int registerId = instruction.getLast4Bits();
        if (registerId % 2 == 0) {
            this.registerMap.get(registerId / 2)[0][0] = this.Accumulator;
        } else {
            this.registerMap.get(registerId / 2)[1][0] = this.Accumulator;
        }
        this.Accumulator = 0;
    }

    public void BBL() throws SomethingGotWrong {
        try {
            Block instruction = this.Stack[0];
            for (int i = 0; i < 2; i++) {
                this.Stack[i] = this.Stack[i+1];
            }
            this.Stack[3] = null;
            byte valueToLoad = this.FourBitMask(instruction.getLast4Bits());
            this.Accumulator = valueToLoad;
        } catch (Exception e) {
            throw new SomethingGotWrong("");
        }

    }

    public void LDM() {
        Block instruction = this.Stack[0];
        byte valueToAdd = this.FourBitMask(instruction.getLast4Bits());
        this.Accumulator = valueToAdd;
    }

    public void CLB() {
        this.Accumulator = 0;
        this.carry = false;
    }

    public void CLC() {
        this.carry = false;
    }

    public void IAC() throws SomethingGotWrong {
        if (this.Accumulator < 15) {
            this.Accumulator++;
        } else {
            this.running = false;
        }
    }

    public void CMC() {
        this.carry = !this.carry;
    }

    public void CMA() {
        this.Accumulator = (byte) (~this.Accumulator&0b00001111);
    }

    public void RAL() {
        if ((this.Accumulator << 1) > 15) {
            byte n = (byte) (this.Accumulator<<1);
            n = this.FourBitMask(n);
            this.Accumulator = n;
            System.out.println("Overflow Accumulator");
        } else {
            this.Accumulator = (byte) (this.Accumulator << 1);
        }
    }

    public void RAR() {
        this.Accumulator = (byte) (this.Accumulator >> 1);
    }

    public void TCC() {
        this.Accumulator = (byte) (this.carry ? 1 : 0);
        this.carry = false;
    }

    public void DAC() {
        this.Accumulator = (byte) (this.Accumulator >= 0 ? this.Accumulator - 1 : 0);
    }

    public void TCS() throws SomethingGotWrong {
        this.Accumulator = (byte) (this.carry ? 10:9);
        this.carry = false;
    }

    public void STC() {
        this.carry = true;
    }

    public void DAA() {
        if(this.carry || this.Accumulator>9){
            byte newValue = this.FourBitMask((byte) (this.Accumulator+6));
            this.carry = newValue < this.Accumulator+6 ? true:false;
            this.Accumulator = newValue;
        }
    }

    public void KBP() {
        if(this.Accumulator >= 5 || this.Accumulator==3) this.Accumulator = 15;
    }

    public void DCL() {

    }
}

/*
 * TODO
 * 1 - DCL
 * 2 - KBP ???
 */