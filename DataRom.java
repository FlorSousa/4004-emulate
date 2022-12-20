import java.util.List;

public class DataRom {
    private List<Block> ROM;
    DataRom(List<Block> ROM){
        this.ROM = ROM;
    }

    public Block ReturnBlock(byte pointer){
        return pointer <= this.ROM.size()-1 ? this.ROM.get(pointer) : null;
    }
    
}
