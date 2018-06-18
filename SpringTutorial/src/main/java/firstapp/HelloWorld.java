package firstapp;

/**
 * обычный бин
 */

public class HelloWorld {

    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public void getMessage(){
        System.out.println("Message: " + message);
    }

    public void init(){
        System.out.println("Hello World Bean init method");
    }

    public void destroy(){
        System.out.println("Hello World Bean destroy method");
    }
}
