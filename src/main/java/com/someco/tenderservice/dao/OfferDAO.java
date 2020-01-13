package com.someco.tenderservice.dao;

import com.someco.tenderservice.model.Offer;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;

public interface OfferDAO extends CrudRepository<Offer, Long>, OfferDAOFacade {
    @Query(value = "SELECT * from offer where OfferTenderID = :offerTenderID", nativeQuery = true)
    List<Offer> finOffersByTenderID(@Param("offerTenderID") Long offerTenderID);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT * from offer where OfferTenderID = :tenderID and OfferStatus = :status", nativeQuery = true)
    List<Offer> findByTenderIDAndStatus(Long tenderID, String status);

    @Modifying
    @Query(value = "UPDATE offer SET OfferStatus = \'rejected\' where OfferTenderID = :tenderID and OfferStatus = \'pending\'", nativeQuery = true)
    void setPendingOffersAsRejected(Long tenderID);
}
