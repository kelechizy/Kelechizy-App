package com.kelechizy.kelechizyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.kelechizy.kelechizyapp.activity.Portfolio;
import com.kelechizy.kelechizyapp.R;

import static android.view.View.OnClickListener;

/**
 * Created by Kelechi on 1/19/2016.
 */
public class PortfolioAdapter implements ListAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private TypedArray mResources;
    private TypedArray imgs;
    private TypedArray imgsLarge;
    private String[] portfolioNames;
    private String[] portfolioDetails;
    private String[] portfolioCategories;
    private String[] portfolioYear;

    public PortfolioAdapter(Context context, TypedArray resources) {
        mContext = context;
        mResources = resources;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        imgs = mContext.getResources().obtainTypedArray(R.array.portfolio_images);
        imgsLarge = mContext.getResources().obtainTypedArray(R.array.portfolio_images_large);
        portfolioNames = mContext.getResources().getStringArray(R.array.portfolio_names);
        portfolioDetails = mContext.getResources().getStringArray(R.array.portfolio_details);
        portfolioCategories = mContext.getResources().getStringArray(R.array.portfolio_category);
        portfolioYear = mContext.getResources().getStringArray(R.array.portfolio_years);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return mResources.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_portfolio,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.portfolio_image);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ImageView imageView = viewHolder.imageView;
        imageView.setImageResource(mResources.getResourceId(position, -1));
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Portfolio.class)
                        .putExtra("IMAGE",imgs.getResourceId(position,-1))
                        .putExtra("IMAGE_LARGE", imgsLarge.getResourceId(position,-1))
                        .putExtra("NAME", portfolioNames[position])
                        .putExtra("CATEGORY", portfolioCategories[position])
                        .putExtra("YEAR", portfolioYear[position])
                        .putExtra("DETAIL",portfolioDetails[position]);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return mResources.length();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private static class ViewHolder {
        ImageView imageView;

        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.portfolio_image);
        }

        public ViewHolder() {}
    }
}
