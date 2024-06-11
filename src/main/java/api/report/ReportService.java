package api.report;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import api.report.dtos.ReportDTO;
import api.shop.ShopEntity;
import api.shop.dtos.ShopDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
	private final ReportRepositoryImplementation repo;

	public List<ShopDTO> getShopByFilters(LocalDate dateStart, LocalDate dateEnd, Float minValue) {
		List<ShopEntity> shops = repo.getShopByFilters(dateStart, dateEnd, minValue);
		return shops.stream().map(ShopDTO::convert).toList();
	}

	public ReportDTO getReportByDate(LocalDate dateStart, LocalDate dateEnd) {
		return repo.getReportByDate(dateStart, dateEnd);
	}
}
