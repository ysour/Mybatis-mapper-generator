package com.linkin.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkin.models.Association;

public class DeepCopy {

	@SuppressWarnings("unchecked")
	public static Association copyAssociation(Association obj) {
		if (obj == null)
			return null;

		Gson gson = new Gson();
		Association t = gson.fromJson(gson.toJson(obj),
				new TypeToken<Association>() {
				}.getType());
		return t;
	}
}
