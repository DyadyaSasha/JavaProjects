package inheritance.tableperclass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NonTeachingStaffTPC")
@DiscriminatorValue(value = "NS")
public class NonTeachingStaff extends Staff{

    private String areaexpertise;

    public NonTeachingStaff(){}

    public NonTeachingStaff(int sid, String sname, String areaexpertise) {
        super(sid, sname);
        this.areaexpertise = areaexpertise;
    }

    public String getAreaexpertise() {
        return areaexpertise;
    }

    public void setAreaexpertise(String areaexpertise) {
        this.areaexpertise = areaexpertise;
    }
}
