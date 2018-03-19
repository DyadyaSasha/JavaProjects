import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;

/**
 * аннотация @{@link com.google.gson.annotations.Since} позволяет сериализовывать/десирилизовывать только поля конкретной версии
 */
public class Versioning {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        /**
         * устанавливаем версию, которую надо будет учитывать при сериализации/десириализации
         */
        builder.setVersion(1.0);
        Gson gson = builder.create();

        Student2 student = new Student2();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);

        gson = new Gson();
        jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }
}

class Student2{
    @Since(1.0)
    private int rollNo;

    @Since(1.0)
    private String name;

    @Since(1.1)
    private boolean verified;

    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public boolean isVerified() {
        return verified;
    }
}
