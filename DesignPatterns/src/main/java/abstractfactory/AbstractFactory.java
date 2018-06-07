package abstractfactory;

/**
 * класс абстрактной фабрики, от которой будут унаследованы фабрики для цвета и фигур
 */
public abstract class AbstractFactory {

    abstract Color getColor(String color);
    abstract Shape getShape(String shape);

}
