package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Supplier;
import lk.ijse.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,supplier.getId());
        pstm.setObject(2,supplier.getName());
        pstm.setObject(3,supplier.getContact());
        pstm.setObject(4,supplier.getNIC());
        pstm.setObject(5,supplier.getAddress());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supName = ? , contact = ?, NIC = ?, address = ? WHERE supId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1,supplier.getName());
        pstm.setObject(2,supplier.getContact());
        pstm.setObject(3,supplier.getNIC());
        pstm.setObject(4,supplier.getAddress());
        pstm.setObject(5,supplier.getId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);


            Supplier supplier = new Supplier(id, name, contact, NIC, address);
            supList.add(supplier);
        }
        return supList;
    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT supId FROM supplier ORDER BY supId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String supID = resultSet.getString(1);
            return supID;
        }
        return null;
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact=resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);

            Supplier supplier= new Supplier(supid,name,contact,NIC,address);


            return supplier;
        }

        return null;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT supId FROM supplier";
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

    public static String getId(String value) throws SQLException {
        String sql = "SELECT supId FROM supplier WHERE supId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, value);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supID = resultSet.getString(1);
            return supID;
        }
        return null;
    }

    public static List<String> searchNIC() throws SQLException {
        String sql = "SELECT NIC   FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String Nic = resultSet.getString(1);
            idList.add(Nic);
        }
        return idList;
    }

    public static Supplier searchByNIC(String nic) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE NIC = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, nic);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String SupName = resultSet.getString(2);
            String Contact = resultSet.getString(3);
            String Nic = resultSet.getString(3);
            String address = resultSet.getString(3);

            Supplier supplier =new Supplier(supId, SupName,Contact,Nic,address);


            return supplier;
        }

        return null;
    }
}
