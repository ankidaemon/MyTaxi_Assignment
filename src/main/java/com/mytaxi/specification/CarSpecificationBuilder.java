package com.mytaxi.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mytaxi.domainobject.CarDO;

public class CarSpecificationBuilder {
	private final List<SearchCriteria> params;

	public CarSpecificationBuilder() {
	    this.params = new ArrayList<>();
	}

	public CarSpecificationBuilder with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public Specification<CarDO> build() {
		if (params.size() == 0) {
			return null;
		}

		List<Specification<CarDO>> specs = new ArrayList<Specification<CarDO>>();
		for (SearchCriteria param : params) {
			specs.add(new CarSpecification(param));
		}

		Specification<CarDO> result = specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and(specs.get(i));
		}
		return result;
	}
}
