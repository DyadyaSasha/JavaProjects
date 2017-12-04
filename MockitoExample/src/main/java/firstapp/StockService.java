package firstapp;

/**
 * имплементации этого интерфеса в классе {@link Portfolio}
 * и в классе {@link Stock} нет, несмотря на это, метод
 * getPrice(Stock stock) вызвается в одном из методов класса {@link Stock}
 * данный метод будет реализован с помощью mock при тестировании
 */
public interface StockService{
    double getPrice(Stock stock);
}
