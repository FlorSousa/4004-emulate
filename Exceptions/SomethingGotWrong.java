package Exceptions;
public class SomethingGotWrong extends Exception{
    public SomethingGotWrong(String msg){
        System.out.println(msg);
    }
}