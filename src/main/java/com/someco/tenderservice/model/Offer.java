package com.someco.tenderservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "offer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Column(name = "OfferID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerID;

    @Column(name = "OfferPrice")
    private BigDecimal offerPrice;

    @Column(name = "OfferStatus")
    private String offerStatus;

    @Column(name = "OfferCreated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date offerCreated;

    @Column(name = "OfferModified")
    private Date offerModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfferTenderID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Tender tender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OfferCompanyID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Company company;


}
