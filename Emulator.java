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


}
