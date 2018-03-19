package specs;

import base.Greeting;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class GreetingFixture {

    Greeting greeting = new Greeting();

    public String getGreeting(String name) {
        return greeting.getGreeting(name);
    }

}
