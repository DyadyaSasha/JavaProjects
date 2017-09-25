package jacksonexamples;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenericDataBind {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, UserData> userDataMap = new HashMap<>();
        UserData studentData = new UserData();
        int[] marks = {1,2,3};

        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        studentData.setStudent(student);
        studentData.setName("Mahesh Kumar");
        studentData.setVerified(Boolean.FALSE);
        studentData.setMarks(marks);

        TypeReference reference = new TypeReference<Map<String, UserData>>(){};
        userDataMap.put("studentData1", studentData);
        try {
            mapper.writeValue(new File("generic.json"), userDataMap);
            userDataMap = mapper.readValue(new File("generic.json"), reference);
            System.out.println(userDataMap.get("studentData1").getStudent());
            System.out.println(userDataMap.get("studentData1").getName());
            System.out.println(userDataMap.get("studentData1").getVerified());
            System.out.println(Arrays.toString(userDataMap.get("studentData1").getMarks()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
