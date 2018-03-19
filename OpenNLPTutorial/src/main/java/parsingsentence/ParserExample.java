package parsingsentence;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ParserExample {
    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream(System.getProperty("user.dir") + "\\lib\\en-parser-chunking.bin");
        ParserModel model = new ParserModel(is);
        Parser parser = ParserFactory.create(model);
        String sentence = "Tutorialspoint is the largest tutorial library.";
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
        for(Parse parse: topParses){
            parse.show();
        }

    }
}
