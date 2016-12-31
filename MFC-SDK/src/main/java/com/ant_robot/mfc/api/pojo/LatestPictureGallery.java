package com.ant_robot.mfc.api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestPictureGallery extends BestPictureGallery implements Parcelable {

    @SerializedName("pictures")
    @Expose
    private BestGallery gallery;

    public BestGallery getGallery() {
        return gallery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LatestPictureGallery that = (LatestPictureGallery) o;

        return gallery != null ? gallery.equals(that.gallery) : that.gallery == null;

    }

    @Override
    public int hashCode() {
        return gallery != null ? gallery.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LatestPictureGallery{" +
                "name=" + name +
                "version=" + version +
                "gallery=" + gallery +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.gallery, flags);
        dest.writeString(this.name);
        dest.writeString(this.version);
    }

    public LatestPictureGallery() {
    }

    protected LatestPictureGallery(Parcel in) {
        this.gallery = in.readParcelable(BestGallery.class.getClassLoader());
        this.name = in.readString();
        this.version = in.readString();
    }

    public static final Creator<LatestPictureGallery> CREATOR = new Creator<LatestPictureGallery>() {
        @Override
        public LatestPictureGallery createFromParcel(Parcel source) {
            return new LatestPictureGallery(source);
        }

        @Override
        public LatestPictureGallery[] newArray(int size) {
            return new LatestPictureGallery[size];
        }
    };
}