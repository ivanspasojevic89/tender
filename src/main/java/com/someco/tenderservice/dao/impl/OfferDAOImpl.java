package com.someco.tenderservice.dao.impl;

import com.someco.tenderservice.api.request.GetOffersRequest;
import com.someco.tenderservice.dao.OfferDAOFacade;
import com.someco.tenderservice.model.Offer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OfferDAOImpl implements OfferDAOFacade {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Offer> findOfferByCriteria(GetOffersRequest getOffersRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);

        List<Predicate> predicateList = getPredicateList(getOffersRequest, criteriaBuilder, root);
        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));

        TypedQuery<Offer> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private List<Predicate> getPredicateList(GetOffersRequest getOffersRequest, CriteriaBuilder criteriaBuilder, Root<Offer> root) {
        List<Predicate> predicateList = new ArrayList<>();
        if (getOffersRequest.getCompanyID() != null) {
            predicateList.add(criteriaBuilder.equal(root.get("company"), getOffersRequest.getCompanyID()));
        }
        if (getOffersRequest.getTenderID() != null) {
            predicateList.add(criteriaBuilder.equal(root.get("tender"), getOffersRequest.getTenderID()));
        }
        return predicateList;
    }
}

