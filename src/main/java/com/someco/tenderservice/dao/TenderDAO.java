package com.someco.tenderservice.dao;

import com.someco.tenderservice.model.Tender;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenderDAO extends CrudRepository<Tender, Long> {
    @Query(value = "SELECT * FROM tender WHERE TenderCompanyID = :companyID", nativeQuery = true)
    List<Tender> findByCompanyID(@Param("companyID") Long companyID);
}
