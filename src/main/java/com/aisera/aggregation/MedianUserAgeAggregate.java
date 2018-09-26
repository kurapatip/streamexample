package com.aisera.aggregation;

import java.util.ArrayList;
import java.util.List;

import com.aisera.domain.User;

public class MedianUserAgeAggregate  implements Aggregate {
	
	private List<Integer> lstUserAge = new ArrayList<Integer>();

	@Override
	public void reset() {
		lstUserAge.clear();
	}

	@Override
	public void processInput(User user) {
		lstUserAge.add(user.getAge());
	}

	@Override
	public String toString() {
		Integer[] uesrAgeArr = new Integer[lstUserAge.size()];
		uesrAgeArr = lstUserAge.toArray(uesrAgeArr);
		return String.format("Median for user age is %d\n", Util.median(uesrAgeArr));
	}

}
