package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Fish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishRepo {

        public static boolean save(Fish fish) throws SQLException {
            String sql = "INSERT INTO fish VALUES(?,?,?,?,?) ";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, fish.getId());
            pstm.setObject(2, fish.getName());
            pstm.setObject(3, fish.getQty());
            pstm.setObject(4, fish.getNormalPrice());
            pstm.setObject(5, fish.getWholesaleprice());

            return pstm.executeUpdate() > 0;

        }

        public static boolean update(Fish fish) throws SQLException {
            String sql = "UPDATE fish SET  name= ?, qtyOnHand = ?, normalPrice = ?, wholeSalePrice = ? WHERE FishId = ?";

            Connection connection =DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);


            pstm.setObject(1, fish.getName());
            pstm.setObject(2, fish.getQty());
            pstm.setObject(3, fish.getNormalPrice());
            pstm.setObject(4, fish.getWholesaleprice());
            pstm.setObject(5, fish.getId());;

            return pstm.executeUpdate() > 0;
        }

        public static Fish searchById(String id) throws SQLException {
            String sql = "SELECT * FROM fish WHERE fishId = ?";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String fishId = resultSet.getString(1);
                String name = resultSet.getString(2);
                String qty=resultSet.getString(3);
                double normalPrice = Double.parseDouble(resultSet.getString(4));
                double WholeSalePrice = Double.parseDouble(resultSet.getString(5));


                Fish fish = new Fish(fishId, name, qty, normalPrice, WholeSalePrice);


                return fish;
            }

            return null;

        }

        public static boolean delete(String id) throws SQLException {
            String sql = "DELETE FROM fish WHERE fishId = ?";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);

            return pstm.executeUpdate() > 0;
        }

        public static List<Fish> getAll() throws SQLException {
            String sql = "SELECT * FROM fish";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            List<Fish> fishList = new ArrayList<>();

            while (resultSet.next()) {
                String fishid = resultSet.getString(1);
                String name = resultSet.getString(2);
                String qty = resultSet.getString(3);
                double normalprice = Double.parseDouble(resultSet.getString(4));
                double wholesaleprice = Double.parseDouble(resultSet.getString(5));


                Fish fish = new Fish(fishid,name,qty,normalprice,wholesaleprice);
                fishList.add(fish);
            }
            return fishList;


        }

        public static String getCurrentId() throws SQLException {
            String sql = "SELECT fishId FROM fish ORDER BY fishId DESC LIMIT 1";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                String fishId = resultSet.getString(1);
                return fishId;
            }
            return null;
        }


    public static List<String> getIds() throws SQLException {
        String sql = "SELECT fishId FROM fish";
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

 /*   public static boolean update1(List<OrderDetail> odlist) throws SQLException {
        for (OrderDetail od : odlist) {
            boolean isUpdateQty = updateQty1(od.getFishId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }*/

    static boolean updateQty1(String fishId, int qty) throws SQLException {
        String sql = "UPDATE fish SET qtyOnHand = qtyOnHand - ? WHERE fishId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, fishId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateSupFish(int qty, String id) throws SQLException {

        String sql = "UPDATE fish SET qtyOnHand = qtyOnHand + ? WHERE fishId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;


    }

    public static Map<String, Integer> GetFishDetail() {
        Map<String, Integer> FishDetail = new HashMap<>();

        String sql = "SELECT name, qtyOnHand FROM fish;";

        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int qtyOnHand = (int) resultSet.getDouble("qtyOnHand");
                FishDetail.put(name, qtyOnHand);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FishDetail;
    }

}
