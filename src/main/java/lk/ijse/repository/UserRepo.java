package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Supplier;
import lk.ijse.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public static List<String> searchName() throws SQLException {
        String sql = "SELECT userName  FROM user";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            idList.add(name);
        }
        return idList;
    }

    public static User searchByName(String name) throws SQLException {
        String sql = "SELECT * FROM user WHERE userName = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, name);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String name1 = resultSet.getString(1);
            String password = resultSet.getString(2);
            String email = resultSet.getString(3);

            User user = new User(name1, password, email);


            return user;
        }

        return null;
    }

    public static boolean update(String Password, String userName) throws SQLException {
        String sql = "UPDATE user SET password = ? WHERE userName = ?";


        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, Password);
        pstm.setString(2, userName);

        return pstm.executeUpdate() > 0;

    }

    public static boolean update1(String email, String userName) throws SQLException {
        String sql = "UPDATE user SET email    = ? WHERE userName = ?";


        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, email);
        pstm.setString(2, userName);

        return pstm.executeUpdate() > 0;
    }
}
