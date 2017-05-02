package com.aessense.agm.sensorhistory.util;

import java.util.ArrayList;
import java.util.List;

import com.aessense.agm.sensorhistory.model.SensorType;

/**
 * Utility class for working with SensorType enums.
 * @author John Long
 *
 */
public class SensorTypeHelper {
	
	private SensorTypeHelper() {
		// Hiding default constructor
	}

	/**
	 * Given an array of strings return a list of SensorType enums.
	 */
	public static List<SensorType> stringArrayToEnumList(String[] types) {
		List<SensorType> enums = new ArrayList<>();
		
		if(null != types && types.length > 0) {
			for(String s : types) {
				enums.add(SensorType.valueOf(s));
			}
		}
		
		return enums;
	}
	
	/**
	 * Given a list SensorType enums, return an array of ID's.
	 */
	public static int[] enumListToIntArrayOfIds(List<SensorType> enums) {
		
		if(null == enums || enums.isEmpty() ) {
			return new int[0];
		}
		
		int size = enums.size();
		int[] ids = new int[size];
		
		for(int i = 0; i < size; i++) {
			ids[i] = enums.get(i).getId();
		}
		
		return ids;
	}
}
