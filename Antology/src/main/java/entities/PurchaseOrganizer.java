package entities;

/**
 * Организатор закупки
 */
public class PurchaseOrganizer {

    /**
     * Наименование организации
     */
    private String organizationName;
    /**
     * Место нахождения
     */
    private String organizationLocation;
    /**
     * Почтовый адрес
     */
    private String postAddress;
    /**
     * Телефон
     */
    private String phoneNumber;
    /**
     * Адрес электронной почты
     */
    private String emailAddress;
    /**
     * Факс
     */
    private String fax;
    /**
     * Контактное лицо
     */
    private String contactPerson;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationLocation() {
        return organizationLocation;
    }

    public void setOrganizationLocation(String organizationLocation) {
        this.organizationLocation = organizationLocation;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "Наименование организации: " + organizationName + " Место нахождения: " + organizationLocation +
                " Почтовый адрес: " + postAddress + " Телефон: " + phoneNumber + " Адрес электронной почты: " +
                emailAddress + " Факс: " + fax + " Контактное лицо: " + contactPerson;
    }
}
