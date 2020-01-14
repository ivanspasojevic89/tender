CREATE TABLE `company` (
                         `CompanyID` bigint(20) NOT NULL AUTO_INCREMENT,
                         `CompanyName` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                         `CompanyTaxNumber` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `CompanyRegistrationNumber` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `CompanyCreated` datetime DEFAULT NULL,
                         PRIMARY KEY (`CompanyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tender` (
                        `TenderID` bigint(20) NOT NULL AUTO_INCREMENT,
                        `TenderDescription` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `TenderConditions` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `TenderCompanyID` bigint(20) NOT NULL COMMENT 'Company issuer of tender',
                        `TenderStatus` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'issued,completed',
                        `TenderCreated` datetime DEFAULT NULL,
                        `TenderCompleted` datetime DEFAULT NULL,
                        PRIMARY KEY (`TenderID`),
                        KEY `tender_company_FK` (`TenderCompanyID`),
                        CONSTRAINT `tender_company_FK` FOREIGN KEY (`TenderCompanyID`) REFERENCES `company` (`CompanyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `offer` (
                       `OfferID` bigint(20) NOT NULL AUTO_INCREMENT,
                       `OfferPrice` decimal(10,0) NOT NULL,
                       `OfferTenderID` bigint(20) NOT NULL,
                       `OfferCompanyID` bigint(20) NOT NULL COMMENT 'Company created an offer',
                       `OfferStatus` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'pending,canceled,rejected,accepted',
                       `OfferCreated` datetime DEFAULT NULL,
                       `OfferModified` datetime DEFAULT NULL,
                       PRIMARY KEY (`OfferID`),
                       KEY `offer_tender_FK` (`OfferTenderID`),
                       KEY `offer_company_FK` (`OfferCompanyID`),
                       CONSTRAINT `offer_company_FK` FOREIGN KEY (`OfferCompanyID`) REFERENCES `company` (`CompanyID`),
                       CONSTRAINT `offer_tender_FK` FOREIGN KEY (`OfferTenderID`) REFERENCES `tender` (`TenderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

