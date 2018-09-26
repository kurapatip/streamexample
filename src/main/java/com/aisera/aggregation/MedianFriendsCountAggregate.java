package com.aisera.aggregation;

import java.util.ArrayList;
import java.util.List;
import com.aisera.domain.Friend;
import com.aisera.domain.User;

public class MedianFriendsCountAggregate implements Aggregate {

	private List<Integer> friendCountList = new ArrayList<Integer>();

	@Override
	public void reset() {
		friendCountList.clear();
	}

	@Override
	public void processInput(User user) {
		List<Friend> friendList = user.getFriends();
		friendCountList.add(friendList == null ? 0 : friendList.size());
	}

	@Override
	public String toString() {
		Integer[] friendsArr = new Integer[friendCountList.size()];
		friendsArr = friendCountList.toArray(friendsArr);
		return String.format("Median for friends count is %d\n", Util.median(friendsArr));
	}

}
