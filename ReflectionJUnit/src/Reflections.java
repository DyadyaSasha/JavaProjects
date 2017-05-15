import javax.xml.soap.SAAJResult;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public interface Reflections {

    public Object getfieldValueByName(Object object, String fieldName) throws NoSuchFieldException;

    public Set<String> getProtectedMehodNames(Class clazz);

    public Set<Method> getAllImpementedMethodsWithSupers(Class clazz);

    public List<Class> getExtendendsHierarchy(Class clazz);

    public Set<Class> getImplementedInterfaces(Class clazz);

    public List<Class> getThrownExceptions(Method mrthod);

    public String getFooFunctionResultForDefaultConstructedClass();

    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer ... integers);

    @SuppressWarnings("unused")
    public class SecretClass{

        private String text = null;

        private String secret = "secret";

        private SecretClass(){
            ;
        }

        public SecretClass(String text){
            this.text = text;
        }

        public void setSecret(String secret){
            this.secret = secret;
        }

        private String foo(String string, Integer ... integers){
            String s = "";
            /* Some text hidden : start */
            int in = 0;
            if (integers != null){
                for (Integer i : integers){
                    in += i;
                }
            }
            s += string + " - > " + in;
            /* Some text hidden : end */
            return s;
        }

        private String foo(){
            String s = "";
            /* Some text hidden : start */
            s += "abraKadabra";
            /* Some text hidden : end*/
            return s;
        }
    }
}
