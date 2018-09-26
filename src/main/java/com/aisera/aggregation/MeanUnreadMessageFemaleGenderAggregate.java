package com.aisera.aggregation;

import java.util.ArrayList;
import java.util.List;

import com.aisera.domain.User;

public class MeanUnreadMessageFemaleGenderAggregate implements Aggregate {

	private List<Integer> unreadMessageCountOfFemaleGender = new ArrayList<Integer>();

	@Override
	public void reset() {
		unreadMessageCountOfFemaleGender.clear();
	}

	@Override
	public void processInput(User user) {
		if (user.getGender().equalsIgnoreCase(Constants.GENDER_FEMALE)) {
			Integer unreadMessageCount = getUnreadMessageCount(user.getGreeting());
			unreadMessageCountOfFemaleGender.add(unreadMessageCount);
		}
	}

	@Override
	public String toString() {
		return String.format("Mean value of Unread Message of Female Gender is  %f\n",
				Util.meanInt(unreadMessageCountOfFemaleGender));
	}

	private Integer getUnreadMessageCount(String greeting) {
		Integer retVal = 0;
		// Sample : "greeting": "Hello, Rowena Olson! You have 62 unread messages."
		// Splitting string is necessary on safer side if name or first part contains
		// number
		// later string part is static so
		String[] strArray = greeting.split("!");
		if (strArray.length > 1) {
			// Option 1
			String unreadCount = strArray[1].replaceAll("[^0-9]+", "");
			// Option 2
			// String unreadCount = strArray[1].replace("You have", "").replace("unread
			// messages.", "").trim();

			if (!unreadCount.isEmpty()) {
				retVal = Integer.parseInt(unreadCount);
			}
		}
		return retVal;
	}

}
