package net.karthikraj.apps.chennairains.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class Donate implements Parcelable {

    private String organizationName, donationURL, donationCurrency, others, verifiedBy, cloumnID;

    public Donate() {
        this.organizationName = "";
        this.donationURL = "";
        this.donationCurrency = "";
        this.others = "";
        this.verifiedBy = "";
        this.cloumnID = "";
    }

    public Donate(String organizationName,String donationURL,String donationCurrency,String others,String verifiedBy,String cloumnID){
        this.organizationName = organizationName;
        this.donationURL = donationURL;
        this.donationCurrency = donationCurrency;
        this.others = others;
        this.verifiedBy = verifiedBy;
        this.cloumnID = cloumnID;
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.organizationName,
        this.donationURL,
        this.donationCurrency,
        this.others,
        this.verifiedBy,
        this.cloumnID});
    }

    public Donate(Parcel in){
        String[] data = new String[6];

        in.readStringArray(data);
        this.organizationName = data[0];
        this.donationURL = data[1];
        this.donationCurrency = data[2];
        this.others = data[3];
        this.verifiedBy = data[4];
        this.cloumnID = data[5];
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Donate createFromParcel(Parcel in) {
            return new Donate(in);
        }

        public Donate[] newArray(int size) {
            return new Donate[size];
        }
    };


    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDonationURL() {
        return donationURL;
    }

    public void setDonationURL(String donationURL) {
        this.donationURL = donationURL;
    }

    public String getDonationCurrency() {
        return donationCurrency;
    }

    public void setDonationCurrency(String donationCurrency) {
        this.donationCurrency = donationCurrency;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getCloumnID() {
        return cloumnID;
    }

    public void setCloumnID(String cloumnID) {
        this.cloumnID = cloumnID;
    }
}
