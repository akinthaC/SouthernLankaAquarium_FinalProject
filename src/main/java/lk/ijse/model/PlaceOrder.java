package lk.ijse.model;

import lk.ijse.model.Order;
import lk.ijse.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrder {

  private Order order;
  private List<OrderDetail> odlist;
}
