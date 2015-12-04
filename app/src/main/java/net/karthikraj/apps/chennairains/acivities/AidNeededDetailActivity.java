package net.karthikraj.apps.chennairains.acivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import net.karthikraj.apps.chennairains.R;
import net.karthikraj.apps.chennairains.database.DButils;
import net.karthikraj.apps.chennairains.models.AidNeeded;
import net.karthikraj.apps.chennairains.models.Shelters;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class AidNeededDetailActivity extends BaseActivity{


    TextView contentValue, numberValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_text);


        numberValue = (TextView) findViewById(R.id.textViewDetailItemNumber);
        contentValue = (TextView) findViewById(R.id.textViewDetailItem);

        numberValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+" + numberValue.getText().toString().trim()));
                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(AidNeededDetailActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(AidNeededDetailActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(AidNeededDetailActivity.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                0);
                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }
                startActivity(callIntent );
            }
        });


        if(getIntent().getStringExtra("colID") != null && !getIntent().getStringExtra("colID").isEmpty()) {
            AidNeeded aidNeeded = DButils.readAidNeededData(AidNeededDetailActivity.this, getIntent().getStringExtra("colID"));
//timeStamp, name, whatKind, area, contactNumber, originalSource, others, coloumnID;
            contentValue.setText("Reported at : "+aidNeeded.getTimeStamp()+"\n"+
                    "Name : "+aidNeeded.getName()+"\n"+
                    "What kind of : "+aidNeeded.getWhatKind()+"\n"+
                    "Area : "+aidNeeded.getArea()+"\n"+
                    "Original source : "+aidNeeded.getOriginalSource()+"\n"+
                    "Others/Comments : "+aidNeeded.getOthers()+"\n");
            numberValue.setText("Contact : "+aidNeeded.getContactNumber());
        }

    }

    @Override
    protected int getSelfNavDrawerItem() {
        Toolbar toolbar = getActionBarToolbar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return NAVDRAWER_ITEM_INVALID;
    }

}
