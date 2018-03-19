import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * можно отнаследоваться от {@link TypeAdapter}, чтобы для конкретного типа написать свою реализацию сериализации и десириализации
 * объекта
 */

public class SerializationExamples{
    public static void main(String[] args) {

        /**
         * Сереализуем массивы
         */
        Gson gson = new Gson();
        int marks[] = new int[]{100,90,85};
        String[] names = {"Ram","Shyam","Mohan"};

        /**
         * Сериализуем
         */
        System.out.print("{");
        System.out.print("marks: " + gson.toJson(marks) + ", ");
        System.out.print("names: " + gson.toJson(names));
        System.out.println("}");

        /**
         * Десериализуем
         */
        marks = gson.fromJson("[100,90,85]", int[].class);
        names = gson.fromJson("[\"Ram\",\"Shyam\",\"Mohan\"]", String[].class);
        System.out.println("marks: " + Arrays.toString(marks));
        System.out.println("names: " + Arrays.toString(names));

        System.out.println();

        /**
         * !Коллекции!
         */
        Collection<Integer> marks1 = new ArrayList<>();
        marks1.add(100);
        marks1.add(90);
        marks1.add(85);

        /**
         * Сериализуем
         */
        System.out.print("{");
        System.out.print("marks: " + gson.toJson(marks1));
        System.out.println("}");

        /**
         * Десериализуем
         */
        /**
         * так как при сериализации теряется информация о generic типах, то чтобы десириализовать в нужный generic тип(коллекция - generic тип) нужно указать его тип в TypeToken
         * в данном случае указываем, что json объект должен десириализоваться в коллекцию типа Integer
         */
        Type listType = new TypeToken<Collection<Integer>>(){}.getType();
        marks1 = gson.fromJson("[100,90,85]", listType);
        System.out.println("marks: " + marks1);

        System.out.println();


        /**
         * Сериализуем Generic типы
         */
        Shape<Circle> shape = new Shape<>();
        Circle circle = new Circle(5.0);
        shape.setShape(circle);

        Type shapeType = new TypeToken<Shape<Circle>>(){}.getType();

        String jsonString = gson.toJson(shape, shapeType);
        System.out.println(jsonString);

        /**
         * generic тип не увидит
         */
        Shape shape1 = gson.fromJson(jsonString, Shape.class);
        System.out.println(shape1.getShape().getClass());
        System.out.println(shape1.getShape().toString());
        System.out.println(shape1.getArea());

        /**
         * увидит generic тип, так как указан {@link TypeToken}
         */
        shape1 = gson.fromJson(jsonString, shapeType);
        System.out.println(shape1.getShape().getClass());
        System.out.println(shape1.getShape().toString());
        System.out.println(shape1.getArea());




    }
}

class Shape <T>{
    public T shape;

    public void setShape(T shape){
        this.shape = shape;
    }

    public T getShape(){
        return shape;
    }

    public double getArea(){
        if(shape instanceof Circle){
            return ((Circle) shape).getArea();
        } else {
            return 0.0;
        }
    }

}

class Circle{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle";
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return radius*radius*3.14;
    }
}
