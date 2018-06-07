package filterclasses;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;

public class WildcardFileFilterTester {
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

        System.out.println("\nFile name ending with t.\n");
        /**
         * {@link WildcardFileFilter} представляет собой фильтр, фильтрующий имена директорий и файлов по подстановочному знаку(wildcard)
         */
        String[] filesNames = currentDirectory.list(new WildcardFileFilter("*t"));
        for (int i = 0; i < filesNames.length; i++){
            System.out.println(filesNames[i]);
        }
    }
}
