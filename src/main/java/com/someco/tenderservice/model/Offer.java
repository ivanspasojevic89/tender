package com.someco.tenderservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date offerCreated;

    @Column(name = "OfferModified")
    private Date offerModified;

    @ManyToOne
    @JoinColumn(name = "OfferTenderID", nullable = false)
    @JsonProperty
    private Tender tender;

    @ManyToOne
    @JoinColumn(name = "OfferCompanyID", nullable = false)
    @JsonProperty
    private Company company;


}
