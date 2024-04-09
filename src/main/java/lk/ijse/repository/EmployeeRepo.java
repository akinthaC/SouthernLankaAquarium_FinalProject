package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static boolean save(Employee employee) throws SQLException {
       String sql = "INSERT INTO employee VALUES(?,?,?,?,?) ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getContact());
        pstm.setObject(4, employee.getNIC());
        pstm.setObject(5, employee.getAddress());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET empName = ?, contact = ?, NIC = ?, address = ? WHERE empId = ?";
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getContact());
        pstm.setObject(3, employee.getNIC());
        pstm.setObject(4, employee.getAddress());
        pstm.setObject(5, employee.getId());

        return pstm.executeUpdate() > 0;
    }

    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE empId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String empid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact=resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);
           ;
            Employee employee = new Employee(empid,name,contact,NIC,address);


            return employee;
        }

        return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE EmpId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);


            Employee employee = new Employee(id, name, contact, NIC, address);
            empList.add(employee);
        }
        return empList;


    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT empId FROM employee ORDER BY empId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String EMPID = resultSet.getString(1);
            return EMPID;
        }
        return null;
    }

}
