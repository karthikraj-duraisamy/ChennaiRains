package net.karthikraj.apps.chennairains.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class AidAvailable implements Parcelable {

    private String timeStamp, contactNumber, whatKind, noOfPeople, area, originalSource, name, others, coloumnID;

    public AidAvailable() {
        this.timeStamp = "";
        this.contactNumber = "";
        this.whatKind = "";
        this.noOfPeople = "";
        this.area = "";
        this.originalSource = "";
        this.name = "";
        this.others = "";
        this.coloumnID = "";
    }

    public AidAvailable(String timeStamp,String  contactNumber,String  whatKind,String  noOfPeople,String  area,String  originalSource,String  name,String  others,String  coloumnID){
        this.timeStamp = timeStamp;
        this.contactNumber = contactNumber;
        this.whatKind = whatKind;
        this.noOfPeople = noOfPeople;
        this.area = area;
        this.originalSource = originalSource;
        this.name = name;
        this.others =others;
        this.coloumnID = coloumnID;
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.timeStamp,
        this.contactNumber,
        this.whatKind,
        this.noOfPeople,
        this.area,
        this.originalSource,
        this.name,
        this.others,
        this.coloumnID});
    }

    public AidAvailable(Parcel in){
        String[] data = new String[9];

        in.readStringArray(data);
        this.timeStamp = data[0];
        this.contactNumber = data[1];
        this.whatKind = data[2];
        this.noOfPeople = data[3];
        this.area = data[4];
        this.originalSource = data[5];
        this.name = data[6];
        this.others = data[7];
        this.coloumnID = data[8];
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AidAvailable createFromParcel(Parcel in) {
            return new AidAvailable(in);
        }

        public AidAvailable[] newArray(int size) {
            return new AidAvailable[size];
        }
    };


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWhatKind() {
        return whatKind;
    }

    public void setWhatKind(String whatKind) {
        this.whatKind = whatKind;
    }

    public String getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(String noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOriginalSource() {
        return originalSource;
    }

    public void setOriginalSource(String originalSource) {
        this.originalSource = originalSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
