package parser;



import data.DataSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class JsoupParser implements Parser{

    private static final String BASE_URL = "http://www.tutorialspoint.com";

    public List<DataSource> parse() {

        List<DataSource> list = new ArrayList<DataSource>();
        DataSource ds;

        try {
            Document doc = Jsoup.connect(BASE_URL).get();

            Elements elements = doc.body().select("li>a[title]");

            for(Element element : elements){
                if (element.attributes().size() == 2){

                    ds = new DataSource();
                    
                    ds.setHref(BASE_URL + element.attr("href"));
                    ds.setTitle(element.attr("title"));

//                    for (Iterator<Attribute> iterator = element.attributes().iterator(); iterator.hasNext();){
//                        Attribute attr = iterator.next();
//                        if (attr.getKey().equals("href")){
//                            ds.setHref(BASE_URL + attr.getValue());
//                        } else if (attr.getKey().equals("title")){
//                            ds.setTitle(attr.getValue());
//                        }
//                    }

                    list.add(ds);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
