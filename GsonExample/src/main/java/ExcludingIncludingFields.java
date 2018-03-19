import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Modifier;

/**
 * по умолчанию Gson не сериализует static и transient поля
 */
public class ExcludingIncludingFields {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Student3 student = new Student3();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);
        student.setId(1);
        student.className = "VI";
        /**
         * проигнорирует static и transient поля
         */
        String jsonString = gson.toJson(student);
        System.out.println(jsonString);

        System.out.println();

        GsonBuilder builder = new GsonBuilder();
        /**
         * будут игнорироваться только transient поля(чтобы не одно из полей не игнорировалось
         * нужно оставить метод excludeFieldsWithModifiers() без аргументов)
         */
        builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
        gson = builder.create();

        jsonString = gson.toJson(student);
        System.out.println(jsonString);

        System.out.println();

        builder = new GsonBuilder();
        /**
         * игнорирует поля, которые не помечены аннотацией {@link Expose}
         */
        builder.excludeFieldsWithoutExposeAnnotation();
        gson = builder.create();
        jsonString = gson.toJson(student);
        System.out.println(jsonString);


    }
}

class Student3 {
    @Expose
    private int rollNo;
    @Expose
    private String name;
    private boolean verified;
    private transient int id;
    public static String className;

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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}


