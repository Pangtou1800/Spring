package pt.joja.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pt.joja.bean.Employee;

@Repository
public class EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveEmployee(Employee employee) {
        String sql = "insert into employee(emp_name, salary) values(?, ?)";

        int result = jdbcTemplate.update(sql, employee.getEmpName(), employee.getSalary());
        System.out.println(result);
    }
}
