package net.karthikraj.apps.chennairains.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import net.karthikraj.apps.chennairains.models.AidAvailable;
import net.karthikraj.apps.chennairains.models.AidNeeded;
import net.karthikraj.apps.chennairains.models.Contacts;
import net.karthikraj.apps.chennairains.models.Rescue;
import net.karthikraj.apps.chennairains.models.Shelters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class DButils {




    public static void insertContacts(Context context, Contacts contacts)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ContactsTable.CON_SERVICE, contacts.getService());
        initialValues.put(ContactsTable.CON_AREA, contacts.getArea());
        initialValues.put(ContactsTable.CON_NAME, contacts.getContactName());
        initialValues.put(ContactsTable.CON_NUMBER, contacts.getContactNumber());
        initialValues.put(ContactsTable.CON_OTHERS, contacts.getOtherDetails());
        initialValues.put(ContactsTable.CON_LAST_UPDATED, contacts.getLastUpdated());
/*
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH MM SS");
        String currentDateandTime = sdf.format(new Date());
*/
        context.getContentResolver().insert(ContactsContentProvider.CONTENT_URI,
                initialValues);
    }


    public static ArrayList<Contacts> readContactsData(Context context)
    {
        ArrayList<Contacts> returnArrayList = new ArrayList<>();
        try {
            Cursor contactsDataCursor = context.getContentResolver().query(ContactsContentProvider.CONTENT_URI, null, null, null, null);
            if (contactsDataCursor != null && contactsDataCursor.getCount() > 0) {

                while (contactsDataCursor.moveToNext()) {

                    Contacts contacts = new Contacts();
                    contacts.setColoumnID(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.COLUMN_ID)));

                    contacts.setService(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_SERVICE)));
                    contacts.setArea(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_AREA)));
                    contacts.setContactName(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_NAME)));
                    contacts.setContactNumber(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_NUMBER)));
                    contacts.setOtherDetails(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_OTHERS)));
                    contacts.setLastUpdated(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_LAST_UPDATED)));


                    returnArrayList.add(contacts);

                }
            }
            contactsDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return returnArrayList;
        
    }


    public static Contacts readContactData(Context context, String coloumnID)
    {
        Contacts contactsEntry = new Contacts();
        try {
            Cursor contactsDataCursor = context.getContentResolver().query(ContactsContentProvider.CONTENT_URI, null, ContactsTable.COLUMN_ID + " LIKE ? ", new String[]{coloumnID}, null);
            if (contactsDataCursor != null && contactsDataCursor.getCount() > 0) {

                while (contactsDataCursor.moveToNext()) {

                    contactsEntry.setColoumnID(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.COLUMN_ID)));

                    contactsEntry.setService(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_SERVICE)));
                    contactsEntry.setArea(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_AREA)));
                    contactsEntry.setContactName(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_NAME)));
                    contactsEntry.setContactNumber(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_NUMBER)));
                    contactsEntry.setOtherDetails(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_OTHERS)));
                    contactsEntry.setLastUpdated(contactsDataCursor.getString(contactsDataCursor
                            .getColumnIndexOrThrow(ContactsTable.CON_LAST_UPDATED)));
                }
            }
            contactsDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return contactsEntry;

    }


    public static void removeAllContacts(Context context)
    {
        context.getContentResolver().delete(ContactsContentProvider.CONTENT_URI, null, null);
    }

    public static void removeContactsEntry(Context context, String coloumnID)
    {
        context.getContentResolver().delete(ContactsContentProvider.CONTENT_URI, ContactsTable.COLUMN_ID + " LIKE ? ", new String[]{coloumnID});
    }




    //Shelters


    public static void insertShelters(Context context, Shelters shelters)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(SheltersTable.SHELT_TIMESTAMP, shelters.getTimeStamp());
        initialValues.put(SheltersTable.SHELT_AREA, shelters.getArea());
        initialValues.put(SheltersTable.SHELT_ACCOM_NUMBERS, shelters.getAcoomadationNumbers());
        initialValues.put(SheltersTable.SHELT_SOCIAL_PROFILE, shelters.getSocialProfiles());
        initialValues.put(SheltersTable.SHELT_CONT_NUMBER, shelters.getContactNumber());
        initialValues.put(SheltersTable.SHELT_ORIG_SOURCE, shelters.getOriginalSource());
        initialValues.put(SheltersTable.SHELT_OTHERS, shelters.getOthers());
