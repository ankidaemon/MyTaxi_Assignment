package com.mytaxi.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;

public class CarSpecification implements Specification<CarDO> {

	private SearchCriteria criteria;

	public CarSpecification(SearchCriteria param) {
		this.criteria = param;
	}

	@Override
	public Predicate toPredicate(Root<CarDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThan(root.<String> get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThan(root.<String> get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("=")) {
			if (criteria.getKey().equalsIgnoreCase("engineType")) {
				return builder.equal(root.get(criteria.getKey()), EngineType.valueOf(criteria.getValue().toString()));
			} else if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}

}
