package api.shop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import api.shop.dtos.ShopDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	private final ShopRepository shopRepository;

	public List<ShopDTO> get() {
		List<ShopEntity> shops = shopRepository.findAll();
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}

	public List<ShopDTO> getByUser(String userIdentifier) {
		List<ShopEntity> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}

	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<ShopEntity> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}

	public ShopDTO findById(long productId) {
		Optional<ShopEntity> shop = shopRepository.findById(productId);
		if (shop.isPresent())
			return ShopDTO.convert(shop.get());
		return null;
	}

	public ShopDTO save(ShopDTO shopDTO) {
		// Calculate the total of one shopping
		shopDTO.setTotal(shopDTO.getItems().stream().map(item -> item.getPrice()).reduce((float) 0, Float::sum));
		ShopEntity shop = ShopEntity.convert(shopDTO);
		shop.setDate(LocalDateTime.now());
		shop = shopRepository.save(shop);
		return ShopDTO.convert(shop);
	}
}
