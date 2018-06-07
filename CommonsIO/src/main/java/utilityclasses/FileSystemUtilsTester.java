package utilityclasses;

import org.apache.commons.io.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemUtilsTester {
    public static void main(String[] args) throws IOException {
        /**
         * {@link FileSystemUtils} предоставляет метод для получения свободного места на указанном жёстком диске
         */
        System.out.println("Free Space: " + FileSystemUtils.freeSpaceKb("C:/") + " Bytes");
        /**
         * либо так
         */
        System.out.println("Free Space: " + Files.getFileStore(Paths.get("C:./")).getUsableSpace() + " Bytes");
    }
}
