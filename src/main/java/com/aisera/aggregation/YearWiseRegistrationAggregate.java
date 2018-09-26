package com.aisera.aggregation;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.aisera.domain.User;



public class YearWiseRegistrationAggregate implements Aggregate {
	private Map<Integer, Integer> yearlyRegistrations = new HashMap<>();
	
	@Override
	public void reset() {
		yearlyRegistrations.clear();
	}

	@Override
	public void processInput(User user) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(user.getRegistered());
		
		int year = calendar.get(Calendar.YEAR);
		if (yearlyRegistrations.containsKey(year)) {
			yearlyRegistrations.put(year, yearlyRegistrations.get(year) + 1);
		} else {
			yearlyRegistrations.put(year, 1);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int year : yearlyRegistrations.keySet()) {
			builder.append(String.format("Registrations for year %d are %d\n", year, yearlyRegistrations.get(year)));
		}
		return builder.toString();
	}

	
	

}
