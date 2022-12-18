public class Block {
    String codeBlock;
    Short decimalCodeBlock;
    String AssociatadedValue;
    Short AssociatedValueShort;
    
    Block(String codeBlock) throws ImpossibleToConvert{
        this.codeBlock = codeBlock;
        this.decimalCodeBlock();
    }

    Block(String codeBlock, String AssociatadedValue) throws ImpossibleToConvert{
        this.codeBlock = codeBlock;
        this.AssociatadedValue = AssociatadedValue;
        this.decimalCodeBlock();
    }
    
    public boolean hasAssociatedValue(){
        return this.AssociatadedValue != null ? true : false;
    }

    public void decimalCodeBlock() throws ImpossibleToConvert{
        try{
            this.decimalCodeBlock = Short.parseShort(this.codeBlock, 16);
        }catch(Exception e){
            throw new ImpossibleToConvert("Overflow");
        }
    }

    public void decimalAssociatadedValue() throws ImpossibleToConvert{
        try{
            this.AssociatedValueShort = Short.parseShort(this.AssociatadedValue,16);
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
