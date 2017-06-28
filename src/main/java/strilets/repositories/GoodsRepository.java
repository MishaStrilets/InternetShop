package strilets.repositories;

import strilets.model.Goods;
import org.springframework.data.repository.CrudRepository;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {
}
