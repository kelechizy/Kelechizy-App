package com.kelechizy.kelechizyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelechizy.kelechizyapp.R;

/**
 * Created by Kelechi on 2/2/2016.
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private TypedArray mServiceArray;

    public ServiceAdapter(Context context, TypedArray serviceArray){
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mServiceArray = serviceArray;
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View v  = inflater.inflate(R.layout.list_service, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TypedArray itemDef;

        int resId = mServiceArray.getResourceId(position, -1);

        itemDef = mContext.getResources().obtainTypedArray(resId);

        holder.imageView.setImageResource(itemDef.getResourceId(0, -1));
        holder.serviceTextView.setText(itemDef.getString(1));
        holder.serviceExtraTextView.setText(itemDef.getString(2));
    }

    @Override
    public int getItemCount() {
        return mServiceArray.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView serviceTextView;
        public TextView serviceExtraTextView;

        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.serviceImageView);
            serviceTextView = (TextView) view.findViewById(R.id.serviceTextView);
            serviceExtraTextView = (TextView) view.findViewById(R.id.serviceExtraTextView);
        }

    }
}
