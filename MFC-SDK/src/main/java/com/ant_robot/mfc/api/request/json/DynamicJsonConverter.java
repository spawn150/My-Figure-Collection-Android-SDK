package com.ant_robot.mfc.api.request.json;

import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by spawn on 15/12/16.
 */
public class DynamicJsonConverter implements Converter<ResponseBody, Object> {

    private Type type;

    public DynamicJsonConverter(Type type) {
        this.type = type;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        String string = StringUtils.replace(value.string(), "{}", "null");

        if (String.class.equals(type)) {
            return string;
        } else {
            return new GsonBuilder()
                    .setDateFormat(getDateFormat())
                    .create().fromJson(string, type); // convert to the supplied type, typically Object, JsonObject or Map<String, Object>
        }
    }

    protected String getDateFormat() {
        return "yyyy-MM-dd";
    }
}
