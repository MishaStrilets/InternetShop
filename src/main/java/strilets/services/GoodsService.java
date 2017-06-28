package strilets.services;

import strilets.model.Goods;

public interface GoodsService {

	Iterable<Goods> listAllGoods();

	Goods getGoodsById(Integer id);

	Goods saveGoods(Goods product);

	void deleteGoods(Integer id);
}
