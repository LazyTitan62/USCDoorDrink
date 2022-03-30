package com.csci310.uscdoordrink;

import android.os.Parcel;
import android.os.Parcelable;

public class MarkerInfo implements Parcelable {

    float lat;
    float lon;

    @Override
    public int describeContents() {
        return 0;
    }

    // Storing the Movie data to Parcel object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(lat);
        dest.writeFloat(lon);
    }

    // A constructor that initializes the Movie object
    public MarkerInfo(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Retrieving Movie data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private MarkerInfo(Parcel in){
        this.lat = in.readFloat();
        this.lon = in.readFloat();
    }

    public static final Parcelable.Creator<MarkerInfo> CREATOR = new Parcelable.Creator<MarkerInfo>() {
        @Override
        public MarkerInfo createFromParcel(Parcel source) {
            return new MarkerInfo(source);
        }

        @Override
        public MarkerInfo[] newArray(int size) {
            return new MarkerInfo[size];
        }
    };
}
