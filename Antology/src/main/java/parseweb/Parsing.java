package parseweb;

import entities.InformationAboutPurchase;
import entities.Lot;
import entities.Purchase;
import entities.PurchaseOrganizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parsing {

    private final String baseUrl = "http://www.zakupki.rosatom.ru/Web.aspx?node=currentorders&tso=1&tsl=1&sbflag=0&ostate=F&cust=%D0%90%D0%BA%D1%86%D0%B8%D0%BE%D0%BD%D0%B5%D1%80%D0%BD%D0%BE%D0%B5+%D0%BE%D0%B1%D1%89%D0%B5%D1%81%D1%82%D0%B2%D0%BE+%22%D0%90%D1%82%D0%BE%D0%BC%D1%81%D1%82%D1%80%D0%BE%D0%B9%D1%8D%D0%BA%D1%81%D0%BF%D0%BE%D1%80%D1%82%22&customerid=591&pform=a";

    public List<Purchase> parse(String url) throws IOException {
        List<Purchase> purchases = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        for (Element element : doc.select("#table-lots-list > table > tbody")){
            for (Element element1 : element.getElementsByClass("description")){
                String purchaseUrl = element1.getElementsByTag("a").attr("href");
                if (purchaseUrl.startsWith("/")) {
                    Document document = Jsoup.connect("http://www.zakupki.rosatom.ru" + purchaseUrl).get();

                    Purchase purchase = new Purchase(document.select("#form1 > div.wrapper > div.body.body-inner > div.body-column.body-column-right.content > h1").text());

                    InformationAboutPurchase aboutPurchase = new InformationAboutPurchase();
                    for (Element tbody1 : document.select("#table_01 > tbody")){
                        for(Element tr : tbody1.getElementsByTag("tr")){
                             switch (tr.getElementsByTag("td").first().text().trim()){
                                 case "Номер закупки":
                                     aboutPurchase.setPurchaseNumber(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Наименование закупки":
                                     aboutPurchase.setPurchaseName(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Статус":
                                     aboutPurchase.setStatus(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Способ закупки":
                                     aboutPurchase.setPurchaseMethod(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Форма торгов":
                                     aboutPurchase.setBiddingForm(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Количество этапов закупки":
                                     aboutPurchase.setNumberOfStagesPurchase(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Наименование источника финансирования":
                                     aboutPurchase.setFinancingSource(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Начальная (максимальная) цена договора в рублях РФ":
                                     aboutPurchase.setInitialPrice(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Часовой пояс закупки":
                                     aboutPurchase.setTimeZone(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Номер закупки на ООС":
                                     aboutPurchase.setPurchaseNumberForProtection(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Электронная торговая площадка":
                                     aboutPurchase.setMarketplace(tr.getElementsByTag("td").last().text());
                                     break;
                                 case "Ссылка на закупку на ЭТП":
                                     aboutPurchase.setPurchaseLink(tr.getElementsByTag("td").last().text());
                                 default:
                                     break;
                             }
                        }
                    }

                    PurchaseOrganizer organizer = new PurchaseOrganizer();
                    for(Element tbody2 : document.select("#table_02 > tbody")){
                        for(Element tr : tbody2.getElementsByTag("tr")){
                            switch (tr.getElementsByTag("td").first().text().trim()){
                                case "Наименование организации":
                                    organizer.setOrganizationName(tr.getElementsByTag("td").last().text());
                                    break;
                                case "Место нахождения":
                                    organizer.setOrganizationLocation(tr.getElementsByTag("td").last().text());
                                    break;
                                case "Почтовый адрес":
                                    organizer.setPostAddress(tr.getElementsByTag("td").last().text());
                                    break;
                                case "Телефон":
                                    organizer.setPhoneNumber(tr.getElementsByTag("td").last().text());
                                    break;
                                case "Адрес электронной почты":
                                    organizer.setEmailAddress(tr.getElementsByTag("td").last().text());
                                    break;
                                case "Факс":
                                    organizer.setFax(tr.getElementsByTag("td").last().text());
                                    break;
                                case "Контактное лицо":
                                    organizer.setContactPerson(tr.getElementsByTag("td").last().text());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    List<Lot> lots = new ArrayList<>();
                    for(Element l : document.select("#table_07 > table > tbody")){
                        for(Element tr : l.getElementsByTag("tr")){
                            int count = 1;
                            Lot lot = new Lot();
                            for(Element td : tr.getElementsByTag("td")){
                                if (count == 1) {
                                    lot.setLotNumber("http://www.zakupki.rosatom.ru" + td.getElementsByTag("p").first().getElementsByTag("a").first().attr("href"));
                                    count++;
                                } else if (count == 2){
                                    lot.setLotName(td.getElementsByTag("p").first().text());
                                    count++;
                                } else if (count == 3){
                                    lot.setStartingPrice(td.getElementsByTag("p").first().text());
                                    count++;
                                } else if (count == 4){
                                    lot.setStatus(td.getElementsByTag("p").first().text());
                                    count++;
                                }
                            }
                            lots.add(lot);
                        }

                    }
                    purchase.setAbout(aboutPurchase);
                    purchase.setPurchaseOrganizer(organizer);
                    purchase.setLots(lots);
                    purchases.add(purchase);
                }
            }
        }
        return purchases;
    }

}
