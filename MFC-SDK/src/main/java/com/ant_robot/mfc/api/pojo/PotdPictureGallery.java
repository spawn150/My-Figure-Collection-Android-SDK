package com.ant_robot.mfc.api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PotdPictureGallery extends BestPictureGallery implements Parcelable {

    @SerializedName("potd")
    @Expose
    private BestGallery gallery;

    public BestGallery getGallery() {
        return gallery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PotdPictureGallery that = (PotdPictureGallery) o;

        return gallery != null ? gallery.equals(that.gallery) : that.gallery == null;

    }

    @Override
    public int hashCode() {
        return gallery != null ? gallery.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PotdPictureGallery{" +
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

    public PotdPictureGallery() {
    }

    protected PotdPictureGallery(Parcel in) {
        this.gallery = in.readParcelable(BestGallery.class.getClassLoader());
        this.name = in.readString();
        this.version = in.readString();
    }

    public static final Parcelable.Creator<PotdPictureGallery> CREATOR = new Parcelable.Creator<PotdPictureGallery>() {
        @Override
        public PotdPictureGallery createFromParcel(Parcel source) {
            return new PotdPictureGallery(source);
        }

        @Override
        public PotdPictureGallery[] newArray(int size) {
            return new PotdPictureGallery[size];
        }
    };
}