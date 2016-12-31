package com.ant_robot.mfc.api.pojo;

import com.google.gson.annotations.Expose;

public abstract class BestPictureGallery {

    @Expose
    String name;
    @Expose
    String version;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

}