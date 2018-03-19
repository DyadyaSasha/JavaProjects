package partsofspeech;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.uima.postag.POSTagger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * позволяет найти части речи в предложении
 * NN - существительное
 * DT - Determiner
 * VB - глагол в начальной форме
 * VBD - глагол в прошедшем времени
 * VBZ - глагол, третье лицо единственного числа настоящего времени
 * IN - предлог
 * NNP - имя собственное
 * TO - to
 * JJ - прилагательное
 */
public class PosTaggerExample {
    public static void main(String[] args) throws IOException {
        /**
         * отображает производительность
         */
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        perfMon.start();


        InputStream is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-pos-maxent.bin");
        POSModel model = new POSModel(is);
        POSTaggerME taggerME = new POSTaggerME(model);

        String sentence = "Hi welcome to Tutorialspoint";

        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String tokens[] = whitespaceTokenizer.tokenize(sentence);

        /**
         * определяем к какой части речи относится предложение
         */
        String[] tags = taggerME.tag(tokens);

        /**
         * сопоставляем части речи с предложенем
         */
        POSSample sample = new POSSample(tokens, tags);
        System.out.println(sample);

        System.out.println("\n");
        double[] probs = taggerME.probs();
        for(double prob: probs){
            System.out.println(prob);
        }
        System.out.println("\n");


        perfMon.incrementCounter();
        perfMon.stopAndPrintFinalResult();
    }
}
