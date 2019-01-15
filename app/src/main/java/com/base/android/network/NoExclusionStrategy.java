package com.base.android.network;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.List;

/**
 * Created by NguyenLinh on 15,January,2019
 *  String data = new GsonBuilder()
 *                 .addSerializationExclusionStrategy(new NoExclusionStrategy(arrFieldRemove))
 *                 .create()
 *                 .toJson(experienceRequest);
 */
public class NoExclusionStrategy implements ExclusionStrategy {
    private List<String> arrFieldName;

    public NoExclusionStrategy(List<String> arrFieldName) {
        this.arrFieldName = arrFieldName;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return arrFieldName.contains(f.getName().toLowerCase());
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}

