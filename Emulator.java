import java.util.HashMap;
import java.util.Map;

public class Emulator {
    private byte[][] P0 = new byte[1][1];
    private byte[][] P1 = new byte[1][1];
    private byte[][] P2 = new byte[1][1];
    private byte[][] P3 = new byte[1][1];
    private byte[][] P4 = new byte[1][1];
    private byte[][] P5 = new byte[1][1];
    private byte[][] P6 = new byte[1][1];
    private byte[][] P7 = new byte[1][1];
    
    private byte[] Stack = new byte[4];
    private byte StackPointer = 0;

    private byte Accumulator = 0;
    private boolean carry = false;

    private Map<String,Runnable> Commands;
    Emulator(){
        Commands = new HashMap<>();
        Commands.put("NOP", () -> this.NOP());
        Commands.put("JCN", () -> this.JCN());
        Commands.put("FIM", () -> this.FIM());
        Commands.put("FIN", () -> this.FIN());
        Commands.put("JIN", () -> this.JIN());
        Commands.put("JUN", () -> this.JUN());
        Commands.put("JMS", () -> this.JMS());
        Commands.put("INC", () -> this.INC());
        Commands.put("ISZ", () -> this.ISZ());
        Commands.put("ADD", () -> this.ADD());
        Commands.put("SUB", () -> this.SUB());
        Commands.put("LD",  () -> this.LD());
        Commands.put("XCH", () -> this.XCH());
        Commands.put("BBL", () -> this.BBL());
        Commands.put("LDM", () -> this.LDM());
        Commands.put("CLB", () -> this.CLB());
        Commands.put("CLC", () -> this.CLC());
        Commands.put("IAC", () -> this.IAC());
        Commands.put("CMC", () -> this.CMC());
        Commands.put("CMA", () -> this.CMA());
        Commands.put("RAL", () -> this.RAL());
        Commands.put("RAR", () -> this.RAR());
        Commands.put("TCC", () -> this.TCC());
        Commands.put("DAC", () -> this.DAC());
        Commands.put("TCS", () -> this.TCS());
        Commands.put("STC", () -> this.STC());
        Commands.put("DAA", () -> this.DAA());
        Commands.put("KBP", () -> this.KBP());
        Commands.put("DCL", () -> this.DCL());
    }

    public void NOP(){
        System.out.println("No Operation - NOP");
    }

    public void JCN(){
        System.out.println("JUMP to ROM address");
    }

    public void FIM(){

    }

    public void FIN(){

    }

    public void JIN(){

    }

    public void JUN(){

    }

    public void JMS(){

    }

    public void INC(){
        System.out.println("Increment register of register");

    }

    public void ISZ(){

    }

    public void ADD(){
        System.out.println("ADD contents of register to accumulador with a carry");

    }

    public void SUB(){

    }

    public void LD(){

    }

    public void XCH(){

    }

    public void BBL(){

    }

    public void LDM(){

    }

    public void CLB(){

    }

    public void CLC(){

    }

    public void IAC(){
        
    }

    public void CMC(){

    }

    public void CMA(){

    }

    public void RAL(){

    }

    public void RAR(){

    }

    public void TCC(){

    }

    public void DAC(){

    }

    public void TCS(){
        
    }

    public void STC(){

    }
    
    public void DAA(){

    }

    public void KBP(){

    }

    public void DCL(){

    }
}
