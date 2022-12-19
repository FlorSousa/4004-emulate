import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    Scanner scanner;
    final short maxValueAcceptRange = 259;
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
                    String[] returnString = haveAssociateValue(line);
                    Block NewCommandBlock = returnString[1] != null ? createNewBlock(returnString[0], returnString[1])
                            : createNewBlock(returnString[0]);
                    if (this.verifyCommand(NewCommandBlock))
                        this.CodeBlocks.add(NewCommandBlock);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    };

    public Block createNewBlock(String command) throws ImpossibleToConvert {
        try {
            return new Block(command);
        } catch (Exception e) {
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }

    public Block createNewBlock(String command, String value) throws ImpossibleToConvert {
        try {
            return new Block(command, value);
        } catch (Exception e) {
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }

    public boolean verifyCommand(Block command) throws ImpossibleToConvert {
        try {
            return command.getDecimalCodeBlock() < this.maxValueAcceptRange ? true : false;
        } catch (Exception e) {
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }

    public List<Block> getCodeBlocks() {
        return this.CodeBlocks;
    }

    public String[] haveAssociateValue(String line) {
        String cmd;
        String Value;
        String[] rtn = new String[2];
        try {
            cmd = line.split(" ")[0];
            Value = line.split(" ")[1];
            rtn[0] = cmd;
            rtn[1] = Value;
        } catch (Exception e) {
            rtn[0] = line.split(" ")[0];
            rtn[1] = null;
        }
        return rtn;
    }
}