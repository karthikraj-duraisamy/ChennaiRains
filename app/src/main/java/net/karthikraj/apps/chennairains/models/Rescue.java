package net.karthikraj.apps.chennairains.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class Rescue implements Parcelable {

    private String timeStamp, contactNumber, noOfPeople, infantWomChildren, area, typeOfEmerg, originalSource, severity, notes, latitude, longtitude, mapsURL, claimedBy,coloumnID;

    public Rescue() {
        this.timeStamp = "";
        this.contactNumber = "";
        this.noOfPeople = "";
        this.infantWomChildren = "";
        this.area = "";
        this.typeOfEmerg = "";
        this.originalSource = "";
        this.severity = "";
        this.notes = "";
        this.latitude = "";
        this.longtitude = "";
        this.mapsURL = "";
        this.claimedBy = "";
        this.coloumnID = "";
    }

    public Rescue(String timeStamp,String contactNumber,String noOfPeople,String infantWomChildren,String area,String typeOfEmerg,String originalSource,String severity,String notes,String latitude,String longtitude,String mapsURL,String claimedBy,String coloumnID){
        this.timeStamp = timeStamp;
        this.contactNumber = contactNumber;
        this.noOfPeople = noOfPeople;
        this.infantWomChildren = infantWomChildren;
        this.area = area;
        this.typeOfEmerg = typeOfEmerg;
        this.originalSource = originalSource;
        this.severity = severity;
        this.notes = notes;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.mapsURL = mapsURL;
        this.claimedBy = claimedBy;
        this.coloumnID = coloumnID;
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.timeStamp,
        this.contactNumber,
        this.noOfPeople,
        this.infantWomChildren,
        this.area,
        this.typeOfEmerg,
        this.originalSource,
        this.severity,
        this.notes,
        this.latitude,
        this.longtitude,
        this.mapsURL,
        this.claimedBy,
        this.coloumnID});
    }

    public Rescue(Parcel in){
        String[] data = new String[14];

        in.readStringArray(data);
        this.timeStamp = data[0];
        this.contactNumber = data[1];
        this.noOfPeople = data[2];
        this.infantWomChildren = data[3];
        this.area = data[4];
        this.typeOfEmerg = data[5];
        this.originalSource = data[6];
        this.severity = data[7];
        this.notes = data[8];
        this.latitude = data[9];
        this.longtitude = data[10];
        this.mapsURL = data[11];
        this.claimedBy = data[12];
        this.coloumnID = data[13]; }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Rescue createFromParcel(Parcel in) {
            return new Rescue(in);
        }

        public Rescue[] newArray(int size) {
            return new Rescue[size];
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

    public String getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(String noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public String getInfantWomChildren() {
        return infantWomChildren;
    }

    public void setInfantWomChildren(String infantWomChildren) {
        this.infantWomChildren = infantWomChildren;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTypeOfEmerg() {
        return typeOfEmerg;
    }

    public void setTypeOfEmerg(String typeOfEmerg) {
        this.typeOfEmerg = typeOfEmerg;
    }

    public String getOriginalSource() {
        return originalSource;
    }

    public void setOriginalSource(String originalSource) {
        this.originalSource = originalSource;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getMapsURL() {
        return mapsURL;
    }

    public void setMapsURL(String mapsURL) {
        this.mapsURL = mapsURL;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(String claimedBy) {
        this.claimedBy = claimedBy;
    }

    public String getColoumnID() {
        return coloumnID;
    }

    public void setColoumnID(String coloumnID) {
        this.coloumnID = coloumnID;
    }
}
