package com.emlakprojesi.repository;

import java.math.BigDecimal;
import java.util.List;

import com.emlakprojesi.domain.Deal;
import com.emlakprojesi.domain.DealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DealRepository extends JpaRepository<Deal, Long> {

    @Query("select d from Deal d join fetch d.property join fetch d.customer order by d.dealDate desc, d.id desc")
    List<Deal> findAllWithDetails();

    long countByType(DealType type);

    @Query("select sum(d.amount) from Deal d where d.type = :type")
    BigDecimal sumAmountByType(@Param("type") DealType type);
}
