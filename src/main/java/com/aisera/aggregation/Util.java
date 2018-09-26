package com.aisera.aggregation;

import java.util.Arrays;
import java.util.List;

public class Util {

	public static int median(Integer[] numArray) {
		Arrays.sort(numArray);
		int middle = numArray.length / 2;
		int medianValue = 0;
		if (numArray.length % 2 == 1)
			medianValue = numArray[middle];
		else
			medianValue = (numArray[middle - 1] + numArray[middle]) / 2;

		return medianValue;
	}

	public static double mean(List<Double> inputList) {
		double sum = 0;
		double retVal = 0;
		if (inputList.size() > 0) {
			sum = inputList.stream().mapToDouble(f -> f.doubleValue()).sum();
			retVal = sum / inputList.size();
		}
		return retVal;
	}

	public static double meanInt(List<Integer> inputList) {
		double sum = 0;
		double retVal = 0;
		if (inputList.size() > 0) {
			sum = inputList.stream().mapToInt(f -> f.intValue()).sum();
			retVal = sum / inputList.size();
		}
		return retVal;
	}
}
