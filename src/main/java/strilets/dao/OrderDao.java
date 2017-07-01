package strilets.dao;

import java.util.List;

import strilets.model.Order;

public interface OrderDao {

	List<Order> getAllOrders();

	void saveOrder(Order order);

	void deleteOrder(int id);
}
