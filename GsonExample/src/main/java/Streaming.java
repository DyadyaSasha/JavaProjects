import com.google.gson.annotations.Since;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

public class Streaming {
    public static void main(String[] args) {

        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        /**
         * позволяет считывать JSON объект по принципу Stax в XML
         */
        JsonReader reader = new JsonReader(new StringReader(jsonString));
        try {
            handleJsonObject(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleJsonObject(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;
        while (reader.hasNext()) {
            /**
             * берём тип json элемента
             */
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.BEGIN_ARRAY)) {
                System.out.print("Marks [ ");
                handleJsonArray(reader);
                System.out.println("]");
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else {
                if (token.equals(JsonToken.NAME)) {
                    fieldname = reader.nextName();
                }
                if (fieldname.equals("name")) {
                    token = reader.peek();
                    System.out.println("Name: " + reader.nextString());
                }
                if (fieldname.equals("age")) {
                    token = reader.peek();
                    System.out.println("Age:" + reader.nextInt());
                }
                if (fieldname.equals("verified")) {
                    token = reader.peek();
                    System.out.println("Verified:" + reader.nextBoolean());
                }
            }
        }
    }

    private static void handleJsonArray(JsonReader reader) throws IOException {
        reader.beginArray();
        String fieldname = null;
        while (true) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                handleJsonObject(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else {
                System.out.print(reader.nextInt() + " ");
            }
        }
    }


}
