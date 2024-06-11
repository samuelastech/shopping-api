package api.report;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import api.report.dtos.ReportDTO;
import api.shop.ShopEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ReportRepositoryImplementation implements ReportRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<ShopEntity> getShopByFilters(LocalDate dateStart, LocalDate dateEnd, Float minValue) {
		StringBuilder s = new StringBuilder();
		s.append("select s ");
		s.append("from shop s ");
		s.append("where s.date >= :dateStart ");
		
		// Adding optional parameters to the query
		if (dateEnd != null) s.append("and s.date <= :dataEnd ");
		if (minValue != null) s.append("and s.total <= :minValue ");
		
		// SQL Query
		Query q = entityManager.createQuery(s.toString());
		q.setParameter("dateStart", dateStart.atTime(0, 0));
		if (dateEnd != null) q.setParameter("dateEnd", dateEnd.atTime(23, 59));
		if (minValue != null) q.setParameter("minValue", minValue);
		
		return q.getResultList();
	}

	public ReportDTO getReportByDate(LocalDate dateStart, LocalDate dateEnd) {
		StringBuilder s = new StringBuilder();
		s.append("select count(sp.id), sum(sp.total), avg(sp.total)");
		s.append("from shopping.shop sp ");
		s.append("where sp.date >= :dateStart ");
		s.append("and sp.date <= :dataEnd ");
		
		Query q = entityManager.createNativeQuery(s.toString());
		q.setParameter("dateStart", dateStart.atTime(0, 0));
		q.setParameter("dateEnd", dateEnd.atTime(23, 59));
		
		Object[] result = (Object[]) q.getSingleResult();
		
		ReportDTO shopReportDTO = new ReportDTO();
		shopReportDTO.setCount(((BigInteger) result[0]).intValue());
		shopReportDTO.setTotal((Double) result[1]);
		shopReportDTO.setMean((Double) result[2]);
		return shopReportDTO;
	}
	
}
