package monitorclasses;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileEntry;

import java.io.File;

public class FileEntryTester {
    public static void main(String[] args) {

        File file = FileUtils.getFile("input.txt");
        /**
         * {@link FileEntry} предоставляет методы для мониторинга состояния файла:
         * getName() - имя файла
         * exists() - существует ли файл
         * lastModified() - дата последнего изменения файла
         * isDirectory() - является ли файл директорией
         * listFiles() - список файлов в директории
         */
        FileEntry fileEntry = new FileEntry(file);

        System.out.println("Monitored File: " + fileEntry.getFile());
        System.out.println("File name: " + fileEntry.getName());
        System.out.println("Is Directory: " + fileEntry.isDirectory());
    }
}
