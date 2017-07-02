package strilets.services;

import java.util.List;

import strilets.model.Goods;

public interface GoodsService {

	List<Goods> getAllGoods();

	Goods getGoodsById(int id);

	void saveGoods(Goods goods);

	void deleteGoods(int id);

	void updateGoods(Goods goods);

	List<Goods> getGoodsByName(String name);
	
	List<Goods> sortGoods(String sort);
}
