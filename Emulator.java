import java.util.HashMap;
import java.util.Map;

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
    private byte RomPointer = 0;
    private byte Accumulator = 0;
    private boolean carry = false;
    private Map<String, Runnable> MnemonicsTable;
    private Map<Integer, byte[][]> registerMap;
    private DataRom ROM;
    private Conversor conversor;

    Emulator(DataRom ROM) {
        this.ROM = ROM;
        registerMap =  new HashMap<>();
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
        MnemonicsTable.put("INC", () -> this.INC());
        MnemonicsTable.put("ISZ", () -> this.ISZ());
        MnemonicsTable.put("ADD", () -> this.ADD());
        MnemonicsTable.put("SUB", () -> this.SUB());
        MnemonicsTable.put("XCH", () -> this.XCH());
        MnemonicsTable.put("BBL", () -> this.BBL());
        MnemonicsTable.put("LDM", () -> this.LDM());
        MnemonicsTable.put("CLB", () -> this.CLB());
        MnemonicsTable.put("CLC", () -> this.CLC());
        MnemonicsTable.put("IAC", () -> this.IAC());
        MnemonicsTable.put("CMC", () -> this.CMC());
        MnemonicsTable.put("CMA", () -> this.CMA());
        MnemonicsTable.put("RAL", () -> this.RAL());
        MnemonicsTable.put("RAR", () -> this.RAR());
        MnemonicsTable.put("TCC", () -> this.TCC());
        MnemonicsTable.put("DAC", () -> this.DAC());
        MnemonicsTable.put("TCS", () -> this.TCS());
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
    }

    public void RegisterSetup() {
        for (int i = 0; i < this.Stack.length; i++) {
            this.Stack[i] = this.getBlockFromRom();
            this.RomPointer++;
        }
    }

    public void RegisterUpdate() throws SomethingGotWrong {
        try {
            for (int i = 0; i < 1; i++) {
                this.Stack[i] = this.Stack[i + 1];
            }
            this.Stack[3] = this.getBlockFromRom();
            this.RomPointer++;
        } catch (Exception e) {
            throw new SomethingGotWrong("Something got wrong during register update");
        }
    }

    public String TransformOperation(Block InstructionToConvert) throws Exception {
        return this.conversor.ConvertOperation(InstructionToConvert);
    }

    public void ExecuteOperation() throws Exception {
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

    public Block getBlockFromRom() {
        return this.ROM.ReturnBlock(this.RomPointer);
    }

    public void NOP() {
        System.out.println("No Operation - NOP");
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

    }

    public void JIN() {

    }

    public void JUN() {

    }

    public void JMS() {

    }

    public void INC() {
        System.out.println("Increment register of register");

    }

    public void ISZ() {

    }

    public void ADD() {
        System.out.println("ADD contents of register to accumulador with a carry");

    }

    public void SUB() {

    }

    public void LD() throws SomethingGotWrong {
        try {
            Block instruction = this.Stack[0];
            System.out.println(this.Accumulator);
            int registerId = instruction.getLast4Bits();
            this.Accumulator = (byte) (registerId%2==0 ? this.Accumulator+this.registerMap.get(registerId/2)[0][0] : this.Accumulator+this.registerMap.get(registerId)[1][0]);
            System.out.println(this.Accumulator);
        } catch (Exception e) {
            throw e;
        }

    }

    public void XCH() {

    }

    public void BBL() {

    }

    public void LDM() {

    }

    public void CLB() {

    }

    public void CLC() {

    }

    public void IAC() {

    }

    public void CMC() {

    }

    public void CMA() {

    }

    public void RAL() {

    }

    public void RAR() {

    }

    public void TCC() {

    }

    public void DAC() {

    }

    public void TCS() {

    }

    public void STC() {

    }

    public void DAA() {

    }

    public void KBP() {

    }

    public void DCL() {

    }
}
