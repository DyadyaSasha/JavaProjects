package group;

public class MessageUtil {

    private String message;

    public MessageUtil(String message){
        this.message = message;
    }

    public String printMessage() {
        return message;
    }

    public String salutationMessage(){
        message = "tutorialspoint" + message;
        return message;
    }

    public String exitMessage() {
        message = "www." + message;
        return message;
    }
}
