package com.kelechizy.kelechizyapp.tab;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.kelechizy.kelechizyapp.R;
import com.kelechizy.kelechizyapp.adapter.PortfolioAdapter;
import com.kelechizy.kelechizyapp.utility.MyGridView;

/**
 * Created by Kelechi on 3/3/2016.
 */
public class FragmentPortfolio extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_portfolio, container, false);

        TypedArray imgs = getResources().obtainTypedArray(R.array.portfolio_images);

        MyGridView mPortfolioGridView = (MyGridView) view.findViewById(R.id.portfolioGridView);
        ListAdapter mPortfolioAdapter = new PortfolioAdapter(getActivity(),imgs);

        mPortfolioGridView.setAdapter(mPortfolioAdapter);

        return view;
    }
}
