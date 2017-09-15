package relation.data.onetoone;

import javax.persistence.*;

@Entity
public class DepEmployee {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private int eid;

    private String ename;
    private double salary;
    private String deg;

    @OneToOne
    private Department department;

    public DepEmployee(int eid, String ename, double salary, String deg) {
        this.eid = eid;
        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
    }

    public DepEmployee( ) {
    }

    public int getEid( ) {
        return eid;
    }

    public void setEid(int eid)  {
        this.eid = eid;
    }

    public String getEname( ) {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary( ) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg( ) {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
