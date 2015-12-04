package net.karthikraj.apps.chennairains.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class AidNeeded implements Parcelable {

    private String timeStamp, name, whatKind, area, contactNumber, originalSource, others, coloumnID;

    public AidNeeded() {
        this.timeStamp = "";
        this.name = "";
        this.whatKind = "";
        this.area = "";
        this.contactNumber = "";
        this.originalSource = "";
        this.others = "";
        this.coloumnID = "";
    }

    public AidNeeded(String timeStamp,String name,String whatKind,String area,String contactNumber,String originalSource,String others,String coloumnID){
        this.timeStamp = timeStamp;
        this.name = name;
        this.whatKind = whatKind;
        this.area = area;
        this.contactNumber = contactNumber;
        this.originalSource = originalSource;
        this.others = others;
        this.coloumnID = coloumnID;
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.timeStamp,
        this.name,
        this.whatKind,
        this.area,
        this.contactNumber,
        this.originalSource,
        this.others,
        this.coloumnID});
    }

    public AidNeeded(Parcel in){
        String[] data = new String[8];

        in.readStringArray(data);
        this.timeStamp = data[0];
        this.name = data[1];
        this.whatKind = data[2];
        this.area = data[3];
        this.contactNumber = data[4];
        this.originalSource = data[5];
        this.others = data[6];
        this.coloumnID = data[7];
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AidNeeded createFromParcel(Parcel in) {
            return new AidNeeded(in);
        }

        public AidNeeded[] newArray(int size) {
            return new AidNeeded[size];
        }
    };

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhatKind() {
        return whatKind;
    }

    public void setWhatKind(String whatKind) {
        this.whatKind = whatKind;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOriginalSource() {
        return originalSource;
    }

    public void setOriginalSource(String originalSource) {
        this.originalSource = originalSource;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getColoumnID() {
        return coloumnID;
    }

    public void setColoumnID(String coloumnID) {
        this.coloumnID = coloumnID;
    }
}
