package src;
import java.util.List;

import src.Exceptions.SomethingGotWrong;

public class Runner {
    public static void main(String[] args) throws Exception{
        Input entrada = new Input();
        System.out.println("Insert the object code in this format : 00, 22 A2, etc. And always finish using 00 to No Operation Code");
        System.out.println("Example.: 20 40 DA 00");
        System.out.println("Object Code:");
        if(entrada.setCommand()){
            List<Block> CommandList = entrada.getCodeBlocks();
            DataRom Rom = new DataRom(CommandList);
            Emulator newEmulator = new Emulator(Rom);
            newEmulator.ExecuteOperation();
        }else{
            throw new SomethingGotWrong("Something got wrong and the emulator can't set the commands in ROOM class");
        }
        
    }
}
