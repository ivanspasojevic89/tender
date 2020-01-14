package com.someco.tenderservice.dao;

import com.someco.tenderservice.model.Offer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OfferDAO extends CrudRepository<Offer, Long>, OfferDAOFacade {
    @Query(value = "SELECT * from offer where OfferTenderID = :offerTenderID", nativeQuery = true)
    List<Offer> finOffersByTenderID(@Param("offerTenderID") Long offerTenderID);

    @Query(value = "SELECT * from offer where OfferTenderID = :tenderID and OfferStatus = :status for update", nativeQuery = true)
    List<Offer> findByTenderIDAndStatus(@Param("tenderID") Long tenderID, @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE offer SET OfferStatus = \'rejected\' where OfferTenderID = :tenderID and OfferStatus = \'pending\'", nativeQuery = true)
    void setPendingOffersAsRejected(@Param("tenderID") Long tenderID);
}
