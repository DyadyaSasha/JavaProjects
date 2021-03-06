package ibatis;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import models.Employee;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

public class IBatisInsert {
    public static void main(String[] args) throws IOException, SQLException {
        SqlMapClient smc = null;
        try {
//      указываем файл конфигурации
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
//      указываем фреймворку объект с конфигурацией. Через этот объект будет происходить общение с БД
            smc = SqlMapClientBuilder.buildSqlMapClient(reader);
//      создаём таблицу, если она не существует
            smc.insert("Employee.createTableIfNotExist");
            Employee employee = new Employee("Zara", "Ali", 5000);
//      выполняем операцию insert(сам SQL код лежит в файле конфигурации) - сначала указываем namespace, затем через точку
//      id операции
            smc.insert("Employee.insert", employee);
            int lastID = (int) smc.insert("Employee.insert", new Employee("Alex", "Vitsel", 10000));

//      выводим все записи из таблицы
            System.out.println("BEFORE UPDATE: ");
            List<Employee> employees = (List<Employee>) smc.queryForList("Employee.getAll", null);
            for (Employee e : employees) {
                System.out.println(e.getId() + " " + e.getFirst_name() + " " + e.getLast_name() + " " + e.getSalary());
            }

            Employee updateEmpl = new Employee();
            updateEmpl.setId(lastID);
            updateEmpl.setFirst_name("ROMA");
//      обновляем запись в таблице
            smc.update("Employee.update", updateEmpl);

            System.out.println("AFTER UPDATE: ");
            employees = (List<Employee>) smc.queryForList("Employee.getAll", null);
            for (Employee e : employees) {
                System.out.println(e.getId() + " " + e.getFirst_name() + " " + e.getLast_name() + " " + e.getSalary());
            }

            smc.delete("Employee.delete", lastID);
            System.out.println("AFTER DELETE: ");
            employees = (List<Employee>) smc.queryForList("Employee.getAll", null);
            for (Employee e : employees) {
                System.out.println(e.getId() + " " + e.getFirst_name() + " " + e.getLast_name() + " " + e.getSalary());
            }

//      используем ResultMap(обязательно должен возвращать только один эл-нт, иначе - ошибка)
            Employee e = (Employee) smc.queryForObject("Employee.useResultMap", "Zara");
            System.out.println("USING ResultMap:");
            System.out.println("ID:  " + e.getId());
            System.out.println("First Name:  " + e.getFirst_name());
            System.out.println("Last Name:  " + e.getLast_name());
            System.out.println("Salary:  " + e.getSalary());
//          используем dynamic SQL
            Employee employee1 = new Employee();
            employee1.setId(e.getId());
//          в запрос передаём объект, из которого берётся нужное поле и подставляется в WHERE
            List<Employee> e1 = (List<Employee>) smc.queryForList("Employee.findByID", employee1);
            e1.forEach(employee2 -> {
                System.out.println(employee2.getId());
                System.out.println(employee2.getFirst_name());
                System.out.println(employee2.getLast_name());
                System.out.println(employee2.getSalary());
            });

//
        } finally {
//          чистим всё в таблице
            smc.delete("Employee.deleteAll");

        }



    }
}
