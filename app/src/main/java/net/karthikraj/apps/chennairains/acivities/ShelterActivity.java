package net.karthikraj.apps.chennairains.acivities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import net.karthikraj.apps.chennairains.models.Shelters;
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
public class ShelterActivity  extends BaseActivity implements SearchView.OnQueryTextListener{

    private static final int MODE_SHELTER = 1;
    private int mMode = MODE_SHELTER;

    @Override
    protected int getSelfNavDrawerItem() {
        return mMode == MODE_SHELTER ? NAVDRAWER_ITEM_SHELTER : NAVDRAWER_ITEM_INVALID;

    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    private ArrayList<Shelters> mShelters = new ArrayList<>();

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
            mShelters.clear();
            mShelters = DButils.readSheltersData(this);
            mAdapter.notifyDataSetChanged();
        }

        final List<Shelters> filteredModelList = filter(mShelters, query);
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


    private List<Shelters> filter(List<Shelters> models, String query) {
        query = query.toLowerCase();

        final List<Shelters> filteredModelList = new ArrayList<>();
        for (Shelters model : models) {
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
                makeAPICallToFetchShelters();
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

        mShelters = DButils.readSheltersData(this);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        if(mShelters.isEmpty())
        {
            makeAPICallToFetchShelters();
        }


    }


    private void makeAPICallToFetchShelters() {
        if(Utils.isConnectedToInternet(ShelterActivity.this)) {
            progressDialog = ProgressDialog.show(ShelterActivity.this, "", "Fetching Data...", true);

            new DownloadTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    mShelters.clear();
                    mAdapter.notifyDataSetChanged();
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    DButils.removeAllShelters(ShelterActivity.this);
                    new ReadJSONDataTask().execute(object);



                }
            }).execute(Cons.SHELTERS_URL);
        }
        else
            Toast.makeText(ShelterActivity.this, "Internet Disabled!", Toast.LENGTH_SHORT).show();
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder


        public class ViewHolder extends RecyclerView.ViewHolder{
            // each data item is just a string in this case
            public TextView content, numberValue;
            public ViewHolder(View v) {
                super(v);
                content = (TextView) v.findViewById(R.id.textViewListItem);
                numberValue = (TextView) v.findViewById(R.id.textViewListItemNumber);

                numberValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:+" + numberValue.getText().toString().trim()));
                        // Here, thisActivity is the current activity
                        if (ContextCompat.checkSelfPermission(ShelterActivity.this,
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Should we show an explanation?
                            if (ActivityCompat.shouldShowRequestPermissionRationale(ShelterActivity.this,
                                    Manifest.permission.CALL_PHONE)) {
                                // Show an expanation to the user *asynchronously* -- don't block
                                // this thread waiting for the user's response! After the user
                                // sees the explanation, try again to request the permission.
                            } else {
                                // No explanation needed, we can request the permission.
                                ActivityCompat.requestPermissions(ShelterActivity.this,
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
                    .inflate(R.layout.list_items, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.content.setText("Time reported : "+mShelters.get(position).getTimeStamp()+"\nNo.of People : "+
                    mShelters.get(position).getAcoomadationNumbers()+"\nArea : "+mShelters.get(position).getArea());
            holder.numberValue.setText(mShelters.get(position).getContactNumber());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(ShelterActivity.this, SheltersDetailActivity.class);
                    i.putExtra("colID", mShelters.get((int) view.getTag()).getColoumnID());
                    startActivity(i);

                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mShelters.size();
        }





        public void animateTo(List<Shelters> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<Shelters> newModels) {
            for (int i = mShelters.size() - 1; i >= 0; i--) {
                final Shelters model = mShelters.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<Shelters> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final Shelters model = newModels.get(i);
                if (!mShelters.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<Shelters> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final Shelters model = newModels.get(toPosition);
                final int fromPosition = mShelters.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public Shelters removeItem(int position) {
            final Shelters model = mShelters.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, Shelters model) {
            mShelters.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final Shelters model = mShelters.remove(fromPosition);
            mShelters.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

    }



    private void processJson(JSONObject jsonObject)
    {
        try{

            JSONArray articlesArray = jsonObject.getJSONArray("rows");
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONArray jsonArray = articlesArray.getJSONArray(i);
                Shelters item = new Shelters();
                item.setTimeStamp(jsonArray.getString(0));
                item.setArea(jsonArray.getString(1));
                item.setAcoomadationNumbers(jsonArray.getString(2));
                item.setSocialProfiles(jsonArray.getString(3));
                item.setContactNumber(jsonArray.getString(4));
                item.setOriginalSource(jsonArray.getString(5));
                item.setOthers(jsonArray.getString(6));


                DButils.insertShelters(ShelterActivity.this, item);
            }
            mShelters = DButils.readSheltersData(ShelterActivity.this);

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
            progressDialog = ProgressDialog.show(ShelterActivity.this, "", "Reading Data...", true);

            super.onPreExecute();
        }
    }

}