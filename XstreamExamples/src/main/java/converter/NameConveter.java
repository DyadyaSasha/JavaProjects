package converter;

import com.thoughtworks.xstream.converters.SingleValueConverter;

/**
 * В классе, реализующим интерфейс {@link SingleValueConverter}
 * можно прописать как именно будут сериализоваться/десириализоваться
 * классы
 * Данный класс сериализует сериализуемый объект в строку({@link SingleValueConverter})
 * */
public class NameConveter implements SingleValueConverter {


    /**
     * десириализация
     * */
    @Override
    public Object fromString(String name){
        String[] nameParts = name.split(",");
        return new Name(nameParts[0], nameParts[1]);
    }

    /**
     * сериализция
     * */
    @Override
    public String toString(Object name) {
        return ((Name) name).getFirstName() + "," + ((Name) name).getLastName();
    }


    /**
     * проверяет, поддерживает ли объект указанную сериализацию
     * */
    @Override
    public boolean canConvert(Class type){
        return type.equals(Name.class);
    }
}
