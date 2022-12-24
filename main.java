import java.util.List;

public class main {
    public static void main(String[] args) throws Exception{
        Input entrada = new Input();
        System.out.println("Insert the object code in this format : 00, 22, etc");
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
