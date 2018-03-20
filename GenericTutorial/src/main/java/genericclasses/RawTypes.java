package genericclasses;

public class RawTypes {
    public static void main(String[] args) {

        Box<Integer> box = new Box<>();
        box.add(10);
        System.out.printf("Integer Value :%d\n", box.get());

        /**
         * raw(сырой) объект, в нём будут храниться объекты типа {@link Object}(так как не указан generic тип-параметр) независисо от того, кокой объект будет передан
         */
        Box rawBox = new Box();
        rawBox = box;
        System.out.printf("Integer Value :%d\n", rawBox.get());

        /**
         * будет предупреждение от компилятора: непроверяемый вызов методв=а
         */
        rawBox.add(Integer.valueOf(10));
        System.out.printf("Integer Value :%d\n", box.get());

        /**
         * будет предупреждение от компилятора: непроверяемое приведение типа
         */
        box = rawBox;
        System.out.printf("Integer Value :%d\n", box.get());
    }
}
