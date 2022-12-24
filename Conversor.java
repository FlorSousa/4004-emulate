import java.util.HashMap;
import java.util.Map;

public class Conversor {
    Map<Integer, String> OperationsMap;
    Map<Integer, String> MultiplexFOperationsCode;
    Conversor() {
        this.OperationsMap = new HashMap<>();
        this.MultiplexFOperationsCode = new HashMap<>();
        this.OperationsMap.put(0, "NOP");
        this.OperationsMap.put(1, "JCN");
        this.OperationsMap.put(2, "FIM");
        this.OperationsMap.put(3, "MULTIA");
        this.OperationsMap.put(4, "JUN");
        this.OperationsMap.put(5, "JMS");
        this.OperationsMap.put(6, "INC");
        this.OperationsMap.put(7, "ISZ");
        this.OperationsMap.put(8, "ADD");
        this.OperationsMap.put(9, "SUB");
        this.OperationsMap.put(10, "LD");
        this.OperationsMap.put(12, "XCH");
        this.OperationsMap.put(13, "BBL");
        this.OperationsMap.put(14, "NOP");
        this.OperationsMap.put(15, "MULTIB");
        //
        this.MultiplexFOperationsCode.put(0, "CLB");
        this.MultiplexFOperationsCode.put(1, "CLC");
        this.MultiplexFOperationsCode.put(2, "IAC");
        this.MultiplexFOperationsCode.put(3, "CMC");
        this.MultiplexFOperationsCode.put(4, "CMA");
        this.MultiplexFOperationsCode.put(5, "RAL");
        this.MultiplexFOperationsCode.put(6, "RAR");
        this.MultiplexFOperationsCode.put(7, "TCC");
        this.MultiplexFOperationsCode.put(8, "DAC");
        this.MultiplexFOperationsCode.put(9, "TCS");
        this.MultiplexFOperationsCode.put(10, "STC");
        this.MultiplexFOperationsCode.put(11, "DAA");
        this.MultiplexFOperationsCode.put(12, "KBP");
        this.MultiplexFOperationsCode.put(13, "DCL");
    }

    public String ConvertOperation(Block InstructionToConvert) throws ImpossibleToConvert {
        try {
            String MnemonicReturn = "NOP";
            String mnemonic = this.OperationsMap.get((int) InstructionToConvert.getFirst4Bits());
            if (mnemonic.equals("MULTIA")) {
                boolean isJIN = ((InstructionToConvert.getLast4Bits() & 0b000000001) == 1);
                MnemonicReturn = isJIN ? "JIN" : "FIN"; 
            } else if (mnemonic.equals("MULTIB")) {
                MnemonicReturn = this.MultiplexFOperationsCode.get((int) InstructionToConvert.getLast4Bits());
            }else{
                MnemonicReturn = mnemonic;
            }
            return MnemonicReturn;
        } catch (Exception e) {
            throw new ImpossibleToConvert("Cannot convert this block");
        }
    }

}
