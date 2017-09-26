package collectionsutilities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

public class TableInterface {
    public static void main(String[] args) {

        /**
         * Table<row, column, value> - словарь с двумя ключами,
         * ссылающимися на одно значение(т.е. таблица)
         * */
        Table<String, String, String> employeeTable = HashBasedTable.create();

        employeeTable.put("IBM", "101","Mahesh");
        employeeTable.put("IBM", "102","Ramesh");
        employeeTable.put("IBM", "103","Suresh");

        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");

        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");

        /**
         * map<column, value>, у которого row в Table был 'IBM'
         * */
        Map<String,String> ibmEmployees =  employeeTable.row("IBM");
        System.out.println("List of IBM Employees");
//        ibmEmployees.forEach((c,v) -> {
//            System.out.println("Emp Id: " + c + ", Name: " + v);
//        });
        for(Map.Entry<String,String> entry : ibmEmployees.entrySet()){
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        /**
         * множество всех значений row из map<row,column,value>
         * */
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        employers.forEach(e -> System.out.print(e + " "));
        System.out.println();

        /**
         * map<row, value>, у которого column в Table был '102'
         * */
        Map<String,String> employerMap = employeeTable.column("102");
        employerMap.forEach((r,v) ->
                System.out.println("Employer: " + r + ", Name: " + v));


    }

}