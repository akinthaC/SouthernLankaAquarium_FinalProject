package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Accessories;
import lk.ijse.model.Fish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessoriesRepo {
    public static boolean save(Accessories accessories) throws SQLException {
        String sql = "INSERT INTO accessories VALUES(?,?,?,?,?,?) ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, accessories.getId());
        pstm.setObject(2, accessories.getSupid());
        pstm.setObject(3, accessories.getName());
        pstm.setObject(4, accessories.getQty());
        pstm.setObject(5, accessories.getNormalPrice());
        pstm.setObject(6, accessories.getWholesaleprice());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Accessories accessories) throws SQLException {
        String sql = "UPDATE accessories SET supId=?, name= ?, qtyOnHand = ?, normalPrice = ?, wholeSalePrice = ? WHERE accId = ?";

        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, accessories.getSupid());
        pstm.setObject(2, accessories.getName());
        pstm.setObject(3, accessories.getQty());
        pstm.setObject(4, accessories.getNormalPrice());
        pstm.setObject(5, accessories.getWholesaleprice());
        pstm.setObject(6, accessories.getId());;

        return pstm.executeUpdate() > 0;
    }



    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM accessories WHERE accId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Accessories> getAll() throws SQLException {
        String sql = "SELECT * FROM accessories";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Accessories> accessoriesList = new ArrayList<>();

        while (resultSet.next()) {
            String accid = resultSet.getString(1);
            String supid = resultSet.getString(2);
            String name = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String normalprice = resultSet.getString(5);
            String wholesaleprice = resultSet.getString(6);


           Accessories accessories= new Accessories(accid,supid,name,qty,normalprice,wholesaleprice);
            accessoriesList.add(accessories);
        }
        return accessoriesList;


    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT accId FROM accessories ORDER BY accId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String accId = resultSet.getString(1);
            return accId;
        }
        return null;
    }



    public static Accessories searchById(String id) throws SQLException {
        String sql = "SELECT * FROM accessories WHERE accId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String accid = resultSet.getString(1);
            String supid = resultSet.getString(2);
            String name = resultSet.getString(3);
            String qty=resultSet.getString(4);
            String normalprice = resultSet.getString(5);
            String wholesaleprice = resultSet.getString(6);
            ;
            Accessories accessories = new Accessories(accid,supid,name,qty,normalprice,wholesaleprice);


            return accessories;
        }

        return null;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM supplier";
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
