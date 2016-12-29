package com.ant_robot.mfc.api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestPictureGallery implements Parcelable {

    @Expose
    private String name;
    @Expose
    private String version;
    @SerializedName("potd")
    @Expose
    private PodGallery gallery;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public PodGallery getGallery() {
        return gallery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BestPictureGallery that = (BestPictureGallery) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        return gallery != null ? gallery.equals(that.gallery) : that.gallery == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (gallery != null ? gallery.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BestPictureGallery{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", gallery=" + gallery +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.version);
        dest.writeParcelable(this.gallery, flags);
    }

    public BestPictureGallery() {
    }

    protected BestPictureGallery(Parcel in) {
        this.name = in.readString();
        this.version = in.readString();
        this.gallery = in.readParcelable(PodGallery.class.getClassLoader());
    }

    public static final Creator<BestPictureGallery> CREATOR = new Creator<BestPictureGallery>() {
        @Override
        public BestPictureGallery createFromParcel(Parcel source) {
            return new BestPictureGallery(source);
        }

        @Override
        public BestPictureGallery[] newArray(int size) {
            return new BestPictureGallery[size];
        }
    };
}