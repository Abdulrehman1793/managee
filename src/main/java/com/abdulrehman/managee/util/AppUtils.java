package com.abdulrehman.managee.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.context.SecurityContextHolder;

import com.abdulrehman.managee.security.UserPrincipal;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
public class AppUtils {

	private static Sort defaultSort = Sort.by(Direction.ASC, "createdAt");

	/**
	 * Default sort value if no parameter found on request. (CreatedAt - Ascending)
	 */
	public static Sort requestParamSortBuilder(Optional<List<String>> sortByOptional) {

		Sort sort = defaultSort;

		List<String> sorts = (sortByOptional.isPresent()) ? sortByOptional.get() : new ArrayList<String>();

		List<Order> orders = new ArrayList<Sort.Order>();
		for (String row : sorts) {
			String[] params = row.split("-");

			if (Boolean.valueOf(params[1]))
				orders.add(Order.asc(params[0]));
			else
				orders.add(Order.desc(params[0]));
		}
		if (orders.size() > 0)
			sort = Sort.by(orders);

		return sort;
	}

	/**
	 * If no parameter found to sort in list then return the default sort pass as
	 * parameter.
	 */
	public static Sort requestParamSortBuilder(Sort sort, Optional<List<String>> sortByOptional) {

		List<String> sorts = (sortByOptional.isPresent()) ? sortByOptional.get() : new ArrayList<String>();

		List<Order> orders = new ArrayList<Sort.Order>();
		for (String row : sorts) {
			String[] params = row.split("-");

			if (Boolean.valueOf(params[1]))
				orders.add(Order.asc(params[0]));
			else
				orders.add(Order.desc(params[0]));
		}
		if (orders.size() > 0)
			sort = Sort.by(orders);

		return sort;
	}

	public static UserPrincipal userPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
