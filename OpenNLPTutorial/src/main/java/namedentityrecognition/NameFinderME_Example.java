package namedentityrecognition;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * позволяет находить имена, регионы, места и другие сущности в тексте
 */
public class NameFinderME_Example {
    public static void main(String[] args) throws IOException {

        String [] sentence = new String[]{
                "Mike",
                "and",
                "Smith",
                "are",
                "good",
                "friends"
        };

        /**
         * модель ищет имена
         */
        InputStream is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        NameFinderME nameFinder = new NameFinderME(model);
        /**
         * find() принимает массив строк
         */
        Span[] spans = nameFinder.find(sentence);
        for(Span span: spans){
            System.out.println(span + ": " + sentence[span.getStart()]);
        }
        System.out.println("\n");

        String text = "Mike is senior programming manager and Rama is a clerk both are working at Tutorialspoint";
        is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-token.bin");
        TokenizerModel model1 = new TokenizerModel(is);
        TokenizerME tokenizerME = new TokenizerME(model1);
        String tokens[] = tokenizerME.tokenize(text);
        is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-ner-person.bin");
        model = new TokenNameFinderModel(is);
        nameFinder = new NameFinderME(model);
        spans = nameFinder.find(tokens);
        for(Span span: spans){
            System.out.println(span + ": " + tokens[span.getStart()]);
        }
        System.out.println("\n");

        text = "Tutorialspoint is located in Hyderabad";
        is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-token.bin");
        model1 = new TokenizerModel(is);
        tokenizerME = new TokenizerME(model1);
        tokens = tokenizerME.tokenize(text);
        is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-ner-location.bin");
        model = new TokenNameFinderModel(is);
        nameFinder = new NameFinderME(model);
        spans = nameFinder.find(tokens);
        for(Span span: spans){
            System.out.println(span + ": " + tokens[span.getStart()]);
        }
        System.out.println("\n");



    }
}
