package strilets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import strilets.dao.GoodsDao;
import strilets.model.Goods;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao dao;

	public Goods getGoodsById(int id) {
		return dao.getGoodsById(id);
	}

	public void saveGoods(Goods goods) {
		dao.saveGoods(goods);
	}

	public void updateGoods(Goods goods) {
		Goods entity = dao.getGoodsById(goods.getId());
		if (entity != null) {
			entity.setName(goods.getName());
			entity.setDescription(goods.getDescription());
			entity.setPrice(goods.getPrice());
		}
	}

	public void deleteGoods(int id) {
		dao.deleteGoods(id);
	}

	public List<Goods> getAllGoods() {
		return dao.getAllGoods();
	}

	public List<Goods> getGoodsByName(String name) {
		return dao.getGoodsByName(name);
	}

	public List<Goods> sortGoods(String sort) {
		return dao.sortGoods(sort);
	}

}
