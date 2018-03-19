package chunking;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChunkerExample {
    public static void main(String[] args) throws IOException {
        String sentence = "Hi welcome to Tutorialspoint";
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String tokens[] = whitespaceTokenizer.tokenize(sentence);
        File binFile = new File(System.getProperty("user.dir") + "\\lib\\en-pos-maxent.bin");
        POSModel model = new POSModelLoader().load(binFile);
        POSTaggerME taggerME = new POSTaggerME(model);
        String tags[] = taggerME.tag(tokens);

        ChunkerModel chunkerModel = new ChunkerModel(new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-chunker.bin"));
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        String results[] = chunkerME.chunk(tokens, tags);
        for(String result: results){
            System.out.println(result);
        }
        System.out.println("\n");

        Span spans[] = chunkerME.chunkAsSpans(tokens, tags);
        for(Span span: spans){
            System.out.println(span);
        }
        System.out.println("\n");

        double probs[] = chunkerME.probs();
        for(double prob: probs){
            System.out.println(prob);
        }
    }
}
