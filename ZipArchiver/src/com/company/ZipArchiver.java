package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by user on 04.05.17.
 */
public class ZipArchiver implements Archiver {

    public void createZipArchiveWithFile(String zipArchiveName, String fileName) throws IOException{
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipArchiveName));
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry ze = new ZipEntry(file.getName());
        zos.putNextEntry(ze);

        writeFromFisToZos(fis, zos);

        fis.close();
        zos.closeEntry();
        zos.close();
    }

    protected void writeFromFisToZos(FileInputStream inputStream, ZipOutputStream zipOutputStream) throws IOException{
        byte[] buf = new byte[8000];
        int length;
        while (true){
            length = inputStream.read(buf);
            if (length < 0) break;
            zipOutputStream.write(buf, 0, length);
        }
    }
}
