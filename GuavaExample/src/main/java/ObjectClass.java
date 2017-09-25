import com.google.common.base.MoreObjects;

/**
 * Object представляет вспомогательные методы
 * для объектов, такие как equals(), hashCode() и другие
 * */
public class ObjectClass {
    public static void main(String[] args) {

        Student student1 = new Student("Mahesh", "Parashar", 1, "VI");
        Student student2 = new Student("Suresh", null, 3, null);

        System.out.println(student1.equals(student2));
        System.out.println(student1.hashCode());

        System.out.println(
                MoreObjects.toStringHelper(student1)
                .add("Name", student1.getFirstName() + " " + student1.getLastName())
                .add("Class", student1.getClassName())
                .add("Roll No", student1.getRollNo())
                .toString()
        );
    }
}
