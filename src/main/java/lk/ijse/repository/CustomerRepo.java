package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customer.getId());
        pstm.setObject(2,customer.getName());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getContact());
        pstm.setObject(5,customer.getNIC());
        pstm.setObject(6,customer.getType());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET cusName = ? , contact = ?, NIC = ?, address = ?, type = ? WHERE cusId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1,customer.getName());
        pstm.setObject(2,customer.getAddress());
        pstm.setObject(3,customer.getContact());
        pstm.setObject(4,customer.getNIC());
        pstm.setObject(5,customer.getType());
        pstm.setObject(6,customer.getId());
        return pstm.executeUpdate() > 0;
    }

    public static Customer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE cusId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(5);
            String NIC = resultSet.getString(3);
            String address = resultSet.getString(4);
            String type = resultSet.getString(6);

            Customer customer = new Customer(cusId,name,contact,NIC,address,type);
            return customer;

        }
        return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE cusId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);
            String type = resultSet.getString(6);

            Customer customer = new Customer(id, name,contact,NIC,address,type);
            cusList.add(customer);
        }
        return cusList;
    }


    public static String getCurrentId() throws SQLException {
        String sql = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String CUSID = resultSet.getString(1);
            return CUSID;
        }
        return null;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT cusId FROM customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
