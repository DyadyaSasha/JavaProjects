package firstapp;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Indexer {

    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException {

        try (Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath))) {
            writer = new IndexWriter(indexDirectory, new IndexWriterConfig(new StandardAnalyzer()));
        }

    }

    public void close() throws IOException {
        writer.close();
    }

    private Document getDocument(File file) throws FileNotFoundException{

        Document document = new Document();

        Field contentField = new Field(LuceneConstants.CONTENTS, new FileReader(file));
    }

}
