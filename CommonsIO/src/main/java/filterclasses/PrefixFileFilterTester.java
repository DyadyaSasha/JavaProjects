package filterclasses;

import org.apache.commons.io.filefilter.PrefixFileFilter;

import java.io.File;

public class PrefixFileFilterTester {
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

        System.out.println("\nFile starting with input\n");
        /**
         * {@link org.apache.commons.io.filefilter.PrefixFileFilter} фильтрует файлы по префиксу
         */
        String[] filesNames = currentDirectory.list(new PrefixFileFilter("input"));
        for(int i = 0; i < filesNames.length; i++){
            System.out.println(filesNames[i]);
        }
    }
}
