package results;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;


// данный класс вызывается, когда весь suit из xml файла выполнен, и содержит полную информацию о результатах тестов
public class CustomReporter implements IReporter {


    /**
     * @param xmlSuites       список suit-ов, упомянутых в xml файле
     * @param suites          содержит информацию о suit, а именно: о пакете, классах, тестовых методах и их результатах после тестов
     * @param outputDirectory директория, в которую нужно сохранить результаты тестов
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                System.out.println("Passed tests for suite '" + suiteName +
                        "' is:" + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed tests for suite '" + suiteName +
                        "' is:" + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped tests for suite '" + suiteName +
                        "' is:" + tc.getSkippedTests().getAllResults().size());
            }
        }
    }

}
