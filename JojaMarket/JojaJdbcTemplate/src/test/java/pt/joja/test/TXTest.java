package pt.joja.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pt.joja.bean.Employee;
import pt.joja.dao.EmployeeDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TXTest {

    private ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private JdbcTemplate jdbcTemplate = ioc.getBean("jdbcTemplate", JdbcTemplate.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = ioc.getBean("namedParameterJdbcTemplate", NamedParameterJdbcTemplate.class);

    @Test
    public void test01() throws SQLException {
        DataSource dataSource = ioc.getBean("dataSource", DataSource.class);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }

    @Test
    public void test02() throws SQLException {
        System.out.println(jdbcTemplate);
    }

    /**
     * 更新一个员工数据
     */
    @Test
    public void test03() {
        String sql = "update employee set salary = ? where emp_id = ?";
        int updateResult = jdbcTemplate.update(sql, new Object[]{1300.00, 5});
        System.out.println(updateResult);
    }

    /**
     * 批量插入
     */
    @Test
    public void test04() {
        String sql = "insert into employee(emp_name, salary) values(?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{
                "张三", 998.35
        });
        batchArgs.add(new Object[]{
                "小花", 9225.35
        });
        batchArgs.add(new Object[]{
                "毛蛋", 5225.35
        });
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 查询并包装为bean
     */
    @Test
    public void test05() {
        String sql = "select emp_id as empId, emp_name as empName, salary from employee where emp_id = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), 5);
        System.out.println(employee);
    }

    /**
     * 查询并封装为List
     */
    @Test
    public void test06() {
        String sql = "select emp_id as empId, emp_name as empName, salary from employee where salary > ?";
        List<Employee> employeeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), 4000);
        System.out.println(employeeList);
    }

    /**
     * 查询最大salary
     */
    @Test
    public void test07() {
        String sql = "select max(salary) as maxSalary from employee";
        Double result = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println(result);
    }

    /**
     * 使用带有具名参数的SQL插入一条数
     */
    @Test
    public void test08() {
        String sql = "insert into employee(emp_name, salary) values(:_emp_name, :_salary)";
        Map<String, Object> param = new HashMap<>();
        param.put("_emp_name", "小刘");
        param.put("_salary", 5000.35);
        int result = namedParameterJdbcTemplate.update(sql, param);
        System.out.println(result);
    }

    /**
     * 以SqlParameterSource形式传入参数
     */
    @Test
    public void test09() {
        String sql = "insert into employee(emp_name, salary) values(:empName, :salary)";
        Employee employee = new Employee();
        employee.setEmpName("二狗");
        employee.setSalary(998.98);

        int result = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));

        System.out.println(result);
    }

    @Test
    public void test10() {
        EmployeeDao employeeDao = ioc.getBean("employeeDao", EmployeeDao.class);

        Employee employee = new Employee();
        employee.setEmpName("三狗");
        employee.setSalary(1598.98);

        employeeDao.saveEmployee(employee);
    }
}
