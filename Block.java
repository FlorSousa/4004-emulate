public class Block {
    String codeBlock;
    byte decimalCodeBlock;

    Block(String codeBlock){
        this.codeBlock = codeBlock;
        this.decimalCodeBlock();
    }

    public void decimalCodeBlock(){
        this.decimalCodeBlock = Byte.parseByte(this.codeBlock, 16);
    }

    public int getDecimalCodeBlock(){
        return this.decimalCodeBlock;
    }

    public String getCodeBlock(){
        return this.codeBlock;
    }
}
