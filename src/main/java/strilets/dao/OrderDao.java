package strilets.dao;

import java.util.List;

import strilets.model.Order;

public interface OrderDao {

	List<Order> getAllOrders();

	List<Order> getAllOrders(int buy);

	void saveOrder(Order order);

	void deleteOrder(int id);
}
