import com.google.common.base.Objects;

public class Student {

    private String firstName;
    private String lastName;
    private int rollNo;
    private String className;

    public Student(String firstName, String lastName, int rollNo, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.className = className;
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

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student) || o == null) {return false;}

        Student student = (Student) o;

        /**
         * используем метод equals() из класса Objects
         * Objects.equal("test", "test") == true
         * Objects.equal("test", null) == false
         * Objects.equal(null, "test") == false
         * Objects.equal(null, null) == true
         * */
        return Objects.equal(firstName, student.firstName)
                && Objects.equal(lastName, student.lastName)
                && Objects.equal(rollNo, student.rollNo)
                && Objects.equal(className, student.className);
    }

    @Override
    public int hashCode() {
        /**
         * используем метод hashCode() из класса Objects
         * в параметрах метода указываем поля,
         * по которым будет высчитываться хешкод
         * */
        return Objects.hashCode(className, rollNo);
    }
}
