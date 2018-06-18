package annotationJSRConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

public class HelloWorld {

    private String message;

    /**
     * {@link Resource}, {@link PreDestroy}, {@link PostConstruct} - общие аннотации из JSR - 250(их не рекоммендуется использовать, лучше использовать инструменты Spring-а)
     * {@link Resource} - указывает имя бина в конфиге, который нужно внедрить
     * {@link PreDestroy} - аналогично destroy-method
     * {@link PostConstruct} - аналогично init-method
     * */
    @Resource(name = "spellChecker")
    private SpellChecker spellChecker;


    public SpellChecker getSpellChecker() {
        return spellChecker;
    }


    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }


    public void setMessage(String message){
        this.message  = message;
    }
    public String getMessage(){
        System.out.println("Your Message : " + message);
        return message;
    }


    @PostConstruct
    public void init(){
        System.out.println("Bean is going through init.");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Bean will destroy now.");
    }
}
