package com.example.balvinder.bookYourShow.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.ImageView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.adapter.LocationAdapter;
import com.example.balvinder.bookYourShow.model.ApiResponse;
import com.example.balvinder.bookYourShow.model.Location_data;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppConstant;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.balvinder.bookYourShow.R.layout.activity_select_location;


public class SelectLocationActivity extends AppCompatActivity {

    private ArrayList<Location_data> datalist=new ArrayList<>();
    private ListView lv;



    Context context;
    MyTextView changelocation;
    EditText searchlocation;
    ProgressDialog progress;
    LocationAdapter adapter;
    String lat="",lon="";
    Call<ApiResponse> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_select_location);
        context=this;
        adapter=new LocationAdapter(this,datalist) ;

        changelocation=(MyTextView) findViewById(R.id.changelocation);
        Util.applyLetterSpacing(changelocation,"CHANGE LOCATION", (float) 1);
        lv = (ListView) findViewById(R.id.listcity);
        lv.setAdapter(adapter);

        searchlocation=(EditText) findViewById(R.id.searchlocation);
        lv.setTextFilterEnabled(true);


        ImageView back = (ImageView) findViewById(R.id.backloc);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        getCitydata();
    }

    private void getCitydata(){
        progress= new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

           lat=28.6252151+"";
            lon=77.3734901+"";


        call = ApiClient.getInstance().getLocation(lat,lon);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.body().getCode()== AppConstant.SUCCESSCODE) {
                    progress.hide();

                    List<Location_data> nearByCity=response.body().getData().getNb();
                    if(nearByCity!=null&& nearByCity.size()>0){
                        Location_data data=new Location_data();
                        data.setName("NEAR BY");
                        data.setId("");
                        datalist.add(data);
                        datalist.addAll(nearByCity);
                    }
                    List<Location_data> otherCities = response.body().getData().getOt();
                    if(otherCities!=null && otherCities.size()>0){
                        Location_data nearBydata= new Location_data();
                        nearBydata.setName("OTHER CITIES");
                        nearBydata.setId("");
                        datalist.add(nearBydata);
                        datalist.addAll(otherCities);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

}