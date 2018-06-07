package nashornjs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Движок Nashorn, написанный на Java, позволяет исполнять Java Script код в Java среде и наоборот
 * (позволяет выполнять Java Script код в JVM -> команда: jjs script_name.js )
 */
public class JSFromJava {

    public static void main(String[] args) {

        /**
         * ScriptEngineManager позволяет выполнять JS код в Java
         */
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        /**
         * указываем конкретный движок, который будем использовать для выполнения JS кода в Java
         */
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Mahesh";
        Integer result = null;

        try {
            /**
             * указываем команды на JS, которые в тот же момент выполняются
             */
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }
}
