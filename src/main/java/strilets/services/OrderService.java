package strilets.services;

import strilets.model.Order;

public interface OrderService {

	Iterable<Order> getAllOrders();

	Order saveOrder(Order order);

	void deleteOrder(Integer id);
}
