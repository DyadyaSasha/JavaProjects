package relation.data.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Clas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    private String name;

    @ManyToMany(targetEntity = Teacher.class)
    private Set teacherSet;

    public Clas(){}

    public Clas(int cid, String name, Set teacherSet) {
        this.cid = cid;
        this.name = name;
        this.teacherSet = teacherSet;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set teacherSet) {
        this.teacherSet = teacherSet;
    }
}
