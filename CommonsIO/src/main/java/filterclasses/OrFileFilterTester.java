package filterclasses;

import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;

public class OrFileFilterTester {
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

        System.out.println("\nFile starting with . or ends with t\n");
        /**
         * {@link OrFileFilter} позволяет использовать несколько фильтров, которые используются согласно логическому оператору "ИЛИ"
         */
        String[] filesNames = currentDirectory.list(new OrFileFilter(new PrefixFileFilter("."), new WildcardFileFilter("*t")));
        for(int i = 0; i < filesNames.length; i++){
            System.out.println(filesNames[i]);
        }
    }
}
