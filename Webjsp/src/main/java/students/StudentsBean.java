package students;

/**
 * JavaBean класс, сконструированные специальным образом согласно JavaBeans API спецификации
 * отличительные черты от обычного класса:
 * содержит конструктор без аргументов
 * должен быть сериализуемым
 * имеет поля, геттеры и сеттеры
 * не имеет методов логики
 */

public class StudentsBean implements java.io.Serializable {

    private String firstName;
    private String lastName;
    private int age;

    public StudentsBean(){}

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAge(){
        return age;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Integer не подойдёт, т.е. обертки над примитивами недопустимы, иначе будет ошибка при попытке вызова jsp страницы
     * @param age
     */
    public void setAge(int age){
        this.age = age;
    }
}