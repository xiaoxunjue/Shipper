package com.revenant.shipper.bean;
import android.os.Parcel;
import android.os.Parcelable;

import	java.util.Date;

import lombok.Data;

@Data
public class TestFirstBean implements Parcelable {
    private String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    protected TestFirstBean(Parcel in) {
        this.id = in.readString();
    }

    public static final Parcelable.Creator<TestFirstBean> CREATOR = new Parcelable.Creator<TestFirstBean>() {
        @Override
        public TestFirstBean createFromParcel(Parcel source) {
            return new TestFirstBean(source);
        }

        @Override
        public TestFirstBean[] newArray(int size) {
            return new TestFirstBean[size];
        }
    };
}
