package strilets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import strilets.dao.OrderDao;
import strilets.model.Order;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;

	public List<Order> getAllOrders() {
		return dao.getAllOrders();
	}

	public List<Order> getAllOrders(int buy) {
		return dao.getAllOrders(buy);
	}

	public void saveOrder(Order order) {
		dao.saveOrder(order);
	}

	public void deleteOrder(int id) {
		dao.deleteOrder(id);
	}

}
