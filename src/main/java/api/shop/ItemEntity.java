package api.shop;

import api.shop.dtos.ItemDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemEntity {
	private String productIdentifier;
	private Float price;

	public static ItemEntity convert(ItemDTO itemDTO) {
		ItemEntity item = new ItemEntity();
		item.setProductIdentifier(itemDTO.getProductIdentifier());
		item.setPrice(itemDTO.getPrice());
		return item;
	}
}
