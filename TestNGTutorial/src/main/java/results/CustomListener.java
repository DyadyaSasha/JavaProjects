package results;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

// класс, перехватывающий события начала, конец, ошибки(неудачный тест), пропуски(тестов) и др. тестовых методов
public class CustomListener extends TestListenerAdapter{

    private int count;

    @Override
    public void onTestSuccess(ITestResult tr) {
       log(tr.getName() + "--Test method success\n");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName() + "--Test method failed\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName() + "--Test method skipped\n");
    }

    private void log(String string){
        System.out.println(string);
        if(++count % 40 == 0) System.out.println("");
    }
}
