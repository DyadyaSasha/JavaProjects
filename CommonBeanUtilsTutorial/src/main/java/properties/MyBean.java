package properties;

import java.util.ArrayList;
import java.util.List;

public class MyBean {

    private SubBean subBean;
    private String stringProp;
    private float floatProp;
    /**
     * аннотация отключает предупреждение компилятора
     * в аргументе указывается, сообщения какого типа нужно отключить
     */
    @SuppressWarnings("rawtypes")
    private List listProp = new ArrayList();

    public void setStringProp(String stringProp) { this.stringProp = stringProp; }
    public String getStringProp() { return this.stringProp; }

    public void setFloatProp(float floatProp) { this.floatProp = floatProp; }
    public float getFloatProp() { return this.floatProp; }

    public void setListProp(List listProp) { this.listProp = listProp; }
    public List getListProp() { return this.listProp; }

    public SubBean getSubBean() {
        return subBean;
    }

    public void setSubBean(SubBean subBean) {
        this.subBean = subBean;
    }
}
