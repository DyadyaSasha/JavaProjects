package employee;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestEmployeeDetails {

    EmpBusinessLogic logic = new EmpBusinessLogic();
    EmployeeDetails employee = new EmployeeDetails();


    @BeforeClass
    public void beforeTest(){

        employee.setName("Rajeev");
        employee.setAge(25);
        employee.setMonthlySalary(8000);

    }

//    игнорируем данный метод теста
    @Test(enabled = false)
    public void testCalculateAppriasal(){

        double appraisal = logic.calculateAppraisal(employee);
        assertEquals(appraisal, 500, 0.0, "500");

    }

    @Test
    public void testCalculateYearlySalary(){

        double salary = logic.calculateYearlySalary(employee);
        assertEquals(salary, 96000, 0.0, "8000");

    }

}
