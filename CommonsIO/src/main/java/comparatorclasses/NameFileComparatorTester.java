package comparatorclasses;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class NameFileComparatorTester {
    public static void main(String[] args) throws IOException{
        File currentDirectory = new File(".");
        /**
         * {@link NameFileComparator} позволяет сравнивать имена файлов, чтобы использовать при сортировке списков файлов
         * {@link FileFileFilter.FILE} позволяет выбирать только файлы
         */
        NameFileComparator comparator = new NameFileComparator(IOCase.INSENSITIVE);
        File[] sortedFiles = comparator.sort(currentDirectory.listFiles((FileFilter) FileFileFilter.FILE));

        System.out.println("Sorted by name:");
        for(File file:sortedFiles){
            System.out.println(file.getName());
        }

    }
}
