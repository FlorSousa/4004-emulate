public class SomethingGotWrong extends Exception{
    SomethingGotWrong(String msg){
        System.out.println(msg);
    }
}