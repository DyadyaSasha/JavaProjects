package utilityclasses;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class IOUtilsTester {

    public static void main(String[] args) {
        try {
            readUsingTraditionalWay();

            readUsingIOUtils();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void readUsingTraditionalWay() throws IOException{
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        }
    }

    /**
     * {@link IOUtils} - утилитарный класс, содержащий методы для чтения, записи и копирования файлов
     * мметоды данного класса работают с {@link InputStream}, {@link OutputStream}, {@link Reader}, {@link Writer}
     */
    private static void readUsingIOUtils() throws IOException{
        try(InputStream in = new FileInputStream("input.txt")){
            /**
             * представляем содержимое файла в указанной кодировке в виде строки
             */
            System.out.println(IOUtils.toString(in, "UTF-8"));
        }
    }
}
