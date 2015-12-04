package net.karthikraj.apps.chennairains.acivities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.karthikraj.apps.chennairains.R;
import net.karthikraj.apps.chennairains.async.AsyncResult;
import net.karthikraj.apps.chennairains.async.DownloadTask;
import net.karthikraj.apps.chennairains.database.DButils;
import net.karthikraj.apps.chennairains.models.Contacts;
import net.karthikraj.apps.chennairains.utils.Cons;
import net.karthikraj.apps.chennairains.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class ContactsActivity  extends BaseActivity implements SearchView.OnQueryTextListener{

    private static final int MODE_CONTACTS = 0;
    private int mMode = MODE_CONTACTS;

    @Override
    protected int getSelfNavDrawerItem() {
        return mMode == MODE_CONTACTS ? NAVDRAWER_ITEM_CONTACTS : NAVDRAWER_ITEM_INVALID;

    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    private ArrayList<Contacts> mContacts = new ArrayList<>();

    ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search by area...");
        searchView.setOnQueryTextListener(this);
        return true;
    }
    int QUERY_LENGTH = 0;
    @Override
    public boolean onQueryTextChange(String query) {

        if(query.length() <= QUERY_LENGTH)
        {
            mContacts.clear();
            mContacts = DButils.readContactsData(this);
            mAdapter.notifyDataSetChanged();
        }

        final List<Contacts> filteredModelList = filter(mContacts, query);
        mAdapter.animateTo(filteredModelList);
        mRecyclerView.scrollToPosition(0);
        QUERY_LENGTH = query.length();

        if(QUERY_LENGTH <= 0)
            mAdapter.notifyDataSetChanged();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    private List<Contacts> filter(List<Contacts> models, String query) {
        query = query.toLowerCase();

        final List<Contacts> filteredModelList = new ArrayList<>();
        for (Contacts model : models) {
            final String text = model.getArea().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                makeAPICallToFetchContacts();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mRecyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mContacts = DButils.readContactsData(this);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        if(mContacts.isEmpty())
        {
            makeAPICallToFetchContacts();
        }


    }


    private void makeAPICallToFetchContacts() {
        if(Utils.isConnectedToInternet(ContactsActivity.this)) {
            progressDialog = ProgressDialog.show(ContactsActivity.this, "", "Fetching Data...", true);

            new DownloadTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    mContacts.clear();
                    mAdapter.notifyDataSetChanged();
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    DButils.removeAllContacts(ContactsActivity.this);
                    new ReadJSONDataTask().execute(object);



                }
            }).execute(Cons.CONTACTS_URL);
        }
        else
            Toast.makeText(ContactsActivity.this, "Internet Disabled!", Toast.LENGTH_SHORT).show();
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder


        public class ViewHolder extends RecyclerView.ViewHolder{
            // each data item is just a string in this case
            public TextView serviceValue, areaValue, nameValue, numberValue;
            public ViewHolder(View v) {
                super(v);
                serviceValue = (TextView) v.findViewById(R.id.tvContactsServiceValue);
                areaValue = (TextView) v.findViewById(R.id.tvContactsAreaValue);
                nameValue = (TextView) v.findViewById(R.id.tvContactsNameValue);
                numberValue = (TextView) v.findViewById(R.id.tvContactsNumberValue);

                numberValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:+" + numberValue.getText().toString().trim()));




                        // Here, thisActivity is the current activity
                        if (ContextCompat.checkSelfPermission(ContactsActivity.this,
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {

                            // Should we show an explanation?
                            if (ActivityCompat.shouldShowRequestPermissionRationale(ContactsActivity.this,
                                    Manifest.permission.CALL_PHONE)) {

                                // Show an expanation to the user *asynchronously* -- don't block
                                // this thread waiting for the user's response! After the user
                                // sees the explanation, try again to request the permission.

                            } else {

                                // No explanation needed, we can request the permission.

                                ActivityCompat.requestPermissions(ContactsActivity.this,
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
            }
        }


        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            // create a new view

            View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_contacts_items, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.serviceValue.setText(mContacts.get(position).getService());
            holder.areaValue.setText(mContacts.get(position).getArea());
            holder.nameValue.setText(mContacts.get(position).getContactName());
            holder.numberValue.setText(mContacts.get(position).getContactNumber());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(ContactsActivity.this, ContactsDetailActivity.class);
                    i.putExtra("colID", mContacts.get((int) view.getTag()).getColoumnID());
                    startActivity(i);

                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mContacts.size();
        }





        public void animateTo(List<Contacts> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<Contacts> newModels) {
            for (int i = mContacts.size() - 1; i >= 0; i--) {
                final Contacts model = mContacts.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<Contacts> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final Contacts model = newModels.get(i);
                if (!mContacts.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<Contacts> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final Contacts model = newModels.get(toPosition);
                final int fromPosition = mContacts.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public Contacts removeItem(int position) {
            final Contacts model = mContacts.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, Contacts model) {
            mContacts.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final Contacts model = mContacts.remove(fromPosition);
            mContacts.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

    }



    private void processJson(JSONObject jsonObject)
    {
        try{

                JSONArray articlesArray = jsonObject.getJSONArray("rows");
                for (int i = 0; i < articlesArray.length(); i++) {
                    JSONArray jsonArray = articlesArray.getJSONArray(i);
                    Contacts item = new Contacts();
                    item.setService(jsonArray.getString(0));
                    item.setArea(jsonArray.getString(1));
                    item.setContactName(jsonArray.getString(2));
                    item.setContactNumber(jsonArray.getString(3));
                    item.setOtherDetails(jsonArray.getString(4));
                    item.setLastUpdated(jsonArray.getString(5));


                    DButils.insertContacts(ContactsActivity.this, item);
                }
            mContacts = DButils.readContactsData(ContactsActivity.this);

            }catch (JSONException je){je.printStackTrace();}
        }




    class ReadJSONDataTask extends AsyncTask<JSONObject, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(JSONObject... params) {
            processJson(params[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();

            mAdapter.notifyDataSetChanged();
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ContactsActivity.this, "", "Reading Data...", true);

            super.onPreExecute();
        }
    }

}
