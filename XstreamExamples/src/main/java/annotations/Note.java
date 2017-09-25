package annotations;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("note")
public class Note {
    private String title;
    private String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }
}
