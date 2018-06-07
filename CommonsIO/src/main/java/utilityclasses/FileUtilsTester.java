package utilityclasses;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtilsTester {
    public static void main(String[] args) throws IOException{
        /**
         * {@link FileUtils} содержит методы для работы с файлами и директориями такие как: открытие, перемещение,
         * проверка существования, чтение, запись, копирование, сравнение содержимого в файлах, вычисление контрольной суммы
         */
        File file = FileUtils.getFile("input.txt");
        /**
         *
         Возвращает файл, представляющий системный временный каталог.
         */
        File tmpDir = FileUtils.getTempDirectory();
        System.out.println(tmpDir.getName());
        /**
         * копируем файл в директорию
         */
        FileUtils.copyFileToDirectory(file, tmpDir);
        File newTempFile = FileUtils.getFile(tmpDir, file.getName());
        /**
         * читаем файл в строку(указываем кодировку)
         */
        String data = FileUtils.readFileToString(newTempFile, Charset.defaultCharset());

        System.out.println(data);


    }
}
