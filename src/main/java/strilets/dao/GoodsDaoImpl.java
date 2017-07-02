package strilets.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import strilets.model.Goods;

@Repository("goodsDao")
public class GoodsDaoImpl extends AbstractDao<Integer, Goods> implements
		GoodsDao {

	public Goods getGoodsById(int id) {
		return getByKey(id);
	}

	public void saveGoods(Goods goods) {
		persist(goods);
	}

	public void deleteGoods(int id) {
		Query query = getSession().createSQLQuery(
				"delete from Goods where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getAllGoods() {
		Criteria criteria = createEntityCriteria();
		return (List<Goods>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByName(String name) {
		Criteria criteria = createEntityCriteria();
		List<Goods> goods = criteria.add(
				Restrictions.like("name", name, MatchMode.START)).list();
		return goods;
	}

	@SuppressWarnings("unchecked")
	public List<Goods> sortGoods(String sort) {

		Criteria criteria = createEntityCriteria();
		List<Goods> goods = null;

		if (sort.equals("asc")) {
			goods = criteria.addOrder(Order.asc("price")).list();
		}

		else if (sort.equals("desc")) {
			goods = criteria.addOrder(Order.desc("price")).list();
		}

		else if (sort.equals("add")) {
			goods = criteria.list();
		}

		return goods;
	}

}
