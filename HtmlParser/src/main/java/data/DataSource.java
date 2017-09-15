package data;

import java.io.Serializable;

public class DataSource implements Serializable{

    private String href;
    private String title;

    public DataSource(){}

    public DataSource(String href, String title){
        this.href = href;
        this.title = title;
    }

    public void setHref(String href){
        this.href = href;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getHref(){
        return href;
    }

    public String getTitle(){
        return title;
    }
    
}
