public class Block {
    String codeBlock;
    Short decimalCodeBlock;

    Block(String codeBlock){
        this.codeBlock = codeBlock;
        this.decimalCodeBlock();
    }

    public void decimalCodeBlock(){
        this.decimalCodeBlock = Short.parseShort(this.codeBlock, 16);
    }

    public int getDecimalCodeBlock(){
        return this.decimalCodeBlock;
    }

    public String getCodeBlock(){
        return this.codeBlock;
    }
}
