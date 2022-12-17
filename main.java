import java.util.List;

public class main {
    public static void main(String[] args) throws ImpossibleToConvert{
        Input entrada = new Input();
        entrada.setCommand();
        List<Block> CommandList = entrada.getCodeBlocks();
    }
}
