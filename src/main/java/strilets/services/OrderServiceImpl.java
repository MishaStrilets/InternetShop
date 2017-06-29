package strilets.services;

import strilets.model.Order;
import strilets.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public void deleteOrder(Integer id) {
		orderRepository.delete(id);
	}
}
