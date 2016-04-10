package com.kelechizy.kelechizyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelechizy.kelechizyapp.activity.Education;
import com.kelechizy.kelechizyapp.R;

/**
 * Created by Kelechi on 2/3/2016.
 */
public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder>{
    private Context mContext;
    private int mPosition = 0;
    private LayoutInflater mLayoutInflater;
    private TypedArray mEducationArray;

    public EducationAdapter(Context context, TypedArray educationArray){
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEducationArray = educationArray;

    }

    @Override
    public EducationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View v;

        viewType = getItemViewType(mPosition++);

        switch (viewType){
            case 0:
                v  = inflater.inflate(R.layout.transparent_holder, parent, false);
                return new ViewHolderTransparent(v);

            default:
                v  = inflater.inflate(R.layout.list_education, parent, false);
                v.setOnClickListener(myOnClickListener);
                ViewHolder vh = new ViewHolder(v);
                return vh;

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
                break;
            default:
                TypedArray itemDef;

                int resId = mEducationArray.getResourceId(position, -1);

                itemDef = mContext.getResources().obtainTypedArray(resId);

                holder.imageView.setImageResource(itemDef.getResourceId(0, -1));
                holder.educationTextView.setText(itemDef.getString(1));
                holder.certificateTextView.setText(itemDef.getString(2));
                holder.dateTextView.setText(itemDef.getString(3));
        }


    }

    @Override
    public int getItemCount() {
        return mEducationArray.length();
    }


    @Override
    public int getItemViewType(int position) {
        switch(position){
            case 0:
                return 0;
            default:
                return 1;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView educationTextView;
        public TextView certificateTextView;
        public TextView dateTextView;

        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.educationImageView);
            educationTextView = (TextView) view.findViewById(R.id.educationTextView);
            certificateTextView = (TextView) view.findViewById(R.id.certificateTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }

    public static class ViewHolderTransparent extends EducationAdapter.ViewHolder {
        public ViewHolderTransparent(View view){
            super(view);
        }
    }

    private View.OnClickListener myOnClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, Education.class);
        }
    };
}
