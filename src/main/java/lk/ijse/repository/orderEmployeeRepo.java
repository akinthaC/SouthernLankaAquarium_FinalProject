package lk.ijse.repository;

import lk.ijse.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class orderEmployeeRepo {
    public static boolean save(String ordId, String empId) throws SQLException {
        String sql = "INSERT INTO order_employee VALUES(?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,ordId);
        pstm.setObject(2, empId);



        return pstm.executeUpdate() > 0;
    }
}
