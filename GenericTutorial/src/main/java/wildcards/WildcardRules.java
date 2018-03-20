package wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * Правила использования wildcards:
 * если метод читает данные из аргумента, нужно использовать ? extends T
 * если метод передаёт данный в аргумент, нужно использовать ? super T
 * если метод и читает и передаёт данные в аргумент, то wildcard нельзя применять
 * статьи: https://stackoverflow.com/questions/1292109/explanation-of-the-get-put-principle
 *         https://habrahabr.ru/post/207360/
 */
public class WildcardRules {

    /**
     * так как в методе нужно читать данные из аргумента catList, то используем extends
     */
    public static void deleteCat(List<? extends Cat> catList, Cat cat){
        catList.remove(cat);
        System.out.println("Cat Removed");
    }

    /**
     * так как в методе передаются данные в catList, то нужно использовать super
     */
    public static void addCat(List<? super RedCat> catList){
        catList.add(new RedCat("Red Cat"));
        System.out.println("Cat Added");
    }

    /**
     * здесь неважен тип аргумента, поэтому используем просто ?
     */
    public static void printAll(List<?> list){
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Animal> animalList= new ArrayList<>();
        List<RedCat> redCatList= new ArrayList<>();

        addCat(animalList);

        addCat(redCatList);
        addCat(redCatList);


        printAll(animalList);
        printAll(redCatList);

        Cat cat = redCatList.get(0);

        deleteCat(redCatList, cat);
        printAll(redCatList);

    }

}

class Animal {
    String name;
    Animal(String name){
        this.name = name;
    }
    public String toString(){
        return name;
    }
}
class Cat extends Animal {
    Cat(String name){
        super(name);
    }
}
class RedCat extends Cat {
    RedCat(String name){
        super(name);
    }
}
class Dog extends Animal {
    Dog(String name){
        super(name);
    }
}