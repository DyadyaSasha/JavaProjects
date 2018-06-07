package comparatorclasses;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;

public class LastModifiedFileComparatorTester {
    public static void main(String[] args) throws IOException{

        File currentDirectory = new File(".");
        /**
         * {@link org.apache.commons.io.comparator.LastModifiedFileComparator} сравнивает файлы/директории по дате последнего изменения
         */
        LastModifiedFileComparator comparator = new LastModifiedFileComparator();

        File[] sortedFiles = comparator.sort(currentDirectory.listFiles((FileFilter) FileFileFilter.FILE));

        System.out.println("Sorted By Last Modified date: ");
        for(File file:sortedFiles) {
            System.out.println(file.getName() + ", Modified on: " + new Date(file.lastModified()));
        }


    }
}
