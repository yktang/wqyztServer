package com.weiwei.weiqi.jdbc.dao.customer;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.weiwei.weiqi.jdbc.dao.base.BaseJpaRepository;
import com.weiwei.weiqi.jdbc.dbmodel.customer.Customer;

public interface CustomerDao extends BaseJpaRepository<Customer, Integer>{

	@Query(" from Customer bean where bean.mobilePhonenumber = ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Customer findByPhone(String phone);

}
