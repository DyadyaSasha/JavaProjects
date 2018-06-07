import entities.Purchase;
import parseweb.Parsing;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        // #form1 > div.wrapper > div.body.body-inner > div.body-column.body-column-right.content > div:nth-child(10) > div:nth-child(3) - число закупок
        List<Purchase> purchases = new Parsing().parse("http://www.zakupki.rosatom.ru/Web.aspx?node=currentorders&tso=1&tsl=1&sbflag=0&ostate=F&cust=%D0%90%D0%BA%D1%86%D0%B8%D0%BE%D0%BD%D0%B5%D1%80%D0%BD%D0%BE%D0%B5+%D0%BE%D0%B1%D1%89%D0%B5%D1%81%D1%82%D0%B2%D0%BE+%22%D0%90%D1%82%D0%BE%D0%BC%D1%81%D1%82%D1%80%D0%BE%D0%B9%D1%8D%D0%BA%D1%81%D0%BF%D0%BE%D1%80%D1%82%22&customerid=591&pform=a");
        purchases.forEach(System.out::println);
    }
}
