package entities;

import javax.persistence.*;


@Entity
@Table(name = "ANNOEMPL")
public class AnnotatedEmployee {

    @Id //если @GeneratedValue не указана, то будет сгенерирована пододящяя стратегия генерации ключа автоматически
    @GeneratedValue //default - strategy=GenerationType.AUTO
    @Column(name = "Id", nullable = false)
    private int id;


    @Column(name = "First_name", nullable = false)
    private String firstName;

    @Column(name = "Last_name", nullable = false)
    private String lastName;

    @Column(name = "Salary", nullable = false)
    private int salary;

    public AnnotatedEmployee(){}

    public AnnotatedEmployee(String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


}
