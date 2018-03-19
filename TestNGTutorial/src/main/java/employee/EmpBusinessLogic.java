package employee;

public class EmpBusinessLogic {

    public double calculateYearlySalary(EmployeeDetails employeeDetails){
        return employeeDetails.getMonthlySalary()*12;
    }

    public double calculateAppraisal(EmployeeDetails employeeDetails){

//      при всех возможных случаях i инициализируется до использования
        double i;

        if(employeeDetails.getMonthlySalary() < 10000){
            i = 500;
        } else {
            i = 1000;
        }

        return i;
    }


}
