package entities;

/**
 * Сущность лота
 */
public class Lot {

    /**
     * Номер лота(ссылка на лот)
     */
    private String lotNumber;
    /**
     * Наименование
     */
    private String lotName;
    /**
     * Начальная цена (руб.)
     */
    private String startingPrice;
    /**
     * Статус лота
     */
    private String status;

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(String startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Номер лота(ссылка на лот): " + lotNumber + " Наименование: " +  lotName + " Начальная цена (руб.): "
                + startingPrice + " Статус лота: " + status;
    }
}
