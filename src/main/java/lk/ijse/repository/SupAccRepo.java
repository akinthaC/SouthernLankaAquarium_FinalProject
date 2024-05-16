package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Accessories;
import lk.ijse.model.Fish;
import lk.ijse.model.SupAcc;
import lk.ijse.model.SupFish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupAccRepo {
    public static List<SupAcc> getAll() throws SQLException {
        String sql = "SELECT * FROM accessories_supplier ";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<SupAcc> accList = new ArrayList<>();

        while (resultSet.next()) {
            String accId = resultSet.getString(1);
            String supid = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            int qty = (int) Double.parseDouble(resultSet.getString(4));
            double amount = resultSet.getDouble(5);


            SupAcc supAcc = new SupAcc(accId, supid, date, qty, amount);
            accList.add(supAcc);
        }
        return accList;

    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT accId FROM accessories";
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


    public static boolean save(SupAcc supAcc) throws SQLException {
        String sql = "INSERT INTO accessories_supplier VALUES(?,?,?,?,?) ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supAcc.getAccId());
        pstm.setObject(2, supAcc.getSupId());
        pstm.setObject(3, supAcc.getDate());
        pstm.setObject(4, supAcc.getQty());
        pstm.setObject(5, supAcc.getAmount());


        return pstm.executeUpdate() > 0;
    }
}