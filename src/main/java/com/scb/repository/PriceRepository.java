package com.scb.repository;

import com.scb.model.PriceData;
import com.scb.model.PriceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<PriceEntity,Long> {
}
