public class Block {
    String codeBlock;
    Byte decimalCodeBlock;

    Block(String codeBlock) throws ImpossibleToConvert{
        this.codeBlock = codeBlock;
        this.decimalCodeBlock();
    }

    public void decimalCodeBlock() throws ImpossibleToConvert{
        try{
            this.decimalCodeBlock = Byte.parseByte(this.codeBlock, 16);
        }catch(Exception e){
            throw new ImpossibleToConvert("Overflow");
        }
    }

    public int getDecimalCodeBlock(){
        return this.decimalCodeBlock;
    }

    public String getCodeBlock(){
        return this.codeBlock;
    }
}
