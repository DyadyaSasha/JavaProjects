package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class JsoupTester {
    public static void main(String[] args) throws IOException, URISyntaxException {

        String html = "<html><head><title>Sample Title</title></head>"
                + "<body><p>Sample Content</p></body></html>";
//      парсит html-строку и представляет в виде специального объекта, который удобно использовать в дальнейшем поиске
        Document document = Jsoup.parse(html);
//      вывод содержимого тега title
        System.out.println("Title: " + document.title());
//      поиск конкретный тегов
        for (Element element : document.getElementsByTag("p")){
//          вывод содержимого тега p
            System.out.println(element.text());
        }
        System.out.println("______");

//      берём элементы в теге body
        Element body = document.body();
        for (Element element : body.children()){
            System.out.println(element.text());
        }
        System.out.println("______");

//      берём данные из сайта
        document = Jsoup.connect("https://www.google.ru").get();
        for(Element element : document.getAllElements()){
            System.out.println(element.text());
        }
        System.out.println("______");

//      ищет файл в папке resources
        URL path = ClassLoader.getSystemResource("test.htm");
        File file = new File(path.toURI());
//      парсим файл с html
        document = Jsoup.parse(file, "UTF-8");
        for (Element element : document.getAllElements()){
            System.out.println(element.text());
        }
        System.out.println("______");

        html = "<html><head><title>Sample Title</title></head>"
                + "<body>"
                + "<p>Sample Content</p>"
                + "<div id='sampleDiv'><a href='www.google.com'>Google</a>"
                + "<h3><a>Sample</a><h3>"
                +"</div>"
                + "<div id='imageDiv' class='header'><img name='google' src='google.png' />"
                + "<img name='yahoo' src='yahoo.jpg' />"
                +"</div>"
                +"</body></html>";

        document = Jsoup.parse(html);
//      по аналогии с CSS селекторами(выберет с тегом a и с атрибутом href)
        Elements links = document.select("a[href]");
        for (Element link : links){
            System.out.println("Href(relative link): " + link.attr("href"));
            System.out.println("Text: " + link.text());
        }
//      выберет теги img с атрибутом src, значение которого оканчивается на .png
        Elements pngs = document.select("img[src$=.png]");
        for (Element element : pngs){
//           выводим значение атрибута name
            System.out.println("Name: " + element.attr("name"));
        }

//      берём первый(метод first()) элемент с тегом div, принадлежащий классу header(class='header')
        Element headerDiv = document.select("div.header").first();
//      выводим значение атрибута id эл-та
        System.out.println("Id: " + headerDiv.id());
//      выводим всё, что относится к найденному тегу и что внутри него в виде html
        System.out.println("Outer HTML: " + headerDiv.outerHtml());
//      выводим все внутренние теги найденного тега в виде html
        System.out.println("Inner HTML: " + headerDiv.html());

//      берём эл-т внутри тега "a", который следует после тега "h3"
        Elements sampleLinks = document.select("h3 > a");
        for(Element link : sampleLinks){
            System.out.println("Text: " + link.text());
        }

        System.out.println("______");

        html = "<p><a href='http://example.com/'"
                +" onclick='checkData()'>Link</a></p>";
        System.out.println("Before clean: " + html);
//      очищает html от вохможностей атаки межсайтового скриптинга
        html = Jsoup.clean(html, Whitelist.basic());
        System.out.println("After clean: " + html);
    }
}
