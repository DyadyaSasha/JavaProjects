package jacksonexamples;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class TreeToJSON {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.createObjectNode();
        JsonNode marksNode = mapper.createArrayNode();

        ((ArrayNode) marksNode).add(100);
        ((ArrayNode) marksNode).add(90);
        ((ArrayNode) marksNode).add(85);

        ((ObjectNode) rootNode).put("name", "Mahesh Kumar");
        ((ObjectNode) rootNode).put("age", 21);
        ((ObjectNode) rootNode).put("verified", false);
        ((ObjectNode) rootNode).put("marks", marksNode);

        try {
            mapper.writeValue(new File("treetoJSON.json"), rootNode);

            rootNode = mapper.readTree(new File("treetoJSON.json"));

            JsonNode nameNode = rootNode.path("name");
            System.out.println("Name: " + nameNode.textValue());

            JsonNode ageNode = rootNode.path("age");
            System.out.println("Age: " + ageNode.intValue());

            JsonNode verifiedNode = rootNode.path("verified");
            System.out.println("Verified: " + (verifiedNode.booleanValue() ? "Yes" : "No"));

            JsonNode marksNode1 = rootNode.path("marks");
            Iterator<JsonNode> iterator = marksNode1.elements();
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
