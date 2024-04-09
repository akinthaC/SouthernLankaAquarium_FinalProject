package lk.ijse.repository;

import lk.ijse.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class accessoriesOrderRepo {
    public static boolean save(String ordId, String accId, String status, int qty, String description) throws SQLException {
        String sql = "INSERT INTO accessories_order VALUES(?,?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,ordId);
        pstm.setObject(2, accId);
        pstm.setObject(3, qty);
        pstm.setObject(4, description);
        pstm.setObject(5, status);


        return pstm.executeUpdate() > 0;
    }
}
