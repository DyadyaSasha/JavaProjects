package utilityclasses;

import org.apache.commons.io.FilenameUtils;

public class FilenameUtilsTester {
    public static void main(String[] args) {
        String path = "D:\\Projects\\Projects for github\\JavaProjects\\CommonsIO\\input.txt";
        /**
         * {@link FilenameUtils} позволяет работать с именами файлов не используя объект {@link java.io.File}
         * может работать на разных OS
         * получаем полный путь
         */
        System.out.println("Full Path: " + FilenameUtils.getFullPath(path));
        /**
         * полный путь без префикса
         * ( в пути C:\dev\project\file.txt
         *  C:\ - префикс
         *  dev\project\ - относительный(relative) путь
         *  C:\dev\project\ - абсолютный путь
         *  file.txt - полное имя
         *  file - базовое имя
         *  txt - расширение
         * )
         */
        System.out.println("Relative Path: " + FilenameUtils.getPath(path));
        /**
         * получаем префикс
         */
        System.out.println("Prefix: " + FilenameUtils.getPrefix(path));
        /**
         * получаем расширение
         */
        System.out.println("Extension: " + FilenameUtils.getExtension(path));
        /**
         * получаем базовое имя
         */
        System.out.println("Base: " + FilenameUtils.getBaseName(path));
        /**
         * получаем полное имя
         */
        System.out.println("Name: " + FilenameUtils.getName(path));
        /**
         * выводит путь без одной/двух точек, указанных в строке пути
         */
        System.out.println("Normalized Path: " + FilenameUtils.normalize(path));

    }
}
