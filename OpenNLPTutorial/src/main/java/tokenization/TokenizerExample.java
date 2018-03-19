package tokenization;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TokenizerExample {
    public static void main(String[] args) throws IOException {

        String sentence = "Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies";

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = simpleTokenizer.tokenize(sentence);

        for(String token: tokens){
            System.out.println(token);
        }
        System.out.println("\n");


        WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        tokens = tokenizer.tokenize(sentence);
        for (String token: tokens){
            System.out.println(token);
        }
        System.out.println("\n");

        InputStream is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-token.bin");
        TokenizerModel model = new TokenizerModel(is);
        TokenizerME tokenizerME = new TokenizerME(model);
        tokens = tokenizerME.tokenize(sentence);
        for (String token: tokens){
            System.out.println(token);
        }
        System.out.println("\n");

        /**
         * выводим позиции каждого промежутка, получившегося в результате разделения
         */
        Span[] spans = tokenizerME.tokenizePos(sentence);
        for(Span span: spans){
            System.out.println(span);
        }
        System.out.println("\n");

        double[] probs = tokenizerME.getTokenProbabilities();
        for(double prob: probs){
            System.out.println(prob);
        }
        System.out.println("\n");

    }
}
