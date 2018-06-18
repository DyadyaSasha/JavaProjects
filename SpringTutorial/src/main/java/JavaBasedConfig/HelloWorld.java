package JavaBasedConfig;

public class HelloWorld {

    private String message;

    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Your Message : " + message);
    }

    private void destroy() {
        System.out.println("Hello World destroy");
    }

    private void init() {
        System.out.println("Hello World init");
    }
}
