import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * по умолчанию null значения игнорируются, но можно сериализовать null значения
 * для этого нужно у объекта типа {@link com.google.gson.GsonBuilder} вызвать метод serializeNulls()
 */
public class SupportNull {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        /**
         * будут учитываться null значения
         */
        builder.serializeNulls();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Student1 student = new Student1();
        student.setRollNo(1);
        String jsonString = gson.toJson(student);

        System.out.println(jsonString);

        student = gson.fromJson(jsonString, Student1.class);
        System.out.println(student);

    }
}

class Student1 {
    private int rollNo;
    private String name;

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
    public String toString() {
        return "Student[ name = "+name+", roll no: "+rollNo+ "]";
    }
}
