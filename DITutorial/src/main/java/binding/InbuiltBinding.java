package binding;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.logging.Logger;

/**
 * Guice поддерживает встроенные связывания, например для класса {@link Logger}
 * это позволяет не писать в методе configure() связывания
 */
public class InbuiltBinding {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule5());
        TextEditor5 editor = injector.getInstance(TextEditor5.class);
        editor.makeSpellCheck();
    }
}


class TextEditor5{

    private Logger logger;

    @Inject
    public TextEditor5(Logger logger){
        this.logger = logger;
    }

    public void makeSpellCheck() {
        logger.info("In TextEditor.makeSpellCheck() method");
    }
}

class TextEditorModule5 extends AbstractModule{

    @Override
    protected void configure() {

    }
}