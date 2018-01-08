package adler32;

import java.io.*;
import java.util.zip.*;

public class AdlerDemo {
    public static void main(String[] args) throws FileNotFoundException {

        String sourceFile = "hello.txt";
        String targetFile = "hello.zip";

        byte[] buffer = new byte[1024];

        try {
            FileOutputStream fos = new FileOutputStream(targetFile);
//          выходной поток, поддерживающий высчитывание контрольной суммы(вместо Adler32 может быть CRC32)
            CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
//          поток для записи файлов в zip файл
            ZipOutputStream zos = new ZipOutputStream(cos);
//          комментарий к zip архиву
            zos.setComment("First Java Zip file");
            FileInputStream fis = new FileInputStream(sourceFile);
//          вставляем в архив файл(класс ZipEntry характеризует отдельный файл в zip архиве)
            zos.putNextEntry(new ZipEntry(sourceFile));
            int length;
//          читаем быйты из файла и записываем в буфер(без этого не будут записаны данные в файл, находящийся внутри архива)
            while ((length = fis.read(buffer)) > 0){
//              пишем символы в файл внутри архива
                zos.write(buffer, 0, length);
            }
//          закрываем текущее вхождение(отдельный файл) в zip архиве
            zos.closeEntry();
            fis.close();
            zos.close();

//          вывод контрольной суммы
            System.out.println("Adler32 checksum: " + cos.getChecksum().getValue());

//          сбрасываем контрольную сумму
            cos.getChecksum().reset();
            System.out.println("Adler32 reset checksum: " + cos.getChecksum().getValue());

            buffer = "Welcome to Tutorialspoint.com".getBytes();
//          обновляем контрольную сумму, исходя из новых данных
            cos.getChecksum().update(buffer, 0, buffer.length);
            System.out.println("Update checksum: " + cos.getChecksum().getValue());


            fis = new FileInputStream(targetFile);
//          входной поток, поддерживающий высчитывание контрольной суммы
            CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());
//          в конце read() возвращает -1, т.е. конец потока
            while (cis.read(buffer, 0, buffer.length) >= 0){}
//          выводит то, что есть в zip файле в виде строки
            System.out.println(new String(buffer));

//          поток для чтения данных из zip архива
            ZipInputStream zin = new ZipInputStream(new FileInputStream(targetFile));
//          класс, характеризующий zip архив
            ZipFile file = new ZipFile(targetFile);
//          получаем комментарий к архиву
            System.out.println(file.getComment());
//          кол-во элементов в архиве
            System.out.println("Files in Zip archive: " + file.size());

            ZipEntry entry = null;

            fos = null;
//          разархивируем файлы из архива в папку
            while ((entry = zin.getNextEntry()) != null){
                System.out.println(entry.getName());
//              считываем и записываем содержимое из текущего сжатого файла в zip файле
                fos = new FileOutputStream("D:\\Projects\\Projects for github\\JavaProjects\\JavaZip\\result\\" + entry.getName());
                while (zin.available() != 0){
                    fos.write(zin.read());
                }
                fos.close();
                zin.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
