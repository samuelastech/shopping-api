package api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import api.shop.ShopEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
	@NotBlank
	private String userIdentifier;
	@NotNull
	private Float total;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private List<ItemDTO> items;

	public static ShopDTO convert(ShopEntity shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setUserIdentifier(shop.getUserIdentifier());
		shopDTO.setTotal(shop.getTotal());
		shopDTO.setDate(shop.getDate());
		return shopDTO;
	}
}
