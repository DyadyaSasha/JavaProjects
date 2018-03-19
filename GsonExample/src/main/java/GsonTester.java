import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class GsonTester {
    public static void main(String[] args) {

        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        /**
         * из строки в формате JSON в объект
         */
        Student student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);

        /**
         * пишем в файл
         */
        try(FileWriter writer = new FileWriter("student.json")) {
            writer.write(gson.toJson(student));
            writer.close();
        } catch (IOException e){
        }

        /**
         * читаем из файла
         */
        try(BufferedReader reader = new BufferedReader(new FileReader("student.json"))){
            student = gson.fromJson(reader, Student.class);
        } catch (IOException e){}

        /**
         * из объекта в JSON
         */
        jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }


}

class Student{

    private String name;
    private int age;

    public Student(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String toString() {
        return "Student [ name: "+name+", age: "+ age+ " ]";
    }
}
