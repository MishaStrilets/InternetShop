package strilets.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import strilets.model.Order;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements
		OrderDao {

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders() {
		Criteria criteria = createEntityCriteria();
		List<Order> orders = criteria.list();
		return orders;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders(int buy) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("buy", buy));
		List<Order> orders = criteria.list();
		return orders;
	}

	public void saveOrder(Order order) {
		persist(order);
	}

	public void deleteOrder(int id) {
		Query query = getSession().createSQLQuery(
				"delete from Orders where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

}
