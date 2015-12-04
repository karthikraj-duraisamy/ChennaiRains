package net.karthikraj.apps.chennairains.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class Shelters implements Parcelable {

    private String timeStamp, area, acoomadationNumbers, socialProfiles, contactNumber, originalSource, others, coloumnID;

    public Shelters() {
        this.timeStamp = "";
        this.area = "";
        this.acoomadationNumbers = "";
        this.socialProfiles = "";
        this.contactNumber = "";
        this.originalSource = "";
        this.others = "";
        this.coloumnID = "";
    }

    public Shelters(String timeStamp, String area, String acoomadationNumbers, String socialProfiles, String contactNumber, String originalSource, String others, String coloumnID){
        this.timeStamp = timeStamp;
        this.area = area;
        this.acoomadationNumbers = acoomadationNumbers;
        this.socialProfiles = socialProfiles;
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
                this.area,
                this.acoomadationNumbers,
                this.socialProfiles,
                this.contactNumber,
                this.originalSource,
                this.others,
                this.coloumnID});
    }

    public Shelters(Parcel in){
        String[] data = new String[7];

        in.readStringArray(data);
        this.timeStamp = data[0];
        this.area = data[1];
        this.acoomadationNumbers= data[2];
        this.socialProfiles = data[3];
        this.contactNumber = data[4];
        this.originalSource = data[5];
        this.others = data[6];
        this.coloumnID = data[7];
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Shelters createFromParcel(Parcel in) {
            return new Shelters(in);
        }

        public Shelters[] newArray(int size) {
            return new Shelters[size];
        }
    };


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAcoomadationNumbers() {
        return acoomadationNumbers;
    }

    public void setAcoomadationNumbers(String acoomadationNumbers) {
        this.acoomadationNumbers = acoomadationNumbers;
    }

    public String getSocialProfiles() {
        return socialProfiles;
    }

    public void setSocialProfiles(String socialProfiles) {
        this.socialProfiles = socialProfiles;
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
