import java.util.List;

public class main {
    public static void main(String[] args) throws Exception{
        Input entrada = new Input();
        if(entrada.setCommand()){
            List<Block> CommandList = entrada.getCodeBlocks();
            DataRom Rom = new DataRom(CommandList);
            Emulator newEmulator = new Emulator(Rom);
        }else{
            throw new SomethingGotWrong("Something got wrong and the emulator can't set the commands in ROOM class");
        }
        
    }
}
