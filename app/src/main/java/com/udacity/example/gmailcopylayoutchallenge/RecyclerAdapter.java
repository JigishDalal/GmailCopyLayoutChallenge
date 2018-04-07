package com.udacity.example.gmailcopylayoutchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterViewHolder> {
    Context mContext;
    String[] mFrom;
    String[] mTitle;
    String[] mMessage;
    String[] mDate;
    int[] mImage;

    TextView from_tv , title_tv , message_tv , date_tv ;
    ImageView icon ;

    public RecyclerAdapter(Context context, String[] from , String[] title , String[] message , String[] date , int[] image ) {
        mContext = context;
        mFrom = from;
        mTitle = title;
        mMessage = message;
        mDate = date;
        mImage = image;
    }

    public class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {
        public RecyclerAdapterViewHolder(View row) {
            super(row);
            from_tv = row.findViewById(R.id.from);
            title_tv = row.findViewById(R.id.title);
            message_tv = row.findViewById(R.id.message);
            date_tv = row.findViewById(R.id.date);
            icon = row.findViewById(R.id.profile_photo);
        }
    }

    @Override
    public RecyclerAdapter.RecyclerAdapterViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_row, parent , false);
        return new RecyclerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterViewHolder holder, int position) {
        from_tv.setText(mFrom[position]);
        title_tv.setText(mTitle[position]);
        message_tv.setText(mMessage[position]);
        date_tv.setText(mDate[position]);
        icon.setImageResource(mImage[position]);
    }

    @Override
    public int getItemCount() {
        return 9;
    }
}