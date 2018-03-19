package sentencedetection;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SentenceDetectionME {
    public static void main(String[] args) throws IOException {

        String sentence = "Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies";

        /**
         * для ***ME всегда нужна модель, здесь определяем её: файл en-sent.bin
         */
        InputStream is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-sent.bin");
        SentenceModel model = new SentenceModel(is);

        SentenceDetectorME detector = new SentenceDetectorME(model);

        /**
         * определяем(находим) предложения
         */
        String[] sentences = detector.sentDetect(sentence);

        for(String sent: sentences){
            System.out.println(sent);
        }

        /**
         * определяем начало и конец найденных предложений
         */
        Span[] spans = detector.sentPosDetect(sentence);
        for(Span span: spans){
            System.out.println(span);
        }

        /**
         * определяет вероятности предложений
         */
        double[] probs = detector.getSentenceProbabilities();
        for(double prob: probs){
            System.out.println(prob);
        }

    }
}
