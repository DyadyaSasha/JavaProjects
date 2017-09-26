package caching;

import com.google.common.base.MoreObjects;

public class Employee {

    private String name;
    private String dept;
    private String emplD;

    public Employee(String name, String dept, String emplD) {
        this.name = name;
        this.dept = dept;
        this.emplD = emplD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmplD() {
        return emplD;
    }

    public void setEmplD(String emplD) {
        this.emplD = emplD;
    }

    @Override
    public String toString() {
        /**
         * {@link MoreObjects} представляет больше метдов, которых
         * нет в {@link com.google.common.base.Objects}
         * */
        return MoreObjects.toStringHelper(Employee.class)
                .add("Name", name)
                .add("Department", dept)
                .add("Emp ID", emplD).toString();
    }
}
