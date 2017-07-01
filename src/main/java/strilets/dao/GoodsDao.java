package strilets.dao;

import java.util.List;

import strilets.model.Goods;

public interface GoodsDao {

	List<Goods> getAllGoods();

	Goods getGoodsById(int id);

	void saveGoods(Goods goods);

	void deleteGoods(int id);

	List<Goods> getGoodsByName(String name);
}
