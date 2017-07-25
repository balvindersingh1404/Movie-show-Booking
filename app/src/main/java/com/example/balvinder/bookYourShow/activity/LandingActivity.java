package com.example.balvinder.bookYourShow.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.adapter.MfyAdapter;
import com.example.balvinder.bookYourShow.adapter.NavigationAdapter;
import com.example.balvinder.bookYourShow.model.Datum;
import com.example.balvinder.bookYourShow.model.MFYResponse;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MovieApplication;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {
    RoundedImageView profilePic;
    JSONObject pictureJSON;
    List<Datum> datum = new ArrayList<>();
    List<List<Datum>> list = null;
    String userName = null;
    String email;
    String url;
    Context context;
    ImageView searchloc;
    MyTextView location;
    String locationName = "";
    String id = "";
    LinearLayout datanotfound;
    RecyclerView mfylistView;
    private MyTextView signout;
    private RecyclerView recyclerView;
    private NavigationAdapter adapter;
    private MyTextView name = null;
    private Call<MFYResponse> call;
    private RecyclerView moviesRecyclerView;
    private MfyAdapter moviesAdapter;
    private List<Datum> datumList;
    private MyTextView yettobook;
    private MyTextView moviesforu;
    private ProgressDialog progress;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        moviesRecyclerView = (RecyclerView) findViewById(R.id.mfylistView);
        datanotfound = (LinearLayout) findViewById(R.id.datanotfoundmfy);
        mfylistView = (RecyclerView) findViewById(R.id.mfylistView);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        datumList = new ArrayList<>();
        moviesAdapter = new MfyAdapter(this, datum);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        moviesRecyclerView.setLayoutManager(mLayoutManager);
        moviesRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        moviesRecyclerView.setAdapter(moviesAdapter);


        recyclerView = (RecyclerView) navigationView.getHeaderView(0).findViewById(R.id.menulist);
        profilePic = (RoundedImageView) findViewById(R.id.imageView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NavigationAdapter(this, drawer);
        recyclerView.setAdapter(adapter);

        moviesforu = (MyTextView) findViewById(R.id.movies_for_u);
        yettobook = (MyTextView) findViewById(R.id.yettobook);
        signout = (MyTextView) findViewById(R.id.signout);
        name = (MyTextView) findViewById(R.id.hello);

        userName = MovieApplication.getPref().getString(AppPreferences.name);
        email = MovieApplication.getPref().getString(AppPreferences.email);
        name.setText(userName);

        location = (MyTextView) findViewById(R.id.location);

        id = MovieApplication.getPref().getString(AppPreferences.id);
        locationName = MovieApplication.getPref().getString(AppPreferences.location);
        location.setText(locationName);

        if ((MovieApplication.getPref().getString(AppPreferences.name).equals("HELLO"))) {
            signout.setText("SIGN IN");
        } else {
            signout.setText("SIGN OUT");
        }

        if ((MovieApplication.getPref().getString(AppPreferences.picture) != null) && (MovieApplication.getPref().getString(AppPreferences.picture) != "")) {
            url = (MovieApplication.getPref().getString(AppPreferences.picture));
            Picasso.with(getApplicationContext()).load(url).into(profilePic);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        searchloc = (ImageView) findViewById(R.id.searchloc);
        searchloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });

        ImageView locate = (ImageView) findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SelectLocationActivity.class);
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(in);

            }
        });
        getMoviesData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        progress.hide();

        id = MovieApplication.getPref().getString("id");
        locationName = MovieApplication.getPref().getString("location");
        location.setText(locationName);
        getMoviesData();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    public void getMoviesData() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        call = ApiClient.getInstance().getMovies(id);
        call.enqueue(new Callback<MFYResponse>() {

            @Override
            public void onResponse(Call<MFYResponse> call, Response<MFYResponse> response) {
                progress.hide();
                if (response.body().getCode() == 10001) {
                    list = response.body().getData();

                    datum.clear();
                    for (List<Datum> datumList : list) {
                        for (Datum datumObj : datumList) {
                            datum.add(datumObj);
                        }
                    }

                    for (Datum datum1 : datum) {
                        Log.i("NAME", datum1.getN());
                    }
                    mfylistView.setVisibility(View.VISIBLE);
                    datanotfound.setVisibility(View.INVISIBLE);
                } else {
                    mfylistView.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                }
                moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MFYResponse> call, Throwable t) {

            }
        });
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}