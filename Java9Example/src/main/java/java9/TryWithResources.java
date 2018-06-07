package java9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class TryWithResources {
    public static void main(String[] args) throws IOException{

        /**
         * здесь в try with resources нужно отдельно создавать объект {@link BufferedReader}
         * с Java 9 достаточно просто указать ссылку на него
         */
        Reader inputString = new StringReader("test");
        BufferedReader br = new BufferedReader(inputString);
        try (BufferedReader br1 = br){
            System.out.println(br1.readLine());
        }

        /**
         * c Java 9
         */
        Reader inputString1 = new StringReader("test");
        BufferedReader br1 = new BufferedReader(inputString1);
        try (br1){
            System.out.println(br1.readLine());
        }

    }
}
