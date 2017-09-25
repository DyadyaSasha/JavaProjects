package objectstreams;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

/**
 * Сериализация и десириализация по аналогии с эталонной сериализацией и десириализацией
 * в Java через {@link ObjectOutputStream} и {@link ObjectInputStream} соответственно
 * */
public class XStreamTester {

    public static void main(String[] args) {

        XStreamTester tester = new XStreamTester();
        XStream xStream = new XStream(new StaxDriver());

        xStream.autodetectAnnotations(true);

        Student student1 = new Student("Mahesh","Parashar");
        Student student2 = new Student("Suresh","Kalra");
        Student student3 = new Student("Ramesh","Kumar");
        Student student4 = new Student("Naresh","Sharma");

        try {
            ObjectOutputStream outputStream = xStream.createObjectOutputStream(
                    new FileOutputStream("text.txt"));
            outputStream.writeObject(student1);
            outputStream.writeObject(student2);
            outputStream.writeObject(student3);
            outputStream.writeObject(student4);
            outputStream.writeObject("Hello World!");

            outputStream.close();

            ObjectInputStream inputStream = xStream.createObjectInputStream(
                    new FileInputStream("text.txt"));
            Student student5 = (Student) inputStream.readObject();
            Student student6 = (Student) inputStream.readObject();
            Student student7 = (Student) inputStream.readObject();
            Student student8 = (Student) inputStream.readObject();
            String text = (String) inputStream.readObject();

            System.out.println(student5);
            System.out.println(student6);
            System.out.println(student7);
            System.out.println(student8);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
