package entities;

/**
* Общие сведения о закупке
*/
public class InformationAboutPurchase {

    /**
     * Номер закупки
     */
    private String purchaseNumber;
    /**
     * Наименование закупки
     */
    private String purchaseName;
    /**
     * Статус
     */
    private String status;
    /**
     * Способ закупки
     */
    private String purchaseMethod;
    /**
     * Форма торгов
     */
    private String biddingForm;
    /**
     * Количество этапов закупки
     */
    private String numberOfStagesPurchase;
    /**
     * Наименование источника финансирования
     */
    private String financingSource;
    /**
     * Начальная (максимальная) цена договора в рублях РФ
     */
    private String initialPrice;
    /**
     * Часовой пояс закупки
     */
    private String timeZone;
    /**
     * Электронная торговая площадка
     */
    private String marketplace;
    /**
     * Ссылка на закупку на ЭТП
     */
    private String purchaseLink;
    /**
     * Номер закупки на ООС
     */
    private String purchaseNumberForProtection;

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setPurchaseMethod(String purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public String getBiddingForm() {
        return biddingForm;
    }

    public void setBiddingForm(String biddingForm) {
        this.biddingForm = biddingForm;
    }

    public String getNumberOfStagesPurchase() {
        return numberOfStagesPurchase;
    }

    public void setNumberOfStagesPurchase(String numberOfStagesPurchase) {
        this.numberOfStagesPurchase = numberOfStagesPurchase;
    }

    public String getFinancingSource() {
        return financingSource;
    }

    public void setFinancingSource(String financingSource) {
        this.financingSource = financingSource;
    }

    public String getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(String initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public String getPurchaseLink() {
        return purchaseLink;
    }

    public void setPurchaseLink(String purchaseLink) {
        this.purchaseLink = purchaseLink;
    }

    public String getPurchaseNumberForProtection() {
        return purchaseNumberForProtection;
    }

    public void setPurchaseNumberForProtection(String purchaseNumberForProtection) {
        this.purchaseNumberForProtection = purchaseNumberForProtection;
    }

    @Override
    public String toString(){
        return "Номер закупки: " + purchaseNumber +
                " Наименование закупки: " + purchaseName +
                " Статус: " + status +
                " Способ закупки: " + purchaseMethod +
                " Форма торгов: " + biddingForm +
                " Количество этапов закупки: " + numberOfStagesPurchase +
                " Наименование источника финансирования: " + financingSource +
                " Начальная (максимальная) цена договора в рублях РФ: " + initialPrice +
                " Часовой пояс закупки: " + timeZone +
                " Электронная торговая площадка: " + marketplace +
                " Ссылка на закупку на ЭТП: " + purchaseLink +
                " Номер закупки на ООС: " + purchaseNumberForProtection;
    }
}
