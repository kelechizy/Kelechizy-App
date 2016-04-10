package com.kelechizy.kelechizyapp.tab;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelechizy.kelechizyapp.R;
import com.kelechizy.kelechizyapp.activity.Service;
import com.kelechizy.kelechizyapp.activity.Experience;
import com.kelechizy.kelechizyapp.adapter.EducationAdapter;
import com.kelechizy.kelechizyapp.adapter.ExperienceAdapter;
import com.kelechizy.kelechizyapp.adapter.ServiceAdapter;
import com.kelechizy.kelechizyapp.utility.MyRecyclerItemClickListener;

/**
 * Created by Kelechi on 3/3/2016.
 */
public class FragmentAbout extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_about, container, false);

        TypedArray educationArray = getResources().obtainTypedArray(R.array.education);
        TypedArray serviceArray = getResources().obtainTypedArray(R.array.service);
        TypedArray expArray = getResources().obtainTypedArray(R.array.experience);


        RecyclerView mEducationRV;
        mEducationRV = (RecyclerView)rootView.findViewById(R.id.educationRecyclerView);
        mEducationRV.setHasFixedSize(false);
        RecyclerView.LayoutManager mEducationLayoutManager;
        RecyclerView.Adapter mEducationAdapter;

        mEducationLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mEducationRV.setLayoutManager(mEducationLayoutManager);

        mEducationAdapter = new EducationAdapter(getActivity(),educationArray);
        mEducationRV.setAdapter(mEducationAdapter);





        //Service Recycler View
        RecyclerView mServicesRV = (RecyclerView)rootView.findViewById(R.id.serviceRecyclerView);
        mServicesRV.setHasFixedSize(false);
        RecyclerView.LayoutManager mServicesLayoutManager;
        RecyclerView.Adapter mServicesAdapter;

        //mServicesLayoutManager = new GridLayoutManager(getContext(),2);
        mServicesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mServicesRV.setLayoutManager(mServicesLayoutManager);
        mServicesAdapter = new ServiceAdapter(getActivity(),serviceArray);
        mServicesRV.setAdapter(mServicesAdapter);
        mServicesRV.setHasFixedSize(false);
        mServicesRV.addOnItemTouchListener(
                new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Context myContext = getContext();
                        Intent intent = new Intent(myContext, Service.class)
                                .putExtra("POSITION", position);
                        myContext.startActivity(intent);
                    }
                })
        );

        RecyclerView mExperienceRV = (RecyclerView) rootView.findViewById(R.id.experienceRecyclerView);
        mExperienceRV.setHasFixedSize(true);
        RecyclerView.LayoutManager mExperienceLayoutManager;
        RecyclerView.Adapter mExperienceAdapter;

        mExperienceLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mExperienceRV.setLayoutManager(mExperienceLayoutManager);

        mExperienceAdapter = new ExperienceAdapter(getActivity(),expArray);
        mExperienceRV.setAdapter(mExperienceAdapter);
        mExperienceRV.addOnItemTouchListener(
                new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Context myContext = getContext();
                        Intent intent = new Intent(myContext, Experience.class)
                                .putExtra("POSITION", position);
                        myContext.startActivity(intent);
                    }
                })
        );


        return rootView;
    }

}
