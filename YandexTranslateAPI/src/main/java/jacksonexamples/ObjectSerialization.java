package jacksonexamples;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ObjectSerialization {
    public static void main(String[] args){

        ObjectSerialization tester = new ObjectSerialization();

        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        try {
            tester.writeJSON(student);
            Student student1 = tester.readJSON();
            System.out.println(student1);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void writeJSON(Student student) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), student);
    }

    private Student readJSON() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(new File("student.json"), Student.class);
        return student;
    }
}
