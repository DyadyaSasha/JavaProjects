package jacksonexamples;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataBinding {
    public static void main(String[] args){

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> studentDataMap = new HashMap<>();
        int[] marks = {1,2,3};

        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        studentDataMap.put("student", student);

        studentDataMap.put("name", "Mahesh Kumar");

        studentDataMap.put("verified", Boolean.FALSE);

        studentDataMap.put("marks", marks);

        try {
            mapper.writeValue(new File("databinding.json"), studentDataMap);

            studentDataMap = mapper.readValue(new File("databinding.json"), Map.class);

            System.out.println(studentDataMap.get("student"));
            System.out.println(studentDataMap.get("name"));
            System.out.println(studentDataMap.get("verified"));
            System.out.println(studentDataMap.get("marks"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
