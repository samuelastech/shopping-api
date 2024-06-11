package api.shop;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
	public List<ShopEntity> findAllByUserIdentifier(String userIdentifier);

	public List<ShopEntity> findAllByTotalGreaterThan(Float total);

	List<ShopEntity> findAllByDateGreaterThan(LocalDateTime date);
}
