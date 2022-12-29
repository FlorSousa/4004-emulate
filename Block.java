public class Block {
    String First4Bits;
    String Second4Bits;
    String Value;
    
    Block(String First4Bit, String Second4Bit ) throws ImpossibleToConvert{
        this.First4Bits = First4Bit;
        this.Second4Bits = Second4Bit;
    }

    Block(String First4Bit, String Second4Bit, String Value ) throws ImpossibleToConvert{
        this.First4Bits = First4Bit;
        this.Second4Bits = Second4Bit;
        this.Value = Value;
    }

    public byte getFirst4Bits(){
        return Byte.parseByte(First4Bits,16);
    }

    public byte getLast4Bits(){
        return Byte.parseByte(Second4Bits,16);
    }

    public boolean hasAssociatedValue(){
        return this.Value != null ? true : false;
    }

    public String getAssociatedValue(){
        return this.Value;
    }

    public String returnHex(){
        if(hasAssociatedValue()){
            return this.First4Bits + this.Second4Bits +this.Value;
        }else{
            return this.First4Bits + this.Second4Bits;
        }
    }
}
