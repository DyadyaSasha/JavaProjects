package firstapp;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PortfolioTester {

    private Portfolio portfolio;
    private StockService stockService;

    public static void main(String[] args) {
        PortfolioTester tester = new PortfolioTester();
        tester.setUp();
        System.out.println(tester.testMarketValue() ?  "pass" : "fail");
    }

    private boolean testMarketValue() {

        List<Stock> stocks = new LinkedList<>();

        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        portfolio.setStocks(stocks);

        /**
         *  говорим Mockito, что при вызове конкретного метода, с конкретным параметром,
         *  псевдо-реализация должна вернуть конкретный результат(т.е. !имитируем! реальное поведение метода)
         */
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        double marketValue = portfolio.getMarketValue();
        return marketValue == 100500.0;
    }

    private void setUp() {
        portfolio = new Portfolio();
        /**
         * т.к. метод getPrice() интерфейса StockService нигде не реализован, мы говорим Mockito,
         * чтобы он создал заглушку для него, т.е. псевдо-реализацию, которая будет использоваться при тестировании
         */
        stockService = mock(StockService.class);

        /**
         * "вводим(inject)" заглушку(фейковую реализацию) в реальный объект"
         */
        portfolio.setStockService(stockService);
    }
}
