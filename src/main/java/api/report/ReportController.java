package api.report;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.report.dtos.ReportDTO;
import api.shop.dtos.ShopDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class ReportController {
	private final ReportService reportService;
	
	@GetMapping("/search")
	public List<ShopDTO> getShopsByFilter(
			@RequestParam(name = "dateStart", required = true)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateStart,
			
			@RequestParam(name = "dateEnd", required = false)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateEnd,
			
			@RequestParam(name = "minValue", required = false) Float minValue
	) {
		return reportService.getShopByFilters(dateStart, dateEnd, minValue);
	}
	
	@GetMapping("/search")
	public ReportDTO getReportByDate(
			@RequestParam(name = "dateStart", required = true)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateStart,
			
			@RequestParam(name = "dateEnd", required = false)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateEnd
	) {
		return reportService.getReportByDate(dateStart, dateEnd);
	}
}
