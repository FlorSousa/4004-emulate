import java.util.List;

public class DataRom {
    private List<Block> ROM;
    DataRom(List<Block> ROM){
        this.ROM = ROM;
    }

    public Block ReturnBlock(byte pointer) throws IndexOutOfBoundsException{
         Block block = this.ROM.get(pointer);
         if(block !=null){
            return block;
         }
         throw new IndexOutOfBoundsException("Pointer is out of limit");
    }
    
}
