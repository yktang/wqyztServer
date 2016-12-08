package com.weiwei.weiqi.jdbc.dao.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseJpaRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {
	
	public Page<T> findAll(Specification<T> spec, Pageable pageable);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<T> findAll(Specification<T> spec);
}
