package jacksonexamples;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GenertorJSON {
    public static void main(String[] args) {
        try {
            JsonFactory factory = new JsonFactory();

            JsonGenerator generator = factory.createGenerator(new File("generator.json"), JsonEncoding.UTF8);

            generator.writeStartObject();
            generator.writeStringField("name", "Mahesh Kumar");
            generator.writeNumberField("age", 21);
            generator.writeBooleanField("verified", false);
            generator.writeFieldName("marks");
            generator.writeStartArray();
            generator.writeNumber(100);
            generator.writeNumber(90);
            generator.writeNumber(85);
            generator.writeEndArray();
            generator.writeEndObject();
            generator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> dataMap = mapper.readValue(new File("generator.json"), Map.class);
            System.out.println(dataMap.get("name"));
            System.out.println(dataMap.get("age"));
            System.out.println(dataMap.get("verified"));
            System.out.println(dataMap.get("marks"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
