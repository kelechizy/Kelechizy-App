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

import com.kelechizy.kelechizyapp.activity.Experience;
import com.kelechizy.kelechizyapp.R;

/**
 * Created by Kelechi on 2/2/2016.
 */
public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder>{
    private Context mContext;
    private TypedArray itemDef;
    private LayoutInflater mLayoutInflater;
    private TypedArray mExpArray ;
    private int mPosition = 0;


    public ExperienceAdapter(Context context, TypedArray expArray){
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mExpArray = expArray;
    }

    @Override
    public ExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View v;

        viewType = getItemViewType(mPosition++);

        switch (viewType){
            case 0:
                v  = inflater.inflate(R.layout.transparent_holder, parent, false);
                return new ViewHolderTransparent(v);

            default:
                v  = inflater.inflate(R.layout.list_experience, parent, false);
                ViewHolder vh = new ViewHolder(v);
                return vh;
        }
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position){
            case 0:
                break;
            default:
                int resId = mExpArray.getResourceId(position, -1);

                itemDef = mContext.getResources().obtainTypedArray(resId);

                try{
                    holder.imageView.setImageResource(itemDef.getResourceId(0, -1));
                    holder.companyTextView.setText(itemDef.getString(1));
                    holder.roleTextView.setText(itemDef.getString(2));
                    holder.typeTextView.setText(itemDef.getString(3));
                    holder.dateTextView.setText(itemDef.getString(4));
                }
                catch (Exception ex){}
        }
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


    @Override
    public int getItemCount() {
        return mExpArray.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView companyTextView;
        public TextView roleTextView;
        public TextView typeTextView;
        public TextView dateTextView;

        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.companyImageView);
            companyTextView = (TextView) view.findViewById(R.id.companyTextView);
            roleTextView = (TextView) view.findViewById(R.id.roleTextView);
            typeTextView = (TextView) view.findViewById(R.id.typeTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }

    }

    public static class ViewHolderTransparent extends ExperienceAdapter.ViewHolder {
        public ViewHolderTransparent(View view){
            super(view);
        }
    }

    private View.OnClickListener myOnClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, Experience.class);
            mContext.startActivity(intent);
        }
    };
}
