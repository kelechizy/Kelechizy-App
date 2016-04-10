package com.kelechizy.kelechizyapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelechizy.kelechizyapp.R;

public class Portfolio extends AppCompatActivity {

    private String portfolioName;
    private String portfolioDetail;
    private Integer portfolioImage;
    private Integer portfolioImageLarge;
    private String portfolioCategory;
    private String portfolioYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        Intent intent = getIntent();
        portfolioName = intent.getStringExtra("NAME");
        portfolioDetail = intent.getStringExtra("DETAIL");
        portfolioCategory = intent.getStringExtra("CATEGORY");
        portfolioYear = intent.getStringExtra("YEAR");
        portfolioImage = intent.getIntExtra("IMAGE", -1);
        portfolioImageLarge = intent.getIntExtra("IMAGE_LARGE", -1);

        ImageView mPortfolioImage = (ImageView) this.findViewById(R.id.portfolioImageView);
        ImageView mPortfolioImageLarge = (ImageView) this.findViewById(R.id.portfolioImageViewLarge);
        TextView mPortfolioName = (TextView) this.findViewById(R.id.portfolioName);
        TextView mPortfolioCategory = (TextView) this.findViewById(R.id.portfolioCategory);
        TextView mPortfolioYear = (TextView) this.findViewById(R.id.portfolioYear);
        TextView mPortfolioDetail = (TextView) this.findViewById(R.id.portfolioDetail);

        mPortfolioImage.setImageResource(portfolioImage);
        mPortfolioImageLarge.setImageResource(portfolioImageLarge);
        mPortfolioName.setText(portfolioName);
        mPortfolioDetail.setText(portfolioDetail);
        mPortfolioCategory.setText(portfolioCategory);
        mPortfolioYear.setText(portfolioYear);

        setTitle(portfolioName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portfolio, menu);
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
