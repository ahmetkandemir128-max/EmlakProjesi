package com.emlakprojesi.repository;

import java.util.List;

import com.emlakprojesi.domain.Property;
import com.emlakprojesi.domain.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p left join fetch p.owner "
            + "where (:keyword is null or :keyword = '' "
            + "or lower(p.title) like lower(concat('%', :keyword, '%')) "
            + "or lower(p.city) like lower(concat('%', :keyword, '%')) "
            + "or lower(p.district) like lower(concat('%', :keyword, '%'))) "
            + "and (:status is null or p.status = :status)")
    List<Property> search(@Param("keyword") String keyword, @Param("status") PropertyStatus status);

    long countByStatus(PropertyStatus status);

    List<Property> findByStatusIn(List<PropertyStatus> statuses);
}
