package jacksonexamples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JacksonTester {

    public static void main(String args[]){

        ObjectMapper mapper = new ObjectMapper();
        String jsonString =  "{\"name\":\"Mahesh\", \"age\":21}";

        try {
            /**
             * json строку в объект
             * присвоение поле объекта происходит через сеттеры объекта
             * */
            Student student = mapper.readValue(jsonString, Student.class);

            System.out.println(student);

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            /**
             * объект в json строку
             * через геттеры объекта
             * */
            jsonString = mapper.writeValueAsString(student);

            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


