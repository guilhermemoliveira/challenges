package com.guilhermemartinsdeoliveira.app.utils;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

public class GsonUtils {

	private GsonUtils() {

	}

	public static final <T> ArrayList<T> fromJsonList(final Class<T[]> clazz, final String json, Gson gson) {
		final T[] jsonToObject = gson.fromJson(json, clazz);

		return new ArrayList<>(Arrays.asList(jsonToObject));
	}

}
