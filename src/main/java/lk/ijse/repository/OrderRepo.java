package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepo {
    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, order.getId());
        pstm.setObject(2, order.getDate());
        pstm.setObject(3, order.getHandOverDate());
        pstm.setObject(4, order.getQty());
        pstm.setObject(5, order.getStatus());

        return pstm.executeUpdate() > 0;

    }
}
