package data;

import javax.persistence.*;

@Entity
@Table
/** создаём свой именнованный запрос с параметром id (e.eid = :id)*/
@NamedQuery(query =  "SELECT e from Employee  e" +
        " WHERE e.eid = :id", name = "find employee by id")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;

    /**
     над этими полями по
     умолчанию висит аннотация @Basic
    */
    private String ename;
    private double salary;
    private String deg;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString(){
        return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", deg=" + deg + "]";
    }

}
