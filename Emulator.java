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

    Emulator(){
        
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
