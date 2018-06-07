package firstapp;

import java.io.File;
import java.io.FileFilter;

/**
 * класс, реализующий функциональныый интерфейс {@link FileFilter}, в котором определён метод accept для
 * фильтрации файлов по их полному имени
 */
public class TextFileFilter implements FileFilter{

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}
