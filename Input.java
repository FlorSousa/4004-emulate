import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    Scanner scanner;
    final short maxValueAcceptRange = 259;
    boolean recvCode = true;
    List<Block> CodeBlocks = new ArrayList<>();

    public void setCommand() throws ImpossibleToConvert{
        while(recvCode){
            this.scanner = new Scanner(System.in);
            String command = this.scanner.nextLine();
            if(command.isBlank() || command.equals("go") || command.equals("GO")){
                break;
            }else{
                Block NewCommandBlock = new Block(command);
                if(this.verifyCommand(NewCommandBlock)) this.CodeBlocks.add(NewCommandBlock);
            }
        }
    };

    public boolean verifyCommand(Block command) throws ImpossibleToConvert{
        try{
            return command.getDecimalCodeBlock() < this.maxValueAcceptRange ? true : false;
        }catch(Exception e){
            throw new ImpossibleToConvert("The block is not a hexadecimal code");
        }
    }

    public List<Block> getCodeBlocks(){
        return this.CodeBlocks;
    }
}