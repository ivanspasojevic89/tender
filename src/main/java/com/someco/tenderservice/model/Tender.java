package com.someco.tenderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tender")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tender {
    @Column(name = "TenderID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenderID;

    @Column(name = "TenderDescription")
    private String tenderDescription;

    @Column(name = "TenderConditions")
    private String tenderConditions;

    @Column(name = "TenderStatus")
    private String tenderStatus;

    @Column(name = "TenderCreated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date tenderCreated;

    @Column(name = "TenderCompleted")
    private Date tenderCompleted;

    @ManyToOne
    @JoinColumn(name = "TenderCompanyID", nullable = false)
    private Company company;


}
