package inheritance.joined;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "StaffJS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;

    private String sname;

    public Staff(int sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

    public Staff(){}

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

}