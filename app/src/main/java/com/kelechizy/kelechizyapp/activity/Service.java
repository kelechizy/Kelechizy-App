package com.kelechizy.kelechizyapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelechizy.kelechizyapp.R;

public class Service extends AppCompatActivity {

    TypedArray mServiceArray;
    TypedArray itemDef;
    Integer serviceImage;
    Integer serviceBackDrop;
    String serviceName;
    String serviceSummary;
    String serviceTechnology;
    String serviceDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mServiceArray =  getResources().obtainTypedArray(R.array.service);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", -1);

        int resId = mServiceArray.getResourceId(position, -1);
        itemDef = getResources().obtainTypedArray(resId);


        serviceImage = itemDef.getResourceId(0, -1);
        serviceName = itemDef.getString(1);
        serviceDescription = itemDef.getString(2);
        serviceTechnology = itemDef.getString(3);
        serviceSummary = itemDef.getString(4).replace("  "," ").trim();
        serviceBackDrop = itemDef.getResourceId(5, -1);

        ImageView mServiceImage = (ImageView) this.findViewById(R.id.serviceImageView);
        TextView mServiceName = (TextView) this.findViewById(R.id.serviceName);
        TextView mServiceSummary = (TextView) this.findViewById(R.id.serviceSummary);
        TextView mServiceDescription = (TextView) this.findViewById(R.id.serviceDescription);
        TextView mServiceTechnology = (TextView) this.findViewById(R.id.serviceTechnology);
        ImageView mServiceBackdrop = (ImageView) this.findViewById(R.id.serviceBackdrop);

        mServiceImage.setImageResource(serviceImage);
        mServiceName.setText(serviceName);
        mServiceSummary.setText(serviceSummary);
        mServiceDescription.setText(serviceDescription);
        mServiceTechnology.setText(serviceTechnology);
        mServiceBackdrop.setImageResource(serviceBackDrop);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
