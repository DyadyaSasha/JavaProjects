package stockservice;

import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortfolioTester {

    Portfolio portfolio;
    StockService stockService;

    public static void main(String[] args) {
        PortfolioTester tester = new PortfolioTester();
        tester.setUp();
        System.out.println(tester.testMarketValue() ? "pass" : "fail");
    }

    public void setUp(){
        portfolio = new Portfolio();
//      создаём заглушку для интерфейса
        stockService = EasyMock.createMock(StockService.class);
        portfolio.setStockService(stockService);
    }

    public boolean testMarketValue(){

        List<Stock> stocks = new ArrayList<>();

        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        portfolio.setStocks(stocks);
//      прописываем поведение в методах заглушки(при указанном параметре вернуть указанное значение)
        EasyMock.expect(stockService.getPrice(googleStock)).andReturn(50.00);
        EasyMock.expect(stockService.getPrice(microsoftStock)).andReturn(1000.00);

//      активирует заглушку(без этого метода код будет возвращать ошибку)
        EasyMock.replay(stockService);

        return portfolio.getMarketValue() == 100500.0;
    }
}
