package deflater;

import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflaterDemo {
    public static void main(String[] args) throws UnsupportedEncodingException, DataFormatException {
        String message = "Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;"
                +"Welcome to TutorialsPoint.com;";
        System.out.println("Original Message length : " + message.length());
        byte[] input = message.getBytes("UTF-8");

        byte[] output = new byte[1024];
//      DEFLATE - алгоритм сжатия(используется в ZLIB библиотеки для сжатия)
        Deflater deflater = new Deflater();
//      устанавливает уровень сжатия
        deflater.setLevel(Deflater.DEFAULT_COMPRESSION);
//      устанавливает стратегию сжатия
        deflater.setStrategy(Deflater.DEFAULT_STRATEGY);
//      устанавливает данные для сжатия
        deflater.setInput(input);


//      указываем, что deflater больше не будет принимать входные данные для сжатия
        deflater.finish();
//      смотрим, закончился ли процесс сжатия данных
        System.out.println(deflater.finished());
//      сжимаем данные и кладём их в массив байтов, возвращает кол-во байтов после сжатия
        int compressedDataLength = deflater.deflate(output);
//      возвращает кол-во байтов, которые на данный момент идут в очереди на сжатие
        System.out.println("Bytes Read :" + deflater.getBytesRead());
//      указывает число байтов, получившихся в результате сжатия на данный момент
        System.out.println("Bytes Written :" + deflater.getBytesWritten());
//      общее число байтов, которые нужно сжать
        System.out.println("Total uncompressed bytes input :" + deflater.getTotalIn());
//      общее число байтов, которые получатся в результате сжатия
        System.out.println("Total uncompressed bytes output :" + deflater.getTotalOut());
//      возвращаем контрольную сумму сжатого сообщения
        System.out.println("Compressed Message Checksum :" + deflater.getAdler());

//      смотрим, закончился ли процесс сжатия данных
        System.out.println(deflater.finished());
//      закрываем объект deflater(подчищаем в нём данные), после этого метода, методы, вызванные у данного объекта будут возвращать NullPointerException
        deflater.end();


        System.out.println("Compressed Message length: " + compressedDataLength);
//      класс для распаковки файлов, сжатых по алгоритму DEFLATE
        Inflater inflater = new Inflater();
//      указываем, какие данные нужно распаковать
        inflater.setInput(output, 0, compressedDataLength);
        byte[] result = new byte[1024];
//      распаковываем сжатые данные
        int resultLength = inflater.inflate(result);

//      возвращаем контрольную сумму расжатого сообщения
        System.out.println("UnCompressed Message Checksum :" + inflater.getAdler());
//      закрываем объект inflater
        inflater.end();

        String message1 = new String(result, 0, resultLength, "UTF-8");
        System.out.println("UnCompressed Message length : " + message1.length());
        System.out.println(message1);

    }
}
