package relation.data.onetomany;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
/**
 * один департамент может иметь разных employee, поэтому некоторые записи департаментов будут
 * дублироваться и различаться только колонкой, связанной с employee, то есть
 * один employee может работать в нескольких department, но в каждом department
 * только по одному сотруднику
 * !!! В данной связи один department может иметь только одного employee!!!
 * */
    @OneToMany(targetEntity = DepEmployee.class)
    private List employeeList;

    public Department(){}

    public Department(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List employeeList) {
        this.employeeList = employeeList;
    }
}
