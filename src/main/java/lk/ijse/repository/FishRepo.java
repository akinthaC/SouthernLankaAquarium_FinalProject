package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Fish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                String fishid = resultSet.getString(1);
                String name = resultSet.getString(2);
                String qty=resultSet.getString(3);
                String normalprice = resultSet.getString(4);
                String wholesaleprice = resultSet.getString(5);
                ;
               Fish fish = new Fish(fishid,name,qty,normalprice,wholesaleprice);


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
                String normalprice = resultSet.getString(4);
                String wholesaleprice = resultSet.getString(5);


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



}
