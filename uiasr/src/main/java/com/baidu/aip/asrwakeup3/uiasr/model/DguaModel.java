package com.baidu.aip.asrwakeup3.uiasr.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DguaModel implements Parcelable {
    private String address;
    private String message;
    private String mishi="Dgua你好!";
    private String tab;
    private int time;
    private int zt=0;



    public DguaModel(String str, String str2, String str3, String str4, int i, int i2) {
        //this.zt = 0;
        this.tab = str;
        this.address = str2;
        this.mishi = str3;
        this.message = str4;
        this.time = i;
        this.zt = i2;
        System.out.println("序列化对象kaishi"+address);


    }

    public String gettab() {
        return this.tab;
    }

    public String getaddress() {
        return this.address;
    }

    public String getmessage() {
        return this.message;
    }

    public int gettime() {
        return this.time;
    }

    public String getmishi() {
        return this.mishi;
    }

    public void setmishi(String str) {
        this.mishi = str;
    }

    public int getzt() {
        return this.zt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(tab);
        dest.writeString(address);
        dest.writeString(mishi);
        dest.writeString(message);
        dest.writeInt(time);
        dest.writeInt(zt);

        System.out.println("fan序列化对象"+address);


    }
    public static final Creator<DguaModel> CREATOR=new Creator<DguaModel>() {
        @Override
        public DguaModel createFromParcel(Parcel source) {
            return new DguaModel(source.readString(),source.readString(),source.readString(),source.readString(),source.readInt(),source.readInt());
        }

        @Override
        public DguaModel[] newArray(int size) {
            return new DguaModel[size];
        }
    };

}
