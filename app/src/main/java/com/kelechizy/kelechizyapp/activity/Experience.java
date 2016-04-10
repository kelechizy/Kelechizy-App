package com.kelechizy.kelechizyapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelechizy.kelechizyapp.R;

public class Experience extends AppCompatActivity {

    TypedArray mExpArray;
    TypedArray itemDef;

    String experienceName;
    String experienceRole;
    String experienceType;
    String experienceDate;
    String experienceOverview;
    int experienceImage;
    int experienceImageLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        mExpArray =  getResources().obtainTypedArray(R.array.experience);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", -1);

        int resId = mExpArray.getResourceId(position, -1);
        itemDef = getResources().obtainTypedArray(resId);

        experienceName = itemDef.getString(1);
        experienceRole = itemDef.getString(2);
        experienceType = itemDef.getString(3);
        experienceDate = itemDef.getString(4);
        experienceOverview = itemDef.getString(5);
        experienceImage =   itemDef.getResourceId(0, -1);
        experienceImageLarge = itemDef.getResourceId(6, -1);

        ImageView mExperienceImage = (ImageView) this.findViewById(R.id.expereienceImage);
        ImageView mExperienceImageLarge = (ImageView) this.findViewById(R.id.expereienceImageLarge);
        TextView mExperienceName = (TextView) this.findViewById(R.id.experienceName);
        TextView mExperienceRole = (TextView) this.findViewById(R.id.experienceRole);
        TextView mExperienceType = (TextView) this.findViewById(R.id.experienceType);
        TextView mExperienceDate = (TextView) this.findViewById(R.id.experienceDate);
        TextView mExperienceOverview = (TextView) this.findViewById(R.id.experienceOverview);

        try{
            ((CollapsingToolbarLayout) this.findViewById(R.id.collapsing_toolbar_layout)).setTitle(experienceName);}
        catch (Exception e){}

        mExperienceImage.setImageResource(experienceImage);
        mExperienceImageLarge.setImageResource(experienceImageLarge);
        mExperienceName.setText(experienceName);
        mExperienceRole.setText(experienceRole);
        mExperienceType.setText(experienceType);
        mExperienceDate.setText(experienceDate);
        mExperienceOverview.setText(experienceOverview);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.app_bar);
        try {
            (this).setSupportActionBar(toolbar);
            (this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            (this).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        catch (Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_experience, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_rate) {
            Context context = getApplicationContext();

            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

            try { startActivity(goToMarket); }
            catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
