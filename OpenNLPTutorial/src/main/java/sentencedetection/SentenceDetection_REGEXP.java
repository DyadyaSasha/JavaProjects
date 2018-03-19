package sentencedetection;

public class SentenceDetection_REGEXP {
    public static void main(String[] args) {
        String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies";

        String regexp = "[.?!]";
        /**
         * разбиваем предложения по регулярному выражению
         */
        String[] splitString = sentence.split(regexp);
        for (String string: splitString){
            System.out.println(string);
        }
    }
}
