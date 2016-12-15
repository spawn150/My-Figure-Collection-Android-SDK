package com.ant_robot.mfc.api.request.json;

import java.lang.reflect.Type;

/**
 * Created by spawn on 15/12/16.
 */
public class GalleryJsonConverter extends DynamicJsonConverter {

    public GalleryJsonConverter(Type type) {
        super(type);
    }

    @Override
    protected String getDateFormat() {
        return "EEE, dd MMM yyyy HH:mm:ss Z";
    }

}
