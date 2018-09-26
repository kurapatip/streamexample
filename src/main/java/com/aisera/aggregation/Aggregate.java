package com.aisera.aggregation;

import com.aisera.domain.User;

public interface Aggregate {
	void reset();
	void processInput(User user);
}
