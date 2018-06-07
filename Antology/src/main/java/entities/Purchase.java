package entities;

import java.util.List;

/**
 * Сущность закупки
 */
public class Purchase {

    /**
     * Наименование закупки
     */
    private String name;

    /**
     * Общие сведения о закупке
     */
    private InformationAboutPurchase about;
    /**
     * Организатор закупки
     */
    private PurchaseOrganizer purchaseOrganizer;
    /**
     * Лоты
     */
    private List<Lot> lots;


    public Purchase(){}

    public Purchase(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InformationAboutPurchase getAbout() {
        return about;
    }

    public void setAbout(InformationAboutPurchase about) {
        this.about = about;
    }

    public PurchaseOrganizer getPurchaseOrganizer() {
        return purchaseOrganizer;
    }

    public void setPurchaseOrganizer(PurchaseOrganizer purchaseOrganizer) {
        this.purchaseOrganizer = purchaseOrganizer;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Lot lot : lots){
            sb.append(lot.toString() + "\n");
        }
        return "*****************\n" + getName() + "\n" + about.toString() + "\n" + purchaseOrganizer.toString() + "\n" + sb.toString() + "\n\n";
    }
}
