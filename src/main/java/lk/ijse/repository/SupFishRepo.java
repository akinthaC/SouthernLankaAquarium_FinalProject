package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Fish;
import lk.ijse.model.SupFish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupFishRepo {
    public static boolean save(SupFish supFish) throws SQLException {
        String sql = "INSERT INTO fish_supplier VALUES(?,?,?,?,?) ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supFish.getFisId());
        pstm.setObject(2, supFish.getSupId());
        pstm.setObject(3, supFish.getDate());
        pstm.setObject(4, supFish.getQty());
        pstm.setObject(5, supFish.getAmount());


        return pstm.executeUpdate() > 0;

    }

    public static List<SupFish> getAll() throws SQLException {
        String sql = "SELECT * FROM fish_supplier ";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<SupFish> fishList = new ArrayList<>();

        while (resultSet.next()) {
            String fishid = resultSet.getString(1);
            String supid = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            int qty = (int) Double.parseDouble(resultSet.getString(4));
            double amount = resultSet.getDouble(5);



           SupFish supFish = new SupFish(fishid,supid,date,qty,amount);
            fishList.add(supFish);
        }
        return fishList;

    }
}
