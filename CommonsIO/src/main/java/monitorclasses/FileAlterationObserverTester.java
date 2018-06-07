package monitorclasses;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileAlterationObserverTester {
    public static void main(String[] args) throws Exception {

        File inputFile = new File("input.txt");
        String absolutePath = inputFile.getAbsolutePath();
        String parent = absolutePath.substring(0,absolutePath.indexOf("input.txt"));
        File parentDirectory = FileUtils.getFile(parent);
        /**
         * {@link FileAlterationObserver} позволяет проверять изменения в фаловой системе(создание, удаление и т.п.) и вызывать соответсвующие
         * методы в классе слушателе
         */
        FileAlterationObserver observer = new FileAlterationObserver(parentDirectory);
        /**
         * добавляем класс-слушатель
         */
        observer.addListener(new FileAlterationListenerAdaptor(){

            @Override
            public void onDirectoryCreate(File directory) {
                System.out.println("Folder created: " + directory.getName());
            }

            @Override
            public void onDirectoryDelete(File directory) {
                System.out.println("Folder deleted: " + directory.getName());
            }

            @Override
            public void onFileCreate(File file) {
                System.out.println("File created: " + file.getName());
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("File deleted: " + file.getName());
            }
        });

        /**
         * поток, который проверяет(следит за) {@link FileAlterationObserver} в указанные промежутки времени
         */
        FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
        /**
         * начинаем следить за {@link FileAlterationObserver}
         */
        monitor.start();

        File newFolder = new File("testtt");
        File newFile = new File("file1");
        /**
         * создаём директорию указанного объекта {@link File}
         */
        newFolder.mkdir();
        Thread.sleep(1000);
        newFile.createNewFile();
        Thread.sleep(1000);
        /**
         * удаляем файл в файловой системе, который соответствует указанному объекту {@link File}
         */
        FileDeleteStrategy.NORMAL.delete(newFolder);
        Thread.sleep(1000);
        FileDeleteStrategy.NORMAL.delete(newFile);
        Thread.sleep(1000);

        monitor.stop(10000);
    }
}
