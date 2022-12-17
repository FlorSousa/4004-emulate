import java.util.Scanner;
public class Input {
    Scanner scanner;
    final short maxValueAcceptRange = 259;

    public void setCommand() throws ImpossibleToConvert{
        this.scanner = new Scanner(System.in);
        String command = this.scanner.nextLine();
        Block NewCommandBlock = new Block(command);
        System.out.println(this.verifyCommand(NewCommandBlock) ? "Valid Command" : "Invalid Command");
    };

    public boolean verifyCommand(Block command) throws ImpossibleToConvert{
        try{
            return command.getDecimalCodeBlock() < this.maxValueAcceptRange ? true : false;
        }catch(Exception e){
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }
}