package api.shop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import api.dtos.ShopDTO;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shop")
public class ShopEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String userIdentifier;

	@Column
	private float total;

	@Column
	private LocalDateTime date;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
	private List<ItemEntity> items;

	public static ShopEntity convert(ShopDTO shopDTO) {
		ShopEntity shop = new ShopEntity();
		shop.setUserIdentifier(shopDTO.getUserIdentifier());
		shop.setTotal(shopDTO.getTotal());
		shop.setDate(shopDTO.getDate());
		shop.setItems(shopDTO.getItems().stream().map(ItemEntity::convert).collect(Collectors.toList()));
		return shop;
	}
}
