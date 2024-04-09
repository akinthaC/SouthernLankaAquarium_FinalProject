package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Accessories;
import lk.ijse.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?,?,?,?,?,?) ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, payment.getId());
        pstm.setObject(2, payment.getOrdid());
        pstm.setObject(3, payment.getDate());
        pstm.setObject(4, payment.getTotal());
        pstm.setObject(5, payment.getAdvance());
        pstm.setObject(6, payment.getType());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET orderId=?, date= ?, totalAmounr = ?, advance = ?, paytype = ? WHERE payId = ?";

        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);



        pstm.setObject(1, payment.getOrdid());
        pstm.setObject(2, payment.getDate());
        pstm.setObject(3, payment.getTotal());
        pstm.setObject(4, payment.getAdvance());
        pstm.setObject(5, payment.getType());
        pstm.setObject(6, payment.getId());

        return pstm.executeUpdate() > 0;
    }



    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM payment WHERE payId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String payid = resultSet.getString(1);
            String ordid = resultSet.getString(2);
            String date = resultSet.getString(3);
            double total = Double.parseDouble(resultSet.getString(4));
            double advance = Double.parseDouble(resultSet.getString(5));
            String type = resultSet.getString(6);


            Payment payment= new Payment(payid,ordid,date,total,advance,type);
            paymentList.add(payment);
        }
        return paymentList;


    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT payId FROM payment ORDER BY payId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String payId = resultSet.getString(1);
            return payId;
        }
        return null;
    }



    public static Payment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM payment WHERE payid = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String payid = resultSet.getString(1);
            String ordid = resultSet.getString(2);
            String date = resultSet.getString(3);
            double total = Double.parseDouble(resultSet.getString(4));
            double advance = Double.parseDouble(resultSet.getString(5));
            String type = resultSet.getString(6);

            ;

            Payment payment= new Payment(payid,ordid,date,total,advance,type);

            return payment;
        }

        return null;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM orders";
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
