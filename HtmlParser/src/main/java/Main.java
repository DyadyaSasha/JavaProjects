import parser.JsoupParser;
import parser.Parser;
import jdbc.InMemorySaver;
import jdbc.InPersistentSaver;

public class Main {

    public static void main(String[] args) {

        Parser parser = new JsoupParser();
        InMemorySaver h2Saver = new InMemorySaver();
        h2Saver.save(parser.parse());
        InPersistentSaver mysqlSaver = new InPersistentSaver();
        mysqlSaver.save();

    }

}
