package com.weiwei.weiqi.service.base;

import java.util.Collection;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.weiwei.common.SearchFilter;
import com.weiwei.weiqi.request.base.MyPageRequest;


public abstract class BaseServiceImpl {
	protected <T> Specification<T> spec(Map<String, String[]> params,Class<T> t) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		final Specification<T> fsp = SearchFilter.spec(filters, t);
		Specification<T> sp = new Specification<T>() {
			public Predicate toPredicate(Root<T> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				boolean distinct = false;
				Predicate pred = fsp.toPredicate(root, query, cb);
				
				if (distinct) {
					query.distinct(true);
				}
				return pred;
			}
		};
		return sp;
	}
	
	/**
	 * 
	* @Description: 获取分页对象
	*
	* @param myPageRequest
	* @return 
	*
	* @version V1.0
	*
	 */
	protected Pageable getPageable(MyPageRequest myPageRequest){
		return  new PageRequest(myPageRequest.getPage(), myPageRequest.getSize());
	}
	
	
	/**
	 * 
	* @Description: 获取分页对象
	*
	* @param myPageRequest
	* @return 
	*
	* @version V1.0
	*
	 */
	protected Pageable getPageable(MyPageRequest myPageRequest,Sort sort){
		return  new PageRequest(myPageRequest.getPage(), myPageRequest.getSize(),sort);
	}
	

	
}
