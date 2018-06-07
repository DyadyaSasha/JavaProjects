package filterclasses;

import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;

public class SuffixFileFilterTester {
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

        System.out.println("\nFile with extenstion txt\n");
        /**
         * {@link org.apache.commons.io.filefilter.SuffixFileFilter} фильтрует файлы по указанному расширению
         */
        String[] filesNames = currentDirectory.list(new SuffixFileFilter("txt"));
        for (int i = 0; i < filesNames.length; i++){
            System.out.println(filesNames[i]);
        }
    }
}
