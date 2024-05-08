package lk.ijse.repository;

import lk.ijse.Db.DbConnection;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetail;
import lk.ijse.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {


    public static boolean orders(PlaceOrder pl) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved =OrderRepo.save(pl.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = false;
                for (OrderDetail od : pl.getOdlist()) {
                    if (od.getFishId() == null) {

                        isQtyUpdated = AccessoriesRepo.updateQty(od.getAccId(),od.getQty());

                    } else {
                        isQtyUpdated = FishRepo.updateQty1(od.getFishId(),od.getQty());
                    }
                }

                if (isQtyUpdated) {
                    boolean isSave=false;
                    for (OrderDetail od : pl.getOdlist()){
                        if (od.getFishId() == null) {

                            isSave =accessoriesOrderRepo.save(od.getOrdId(),od.getAccId(),od.getStatus(),od.getQty(),od.getDescription(),od.getDate());

                        } else {
                            isSave =fishOrderRepo.save(od.getOrdId(),od.getFishId(),od.getStatus(),od.getQty(),od.getDescription(),od.getDate());
                        }
                    }
                    if (isSave ) {
                        boolean isSave1 = false;
                        for (OrderDetail od : pl.getOdlist()) {
                            isSave1 = orderEmployeeRepo.save(od.getOrdId(),od.getEmpId());
                        }
                        if (isSave1) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}

