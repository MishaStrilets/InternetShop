package strilets.services;

import java.util.List;

import strilets.model.Order;

public interface OrderService {

	List<Order> getAllOrders();

	void saveOrder(Order order);

	void deleteOrder(int id);
}
