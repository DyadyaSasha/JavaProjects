package testexample;

public class ClassToTest {

    private String message;

    public ClassToTest(String message){
        this.message = message;
    }

    public String printMessage() {
        System.out.println(message);
        return message;
    }
}
