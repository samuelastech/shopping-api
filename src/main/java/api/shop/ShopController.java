package api.shop;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.shop.dtos.ShopDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {
	private final ShopService shopService;

	@GetMapping
	public List<ShopDTO> getShops() {
		return shopService.get();
	}

	@GetMapping("/user/{userIdentifier}")
	public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
		return shopService.getByUser(userIdentifier);
	}

	@GetMapping("/date")
	public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
		return shopService.getByDate(shopDTO);
	}

	@GetMapping("/{id}")
	public ShopDTO findById(@PathVariable Long id) {
		return shopService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO) {
		return shopService.save(shopDTO);
	}
}
