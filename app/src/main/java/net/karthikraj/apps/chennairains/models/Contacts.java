package net.karthikraj.apps.chennairains.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */

public class Contacts implements Parcelable {

    private String service, area, contactName, contactNumber, otherDetails, lastUpdated, coloumnID;

    public Contacts() {
        this.service = "";
        this.area = "";
        this.contactName = "";
        this.contactNumber = "";
        this.otherDetails = "";
        this.lastUpdated = "";
        this.coloumnID = "";
    }

    public Contacts(String service, String area, String contactName, String contactNumber, String otherDetails, String lastUpdated, String coloumnID){
        this.service = service;
        this.area = area;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.otherDetails = otherDetails;
        this.lastUpdated = lastUpdated;
        this.coloumnID = coloumnID;
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.service,
                this.area,
                this.contactName,
                this.contactNumber,
                this.otherDetails,
                this.lastUpdated,
                this.coloumnID});
    }

    public Contacts(Parcel in){
        String[] data = new String[7];

        in.readStringArray(data);
        this.service = data[0];
        this.area = data[1];
        this.contactName = data[2];
        this.contactNumber = data[3];
        this.otherDetails = data[4];
        this.lastUpdated = data[5];
        this.coloumnID = data[6];
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getColoumnID() {
        return coloumnID;
    }

    public void setColoumnID(String coloumnID) {
        this.coloumnID = coloumnID;
    }
}
