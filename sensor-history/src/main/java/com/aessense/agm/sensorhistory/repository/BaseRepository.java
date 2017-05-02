package com.aessense.agm.sensorhistory.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * The base class that all repositories inherit from.
 * @author John Long
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
	  T findOne(ID id);
}
