package com.prod.realtimecurrencycalc.utils;

import com.google.gson.annotations.SerializedName;
import com.prod.realtimecurrencycalc.datasource.model.Rates;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {

    public static Map<String, Double> getRatesFieldsMap(Rates rates) {
        Map<String, Double> currenciesMap = new HashMap<>();
        Field[] fields = rates.getClass().getDeclaredFields();
        for (Field field : fields) {
            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            if (serializedName != null) {
                String annotationValue = serializedName.value();
                Double fieldName = null;
                try {
                    if (field.isAccessible())
                        fieldName = (Double) field.get(rates);
                    else {
                        field.setAccessible(true);
                        fieldName = (Double) field.get(rates);
                        field.setAccessible(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                currenciesMap.put(annotationValue, fieldName);
            }
        }
        return currenciesMap;
    }
}