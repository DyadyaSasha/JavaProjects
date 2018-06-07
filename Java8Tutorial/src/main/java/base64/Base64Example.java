package base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * стандарт кодирования Base64 был введён в Java 8
 */
public class Base64Example {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /**
         * кодируем строчку
         */
        String base64EncodedString = Base64.getEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
        System.out.println("Base64 Encoded String (Basic Encoder) :" + base64EncodedString);

        /**
         * декодируем
         */
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64EncodedString);

        System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
        System.out.println();

        /**
         * кодируем
         */
        base64EncodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
        System.out.println("Base64 Encoded String (URL Encoder) :" + base64EncodedString);
        System.out.println();

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 10; ++i){
            stringBuilder.append(UUID.randomUUID().toString());
        }

        byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 Encoded String (MIME Encoder) :" + mimeEncodedString);



    }
}
