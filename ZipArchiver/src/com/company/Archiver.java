package com.company;

import java.io.IOException;

/**
 * Created by user on 04.05.17.
 */
public interface Archiver {
    public void createZipArchiveWithFile(String zipArchiveName, String fileName) throws IOException;
}
