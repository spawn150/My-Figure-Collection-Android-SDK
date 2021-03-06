package com.ant_robot.mfc.api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BestGallery implements Parcelable {

    @Expose
    private String count;
    @SerializedName("picture")
    @Expose
    private List<Picture> pictures = new ArrayList<Picture>();

    public String getCount() {
        return count;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BestGallery that = (BestGallery) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        return pictures != null ? pictures.equals(that.pictures) : that.pictures == null;

    }

    @Override
    public int hashCode() {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BestGallery{" +
                "count='" + count + '\'' +
                ", pictures=" + pictures +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.count);
        dest.writeTypedList(this.pictures);
    }

    public BestGallery() {
    }

    protected BestGallery(Parcel in) {
        this.count = in.readString();
        this.pictures = in.createTypedArrayList(Picture.CREATOR);
    }

    public static final Creator<BestGallery> CREATOR = new Creator<BestGallery>() {
        @Override
        public BestGallery createFromParcel(Parcel source) {
            return new BestGallery(source);
        }

        @Override
        public BestGallery[] newArray(int size) {
            return new BestGallery[size];
        }
    };
}