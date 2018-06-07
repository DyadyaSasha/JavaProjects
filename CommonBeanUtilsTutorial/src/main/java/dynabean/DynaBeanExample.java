package dynabean;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

public class DynaBeanExample {

    private final String NR_OF_WHEELS = "numberOfWheels";

    private void runExample(){
        /**
         * {@link DynaClass} - симуляция класса {@link Class} для объектов типа {@link DynaBean}
         * {@link BasicDynaClass} - конструирует DynaClass c конкретным именем, с реализующим классом для новых объектов, с конкретными полями(свойствами)
         */
        DynaClass dynaClass = new BasicDynaClass("Car", null,
                /**
                 * определяем поля(имя и тип) в классе
                 */
                new DynaProperty[]{new DynaProperty(NR_OF_WHEELS, Integer.TYPE)});

        try {
            /**
             * {@link DynaBean} представляет собой объект, имена и значения полей которого могут динамически изменяться
             * здесь возвращается объект определённого выше {@link DynaClass}
             */
            DynaBean car = dynaClass.newInstance();
            /**
             * присваиваем значение полю
             */
            car.set(NR_OF_WHEELS, 4);
            System.out.println("Number of wheels: " + car.get(NR_OF_WHEELS));
            System.out.println("DynaBean is instance of DynaClass: " + car.getDynaClass().getName());
        } catch (IllegalAccessException | InstantiationException e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        DynaBeanExample ac = new DynaBeanExample();
        ac.runExample();
    }
}
