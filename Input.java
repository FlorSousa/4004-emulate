import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    Scanner scanner;
    boolean recvCode = true;
    List<Block> CodeBlocks = new ArrayList<>();

    public boolean setCommand() throws ImpossibleToConvert {
        try {
            while (recvCode) {
                this.scanner = new Scanner(System.in);
                String line = this.scanner.nextLine();
                if (line.isBlank() || line.equals("go") || line.equals("GO")) {
                    break;
                } else {
                    String[] returnString = SplitLine(line);

                    Block NewCommandBlock = returnString[2] != null ? createNewBlock(returnString[0], returnString[1], returnString[2])
                            : createNewBlock(returnString[0], returnString[1]);
                    if (this.verifyCommand(NewCommandBlock))
                        this.CodeBlocks.add(NewCommandBlock);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    };

    public Block createNewBlock(String First4Bits, String Second4Bits) throws ImpossibleToConvert {
        try {
            return new Block(First4Bits,Second4Bits);
        } catch (Exception e) {
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }

    public Block createNewBlock(String First4Bits, String Second4Bits, String value) throws ImpossibleToConvert {
        try {
            return new Block(First4Bits, Second4Bits, value);
        } catch (Exception e) {
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }

    public boolean verifyCommand(Block command) throws ImpossibleToConvert {
        try {
            return (command.getFirst4Bits()<=15 && command.getLast4Bits()<=15);
        } catch (Exception e) {
            throw new ImpossibleToConvert("The object code are wrong!");
        }
    }

    public List<Block> getCodeBlocks() {
        return this.CodeBlocks;
    }

    public String[] SplitLine(String line) {
        String FirstWord;
        String SecondWord;
        String Value;
        String[] rtn = new String[3];
        try {
            if(line.contains(" ")){
                FirstWord = (line.split(" ")[0]).split("")[0];
                SecondWord = (line.split(" ")[0]).split("")[1];
                Value = line.split(" ")[1];
                rtn[0] = FirstWord;
                rtn[1] = SecondWord;
                rtn[2] = Value;
            }else{
                FirstWord = (line.split(" ")[0]).split("")[0];
                SecondWord = (line.split(" ")[0]).split("")[1];
                rtn[0] = FirstWord;
                rtn[1] = SecondWord;
            }
            return rtn;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }
}