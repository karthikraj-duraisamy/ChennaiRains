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
import net.karthikraj.apps.chennairains.models.AidNeeded;
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
public class AidNeededActivity  extends BaseActivity implements SearchView.OnQueryTextListener{

    private static final int MODE_AID_NEEDED = 3;
    private int mMode = MODE_AID_NEEDED;

    @Override
    protected int getSelfNavDrawerItem() {
        return mMode == MODE_AID_NEEDED ? NAVDRAWER_ITEM_AID_NEEDED : NAVDRAWER_ITEM_INVALID;

    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    private ArrayList<AidNeeded> aidNeededs = new ArrayList<>();

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
            aidNeededs.clear();
            aidNeededs = DButils.readAidNeededData(this);
            mAdapter.notifyDataSetChanged();
        }

        final List<AidNeeded> filteredModelList = filter(aidNeededs, query);
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


    private List<AidNeeded> filter(List<AidNeeded> models, String query) {
        query = query.toLowerCase();

        final List<AidNeeded> filteredModelList = new ArrayList<>();
        for (AidNeeded model : models) {
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
                makeAPICallToFetchData();
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

        aidNeededs = DButils.readAidNeededData(this);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        if(aidNeededs.isEmpty())
        {
            makeAPICallToFetchData();
        }


    }


    private void makeAPICallToFetchData() {
        if(Utils.isConnectedToInternet(AidNeededActivity.this)) {
            progressDialog = ProgressDialog.show(AidNeededActivity.this, "", "Fetching Data...", true);

            new DownloadTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    aidNeededs.clear();
                    mAdapter.notifyDataSetChanged();
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    DButils.removeAllAidNeeded(AidNeededActivity.this);
                    new ReadJSONDataTask().execute(object);



                }
            }).execute(Cons.AID_NEEDED_URL);
        }
        else
            Toast.makeText(AidNeededActivity.this, "Internet Disabled!", Toast.LENGTH_SHORT).show();
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
                        if (ContextCompat.checkSelfPermission(AidNeededActivity.this,
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Should we show an explanation?
                            if (ActivityCompat.shouldShowRequestPermissionRationale(AidNeededActivity.this,
                                    Manifest.permission.CALL_PHONE)) {
                                // Show an expanation to the user *asynchronously* -- don't block
                                // this thread waiting for the user's response! After the user
                                // sees the explanation, try again to request the permission.
                            } else {
                                // No explanation needed, we can request the permission.
                                ActivityCompat.requestPermissions(AidNeededActivity.this,
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

            holder.content.setText("Time reported : " + aidNeededs.get(position).getTimeStamp() + "\nArea : " +
                    aidNeededs.get(position).getArea() + "\nWhat Kind of : " + aidNeededs.get(position).getWhatKind());
            holder.numberValue.setText(aidNeededs.get(position).getContactNumber());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(AidNeededActivity.this, AidNeededDetailActivity.class);
                    i.putExtra("colID", aidNeededs.get((int) view.getTag()).getColoumnID());
                    startActivity(i);

                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return aidNeededs.size();
        }





        public void animateTo(List<AidNeeded> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<AidNeeded> newModels) {
            for (int i = aidNeededs.size() - 1; i >= 0; i--) {
                final AidNeeded model = aidNeededs.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<AidNeeded> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final AidNeeded model = newModels.get(i);
                if (!aidNeededs.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<AidNeeded> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final AidNeeded model = newModels.get(toPosition);
                final int fromPosition = aidNeededs.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public AidNeeded removeItem(int position) {
            final AidNeeded model = aidNeededs.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, AidNeeded model) {
            aidNeededs.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final AidNeeded model = aidNeededs.remove(fromPosition);
            aidNeededs.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

    }



    private void processJson(JSONObject jsonObject)
    {
        try{

            JSONArray articlesArray = jsonObject.getJSONArray("rows");
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONArray jsonArray = articlesArray.getJSONArray(i);
                AidNeeded item = new AidNeeded();
                item.setTimeStamp(jsonArray.getString(0));
                item.setName(jsonArray.getString(1));
                item.setWhatKind(jsonArray.getString(2));
                item.setArea(jsonArray.getString(3));
                item.setContactNumber(jsonArray.getString(4));
                item.setOriginalSource(jsonArray.getString(5));
                item.setOthers(jsonArray.getString(6));



                DButils.insertAidNeeded(AidNeededActivity.this, item);
            }
            aidNeededs = DButils.readAidNeededData(AidNeededActivity.this);

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
            progressDialog = ProgressDialog.show(AidNeededActivity.this, "", "Reading Data...", true);

            super.onPreExecute();
        }
    }

}