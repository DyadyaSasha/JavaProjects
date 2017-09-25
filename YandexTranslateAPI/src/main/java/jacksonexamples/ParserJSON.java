package jacksonexamples;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;

public class ParserJSON {
    public static void main(String[] args) {
        JsonFactory factory = new JsonFactory();
        try {
            JsonParser parser = factory.createParser(new File("generator.json"));
            while(parser.nextToken() != JsonToken.END_OBJECT){
                String fieldName = parser.getCurrentName();

                if("name".equals(fieldName)){
                    parser.nextToken();
                    System.out.println(parser.getText());
                }

                if("age".equals(fieldName)){
                    parser.nextToken();
                    System.out.println(parser.getNumberValue());
                }

                if("verified".equals(fieldName)){
                    parser.nextToken();
                    System.out.println(parser.getBooleanValue());
                }

                if("marks".equals(fieldName)){
                    parser.nextToken();
                    while(parser.nextToken() != JsonToken.END_ARRAY){
                        System.out.println(parser.getNumberValue());
                    }
                }
            }
            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
