package com.someco.tenderservice.dao;

import com.someco.tenderservice.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDAO extends CrudRepository<Company,Long> {
}