/*
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH MM SS");
        String currentDateandTime = sdf.format(new Date());
*/
        context.getContentResolver().insert(SheltersContentProvider.CONTENT_URI,
                initialValues);
    }


    public static ArrayList<Shelters> readSheltersData(Context context)
    {
        ArrayList<Shelters> returnArrayList = new ArrayList<>();
        try {
            Cursor sheltersDataCursor = context.getContentResolver().query(SheltersContentProvider.CONTENT_URI, null, null, null, null);
            if (sheltersDataCursor != null && sheltersDataCursor.getCount() > 0) {

                while (sheltersDataCursor.moveToNext()) {
                     //timeStamp, area, acoomadationNumbers, socialProfiles, contactNumber, originalSource,
                    // others, coloumnID;
                    Shelters shelters = new Shelters();
                    shelters.setColoumnID(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.COLUMN_ID)));

                    shelters.setTimeStamp(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_TIMESTAMP)));
                    shelters.setArea(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_AREA)));
                    shelters.setAcoomadationNumbers(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_ACCOM_NUMBERS)));
                    shelters.setSocialProfiles(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_SOCIAL_PROFILE)));
                    shelters.setContactNumber(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_CONT_NUMBER)));
                    shelters.setOriginalSource(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_ORIG_SOURCE)));
                    shelters.setOthers(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_ORIG_SOURCE)));


                    returnArrayList.add(shelters);

                }
            }
            sheltersDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return returnArrayList;

    }


    public static Shelters readShelterData(Context context, String coloumnID)
    {
        Shelters shelters = new Shelters();
        try {
            Cursor sheltersDataCursor = context.getContentResolver().query(SheltersContentProvider.CONTENT_URI, null, SheltersTable.COLUMN_ID + " LIKE ? ", new String[]{coloumnID}, null);
            if (sheltersDataCursor != null && sheltersDataCursor.getCount() > 0) {

                while (sheltersDataCursor.moveToNext()) {


                    shelters.setColoumnID(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.COLUMN_ID)));

                    shelters.setTimeStamp(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_TIMESTAMP)));
                    shelters.setArea(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_AREA)));
                    shelters.setAcoomadationNumbers(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_ACCOM_NUMBERS)));
                    shelters.setSocialProfiles(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_SOCIAL_PROFILE)));
                    shelters.setContactNumber(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_CONT_NUMBER)));
                    shelters.setOriginalSource(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_ORIG_SOURCE)));
                    shelters.setOthers(sheltersDataCursor.getString(sheltersDataCursor
                            .getColumnIndexOrThrow(SheltersTable.SHELT_ORIG_SOURCE)));
                }
            }
            sheltersDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return shelters;

    }


    public static void removeAllShelters(Context context)
    {
        context.getContentResolver().delete(SheltersContentProvider.CONTENT_URI, null, null);
    }




    // AidAvailable

    public static void insertAidAvailable(Context context, AidAvailable aidAvailable)
    {
        //timeStamp, contactNumber, whatKind, noOfPeople, area, originalSource, name, others, coloumnID;
        //The columns we'll include in the dictionary table
        ContentValues initialValues = new ContentValues();
        initialValues.put(AidAvailableTable.AIDAV_TIMESTAMP, aidAvailable.getTimeStamp());
        initialValues.put(AidAvailableTable.AIDAV_CONT_NUMBER, aidAvailable.getContactNumber());
        initialValues.put(AidAvailableTable.AIDAV_WHATKIND, aidAvailable.getWhatKind());
        initialValues.put(AidAvailableTable.AIDAV_NO_OF_PEOPLE, aidAvailable.getNoOfPeople());
        initialValues.put(AidAvailableTable.AIDAV_AREA, aidAvailable.getArea());
        initialValues.put(AidAvailableTable.AIDAV_ORIGINAL_SOURCE, aidAvailable.getOriginalSource());
        initialValues.put(AidAvailableTable.AIDAV_NAME, aidAvailable.getName());
        initialValues.put(AidAvailableTable.AIDAV_OTHERS, aidAvailable.getOthers());


        context.getContentResolver().insert(AidAvailableContentProvider.CONTENT_URI,
                initialValues);
    }


    public static ArrayList<AidAvailable> readAidAvailableData(Context context)
    {
        ArrayList<AidAvailable> returnArrayList = new ArrayList<>();
        try {
            Cursor aidAvailableDataCursor = context.getContentResolver().query(AidAvailableContentProvider.CONTENT_URI, null, null, null, null);
            if (aidAvailableDataCursor != null && aidAvailableDataCursor.getCount() > 0) {

                while (aidAvailableDataCursor.moveToNext()) {
                    //timeStamp, contactNumber, whatKind, noOfPeople, area, originalSource, name, others, coloumnID;
                    AidAvailable aidAvailable = new AidAvailable();
                    aidAvailable.setColoumnID(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.COLUMN_ID)));

                    aidAvailable.setTimeStamp(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_TIMESTAMP)));
                    aidAvailable.setContactNumber(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_CONT_NUMBER)));
                    aidAvailable.setWhatKind(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_WHATKIND)));
                    aidAvailable.setNoOfPeople(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_NO_OF_PEOPLE)));
                    aidAvailable.setArea(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_AREA)));
                    aidAvailable.setOriginalSource(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_ORIGINAL_SOURCE)));
                    aidAvailable.setName(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_NAME)));
                    aidAvailable.setOthers(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_OTHERS)));


                    returnArrayList.add(aidAvailable);

                }
            }
            aidAvailableDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return returnArrayList;

    }


    public static AidAvailable readAidAvailableData(Context context, String coloumnID)
    {
        AidAvailable aidAvailable = new AidAvailable();
        try {
            Cursor aidAvailableDataCursor = context.getContentResolver().query(AidAvailableContentProvider.CONTENT_URI, null, AidAvailableTable.COLUMN_ID + " LIKE ? ", new String[]{coloumnID}, null);
            if (aidAvailableDataCursor != null && aidAvailableDataCursor.getCount() > 0) {

                while (aidAvailableDataCursor.moveToNext()) {

                    aidAvailable.setColoumnID(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.COLUMN_ID)));

                    aidAvailable.setTimeStamp(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_TIMESTAMP)));
                    aidAvailable.setContactNumber(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_CONT_NUMBER)));
                    aidAvailable.setWhatKind(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_WHATKIND)));
                    aidAvailable.setNoOfPeople(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_NO_OF_PEOPLE)));
                    aidAvailable.setArea(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_AREA)));
                    aidAvailable.setOriginalSource(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_ORIGINAL_SOURCE)));
                    aidAvailable.setName(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_NAME)));
                    aidAvailable.setOthers(aidAvailableDataCursor.getString(aidAvailableDataCursor
                            .getColumnIndexOrThrow(AidAvailableTable.AIDAV_OTHERS)));
                }
            }
            aidAvailableDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return aidAvailable;

    }


    public static void removeAllAidAvailable(Context context)
    {
        context.getContentResolver().delete(AidAvailableContentProvider.CONTENT_URI, null, null);
    }







    //AidNeeded

    public static void insertAidNeeded(Context context, AidNeeded aidNeeded)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(AidNeededTable.AIDNEED_TIMESTAMP, aidNeeded.getTimeStamp());
        initialValues.put(AidNeededTable.AIDNEED_NAME, aidNeeded.getArea());
        initialValues.put(AidNeededTable.AIDNEED_WHATKIND, aidNeeded.getWhatKind());
        initialValues.put(AidNeededTable.AIDNEED_AREA, aidNeeded.getArea());
        initialValues.put(AidNeededTable.AIDNEED_CONT_NUMBER, aidNeeded.getContactNumber());
        initialValues.put(AidNeededTable.AIDNEED_ORIGINAL_SOURCE, aidNeeded.getOriginalSource());
        initialValues.put(AidNeededTable.AIDNEED_OTHERS, aidNeeded.getOthers());
        context.getContentResolver().insert(AidNeededContentProvider.CONTENT_URI,
                initialValues);
    }


    public static ArrayList<AidNeeded> readAidNeededData(Context context)
    {
        ArrayList<AidNeeded> returnArrayList = new ArrayList<>();
        try {
            Cursor aidNeededDataCursor = context.getContentResolver().query(AidNeededContentProvider.CONTENT_URI, null, null, null, null);
            if (aidNeededDataCursor != null && aidNeededDataCursor.getCount() > 0) {

                while (aidNeededDataCursor.moveToNext()) {
//timeStamp, name, whatKind, area, contactNumber, originalSource, others, coloumnID;
                    AidNeeded aidNeeded = new AidNeeded();
                    aidNeeded.setColoumnID(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.COLUMN_ID)));

                    aidNeeded.setTimeStamp(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_TIMESTAMP)));
                    aidNeeded.setName(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_NAME)));
                    aidNeeded.setWhatKind(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_WHATKIND)));
                    aidNeeded.setArea(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_AREA)));
                    aidNeeded.setContactNumber(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_CONT_NUMBER)));
                    aidNeeded.setOriginalSource(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_ORIGINAL_SOURCE)));
                    aidNeeded.setOthers(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_OTHERS)));


                    returnArrayList.add(aidNeeded);

                }
            }
            aidNeededDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return returnArrayList;

    }


    public static AidNeeded readAidNeededData(Context context, String coloumnID)
    {
        AidNeeded aidNeeded = new AidNeeded();
        try {
            Cursor aidNeededDataCursor = context.getContentResolver().query(AidNeededContentProvider.CONTENT_URI, null, AidNeededTable.COLUMN_ID + " LIKE ? ", new String[]{coloumnID}, null);
            if (aidNeededDataCursor != null && aidNeededDataCursor.getCount() > 0) {

                while (aidNeededDataCursor.moveToNext()) {

                    aidNeeded.setColoumnID(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.COLUMN_ID)));

                    aidNeeded.setTimeStamp(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_TIMESTAMP)));
                    aidNeeded.setName(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_NAME)));
                    aidNeeded.setWhatKind(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_WHATKIND)));
                    aidNeeded.setArea(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_AREA)));
                    aidNeeded.setContactNumber(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_CONT_NUMBER)));
                    aidNeeded.setOriginalSource(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_ORIGINAL_SOURCE)));
                    aidNeeded.setOthers(aidNeededDataCursor.getString(aidNeededDataCursor
                            .getColumnIndexOrThrow(AidNeededTable.AIDNEED_OTHERS)));
                }
            }
            aidNeededDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return aidNeeded;

    }


    public static void removeAllAidNeeded(Context context)
    {
        context.getContentResolver().delete(AidNeededContentProvider.CONTENT_URI, null, null);
    }







    //Rescue

    public static void insertRescue(Context context, Rescue rescue)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(RescueTable.RES_TIMESTAMP, rescue.getTimeStamp());
        initialValues.put(RescueTable.RES_CONTACT_NUMBER, rescue.getContactNumber());
        initialValues.put(RescueTable.RES_NO_OF_PEOPLE, rescue.getNoOfPeople());
        initialValues.put(RescueTable.RES_INFANTWOMEN, rescue.getInfantWomChildren());
        initialValues.put(RescueTable.RES_AREA, rescue.getArea());
        initialValues.put(RescueTable.RES_TYPEOFEMERG, rescue.getTypeOfEmerg());
        initialValues.put(RescueTable.RES_ORIG_SOURCE, rescue.getOriginalSource());
        initialValues.put(RescueTable.RES_SEVERITY, rescue.getSeverity());
        initialValues.put(RescueTable.RES_NOTES, rescue.getNotes());
        initialValues.put(RescueTable.RES_LATITUDE, rescue.getLatitude());
        initialValues.put(RescueTable.RES_LONGITUDE, rescue.getLongtitude());
        initialValues.put(RescueTable.RES_MAPSURL, rescue.getMapsURL());
        initialValues.put(RescueTable.RES_CLAIMEDBY, rescue.getClaimedBy());
        context.getContentResolver().insert(RescueContentProvider.CONTENT_URI,
                initialValues);
    }


    public static ArrayList<Rescue> readRescueData(Context context)
    {
        ArrayList<Rescue> returnArrayList = new ArrayList<>();
        try {
            Cursor rescueDataCursor = context.getContentResolver().query(RescueContentProvider.CONTENT_URI, null, null, null, null);
            if (rescueDataCursor != null && rescueDataCursor.getCount() > 0) {

                while (rescueDataCursor.moveToNext()) {
                    //timeStamp, contactNumber, noOfPeople, infantWomChildren, area, typeOfEmerg,
                    // originalSource, severity, notes, latitude, longtitude, mapsURL, claimedBy,coloumnID;
                    Rescue rescue = new Rescue();
                    rescue.setColoumnID(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.COLUMN_ID)));

                    rescue.setTimeStamp(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_TIMESTAMP)));
                    rescue.setContactNumber(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_CONTACT_NUMBER)));
                    rescue.setNoOfPeople(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_NO_OF_PEOPLE)));
                    rescue.setInfantWomChildren(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_INFANTWOMEN)));
                    rescue.setArea(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_AREA)));
                    rescue.setTypeOfEmerg(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_TYPEOFEMERG)));

                    rescue.setOriginalSource(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_ORIG_SOURCE)));
                    rescue.setSeverity(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_SEVERITY)));
                    rescue.setNotes(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_NOTES)));
                    rescue.setLatitude(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_LATITUDE)));
                    rescue.setLongtitude(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_MAPSURL)));
                    rescue.setMapsURL(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_TYPEOFEMERG)));

                    rescue.setClaimedBy(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_CLAIMEDBY)));


                    returnArrayList.add(rescue);

                }
            }
            rescueDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return returnArrayList;

    }


    public static Rescue readRescueData(Context context, String coloumnID)
    {
        Rescue rescue = new Rescue();
        try {
            Cursor rescueDataCursor = context.getContentResolver().query(RescueContentProvider.CONTENT_URI, null, RescueTable.COLUMN_ID + " LIKE ? ", new String[]{coloumnID}, null);
            if (rescueDataCursor != null && rescueDataCursor.getCount() > 0) {

                while (rescueDataCursor.moveToNext()) {

                    rescue.setColoumnID(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.COLUMN_ID)));

                    rescue.setTimeStamp(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_TIMESTAMP)));
                    rescue.setContactNumber(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_CONTACT_NUMBER)));
                    rescue.setNoOfPeople(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_NO_OF_PEOPLE)));
                    rescue.setInfantWomChildren(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_INFANTWOMEN)));
                    rescue.setArea(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_AREA)));
                    rescue.setTypeOfEmerg(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_TYPEOFEMERG)));

                    rescue.setOriginalSource(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_ORIG_SOURCE)));
                    rescue.setSeverity(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_SEVERITY)));
                    rescue.setNotes(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_NOTES)));
                    rescue.setLatitude(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_LATITUDE)));
                    rescue.setLongtitude(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_MAPSURL)));
                    rescue.setMapsURL(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_TYPEOFEMERG)));

                    rescue.setClaimedBy(rescueDataCursor.getString(rescueDataCursor
                            .getColumnIndexOrThrow(RescueTable.RES_CLAIMEDBY)));
                }
            }
            rescueDataCursor.close();
        }catch(Exception e){e.printStackTrace();}

        return rescue;

    }


    public static void removeAllRescue(Context context)
    {
        context.getContentResolver().delete(RescueContentProvider.CONTENT_URI, null, null);
    }

}
