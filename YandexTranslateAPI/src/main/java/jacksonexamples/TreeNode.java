package jacksonexamples;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;

public class TreeNode {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh Kumar\",  \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";

        try {
            JsonNode rootNode = mapper.readTree(jsonString);
            JsonNode nameNode = rootNode.path("name");
            System.out.println("Name: " + nameNode.textValue());

            JsonNode ageNode = rootNode.path("age");
            System.out.println("Age: " + ageNode.intValue());

            JsonNode veriefiedNode = rootNode.path("verified");
            System.out.println("Verified: " + (veriefiedNode.booleanValue() ? "Yes" : "No"));

            JsonNode marksNode = rootNode.path("marks");
            Iterator<JsonNode> iterator = marksNode.elements();
            System.out.print("Marks: [ ");
            while (iterator.hasNext()){
                JsonNode marks = iterator.next();
                System.out.print(marks.intValue() + " ");
            }

            System.out.println("]");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
