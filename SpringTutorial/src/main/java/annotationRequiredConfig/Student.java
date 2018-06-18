package annotationRequiredConfig;

import org.springframework.beans.factory.annotation.Required;

public class Student {

    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    /**
     * аннотация {@link Required} ставится на сеттеры полей класса и указывает, что
     * значение поля должно быть обязательно заполнено в XML файле конфигурации бинов,
     * иначе контейнер выбросит - BeanInitializationException
     */
    @Required
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }
}
