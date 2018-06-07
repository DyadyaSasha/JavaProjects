package filterclasses;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.NameFileFilter;

import java.io.File;

public class NameFileFilterTester {
    public static void main(String[] args) {
        File currentDirectory = new File(".");
        /**
         * массив папок и файлов в директории
         */
        String[] files = currentDirectory.list();
        System.out.println("All files and folders.\n");
        for (int i = 0; i < files.length; i++){
            System.out.println(files[i]);
        }

        System.out.println("\nFile with name input.txt\n");
        String[] acceptedNames = {"input","input.txt"};
        /**
         * {@link NameFileFilter} позволяет фильтровать файлы по имени
         */
        String[] filesNames = currentDirectory.list(new NameFileFilter(acceptedNames, IOCase.INSENSITIVE));

        for (int i = 0; i < filesNames.length; i++){
            System.out.println(filesNames[i]);
        }
    }
}
