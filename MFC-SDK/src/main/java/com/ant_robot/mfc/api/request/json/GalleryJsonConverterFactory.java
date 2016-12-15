package com.ant_robot.mfc.api.request.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by spawn on 15/12/16.
 */
public class GalleryJsonConverterFactory extends DynamicJsonConverterFactory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GalleryJsonConverter(type);
    }
}
