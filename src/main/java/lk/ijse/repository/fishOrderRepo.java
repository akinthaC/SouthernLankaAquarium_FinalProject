package lk.ijse.repository;

import lk.ijse.Db.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class fishOrderRepo {
    public static boolean save(String ordId, String fishId, String status, int qty, String description, Date date) throws SQLException {
        String sql = "INSERT INTO fish_order VALUES(?,?,?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,ordId);
        pstm.setObject(2, fishId);
        pstm.setObject(3, qty);
        pstm.setObject(4, description);
        pstm.setObject(5, status);
        pstm.setObject(6, date);


        return pstm.executeUpdate() > 0;

    }
}
