package com.aisera.codetest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.aisera.aggregation.Aggregate;
import com.aisera.aggregation.MeanBalanceAmountAggregate;
import com.aisera.aggregation.MeanUnreadMessageFemaleGenderAggregate;
import com.aisera.aggregation.MedianFriendsCountAggregate;
import com.aisera.aggregation.MedianUserAgeAggregate;
import com.aisera.aggregation.YearWiseRegistrationAggregate;
import com.aisera.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * App to read json files and perform various aggregate operations
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		App app = new App();
		try {
			System.out.println("Analyzing the first file : users-1.json");
			app.analyse("users-1.json");
			// app.analyse("small-user-data.json");
			System.out.println("Analyzing the second file : users-2.json");
			app.analyse("users-2.json");
		} catch (NullPointerException ex) {
			System.out.println("Input file not found");
		}
	}

	private void analyse(String fileName) throws IOException {
		int chunkSize = 1000;
		JsonReader reader = new JsonReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fileName)));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd'T'HH:mm:ssXXX").create();

		// Read file in stream mode
		reader.beginArray();

		List<Aggregate> aggregateList = new ArrayList<>();
		aggregateList.add(new YearWiseRegistrationAggregate());
		aggregateList.add(new MedianFriendsCountAggregate());
		aggregateList.add(new MedianUserAgeAggregate());
		aggregateList.add(new MeanBalanceAmountAggregate());
		aggregateList.add(new MeanUnreadMessageFemaleGenderAggregate());

		int counter = 0;
		int startRecord = 0;
		int lastCounter = 0;
		while (reader.hasNext()) {
			counter++;
			User user = gson.fromJson(reader, User.class);
			aggregateList.forEach(aggregate -> {
				aggregate.processInput(user);
			});

			if (counter % chunkSize == 0) {
				lastCounter = counter;
				startRecord = counter - chunkSize + 1;
				// aggregateCompletedCount++;
				printAggregate(aggregateList, startRecord, counter);
			}
		}

		if (counter % chunkSize != 0) {
			startRecord = lastCounter + 1;
			printAggregate(aggregateList, startRecord, counter);
		}
		reader.endArray();
	}

	private void printAggregate(List<Aggregate> aggregateList, int startRecord, int counter) {
		System.out.println(String.format("========================================================="));
		System.out.println(String.format("Aggregate summary of records from %d to %d", startRecord, counter));
		System.out.println(String.format("========================================================="));
		aggregateList.forEach(aggregate -> {
			System.out.print(aggregate.toString());
			aggregate.reset();
		});
	}
}