package api.report;

import java.time.LocalDate;
import java.util.List;

import api.report.dtos.ReportDTO;
import api.shop.ShopEntity;

public interface ReportRepository {
	public List<ShopEntity> getShopByFilters(LocalDate dateStart, LocalDate dateEnd, Float minValue);

	public ReportDTO getReportByDate(LocalDate dateStart, LocalDate dateEnd);
}
