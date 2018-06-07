package streamclasses;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TeeInputStreamTester {
    public static void main(String[] args) throws IOException {

        String sample = "Welcome to TutorialsPoint. Simply Easy Learning.";
        /**
         * {@link TeeOutputStream} и {@link TeeInputStream} предоставляют промежуточные(прокси) классы для связи
         * двух соответсвующий потоков {@link java.io.OutputStream} и {@link java.io.InputStream}
         */
        TeeInputStream teeInputStream = null;
        TeeOutputStream teeOutputStream = null;

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(sample.getBytes("US-ASCII"))) {
            try (ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream()) {
                try (ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream()){
                    teeOutputStream = new TeeOutputStream(outputStream1, outputStream2);
                    teeInputStream = new TeeInputStream(inputStream, teeOutputStream, true);
                    teeInputStream.read(new byte[sample.length()]);

                    System.out.println("Output stream 1: " + outputStream1.toString());
                    System.out.println("Output stream 2: " + outputStream2.toString());

                }
            }
        }

    }
}
