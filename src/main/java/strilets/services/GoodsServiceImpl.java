package strilets.services;

import strilets.model.Goods;
import strilets.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

	private GoodsRepository goodsRepository;

	@Autowired
	public void setGoodsRepository(GoodsRepository goodsRepository) {
		this.goodsRepository = goodsRepository;
	}

	public Iterable<Goods> listAllGoods() {
		return goodsRepository.findAll();
	}

	public Goods getGoodsById(Integer id) {
		return goodsRepository.findOne(id);
	}

	public Goods saveGoods(Goods goods) {
		return goodsRepository.save(goods);
	}

	public void deleteGoods(Integer id) {
		goodsRepository.delete(id);
	}
}
