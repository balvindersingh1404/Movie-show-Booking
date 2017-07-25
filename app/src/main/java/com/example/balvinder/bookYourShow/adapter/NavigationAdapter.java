package com.example.balvinder.bookYourShow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.balvinder.bookYourShow.activity.AllTheatresActivity;
import com.example.balvinder.bookYourShow.activity.ComingSoonActivity;
import com.example.balvinder.bookYourShow.activity.ContactUsActivity;
import com.example.balvinder.bookYourShow.activity.GiftCardActivity;
import com.example.balvinder.bookYourShow.activity.OffersActivity;
import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.activity.TCPrivacyActivity;
import com.example.balvinder.bookYourShow.util.MyTextView;

import java.util.Arrays;
import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<View_Holder>  {

    List<String> menulist;
    Context context;
    DrawerLayout drawer;

    public NavigationAdapter(Context context,   DrawerLayout drawer) {
        this.context=context;
        this.menulist = Arrays.asList(context.getResources().getStringArray(R.array.menulist));
        this.drawer= drawer;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_layout, parent, false);
        return (new View_Holder(v));
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        holder.title.setText(menulist.get(position));
        holder.title.setTag(position);
        holder.title.setOnClickListener(new View.OnClickListener() {

            private Context applicationContext;

            public Context getApplicationContext() {
                return applicationContext;
            }

            @Override
            public void onClick(View v) {
                int item = (int) v.getTag();
                    drawer.closeDrawer(GravityCompat.START);
                if (item == 0) {
                    Intent in = new Intent(v.getContext(), AllTheatresActivity.class);
                    v.getContext().startActivity(in);
                } else if (item == 1) {
                    Intent in = new Intent(v.getContext(), ComingSoonActivity.class);
                    v.getContext().startActivity(in);
                } else if (item == 2) {

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Under Development", duration);
                    toast.show();
                } else if (item == 3) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Under Development", duration);
                    toast.show();
                } else if (item == 4) {
                    Intent in = new Intent(v.getContext(), GiftCardActivity.class);
                    v.getContext().startActivity(in);

                } else if (item == 5) {
                    Intent in = new Intent(v.getContext(), OffersActivity.class);
                    v.getContext().startActivity(in);
                } else if (item == 6) {

                    Intent in = new Intent(v.getContext(), ContactUsActivity.class);
                    v.getContext().startActivity(in);

                } else if (item == 7) {
                    Intent in = new Intent(v.getContext(), TCPrivacyActivity.class);
                    v.getContext().startActivity(in);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(menulist!=null)
            return menulist.size();
        return 0;
    }

}
class View_Holder extends RecyclerView.ViewHolder {

    MyTextView title;
    View_Holder(View itemView) {
        super(itemView);
        title = (MyTextView) itemView.findViewById(R.id.title);
    }
}