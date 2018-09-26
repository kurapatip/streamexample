package com.aisera.aggregation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.aisera.domain.User;

public class MeanBalanceAmountAggregate implements Aggregate {

	private List<Double> balanceAmountList = new ArrayList<Double>();

	@Override
	public void reset() {
		balanceAmountList.clear();
	}

	@Override
	public void processInput(User user) {
		balanceAmountList
				.add(user.getBalance() == null || user.getBalance().isEmpty() ? 0.0 : formatValue(user.getBalance()));
	}

	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat(".##");
		Double meanBalanceAmount = Util.mean(balanceAmountList);
		return String.format("Mean value of User Balance Amount is $%s\n", df2.format(meanBalanceAmount) );
	}

	private Double formatValue(String balanceAmount) {
		try {
			return Double.parseDouble(balanceAmount.replace("$", "").replace(",", ""));
		} catch (NumberFormatException ex) {
			return 0.0;
		}
	}

}
