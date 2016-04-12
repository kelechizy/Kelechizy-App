package com.kelechizy.kelechizyapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kelechizy.kelechizyapp.adapter.PagerAdapter;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    private static final int TAB_HOME = 1;
    private static final int TAB_ABOUT = 2;
    private static final int TAB_PORTFOLIO = 3;
    private static final int TAB_CONTACT = 4;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private TypedArray imgs;
    private TypedArray icons;
    private String[] titles;
    private String[] portfolioDetails;

    private FloatingActionButton fabPortfolio;
    private FloatingActionButton fabContact;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupToolbar();
        setupTablayout();
    }

    private void setupTablayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        icons = getResources().obtainTypedArray(R.array.tab_icons);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),4);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_work_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_local_phone_black_24dp);

        for(int i=1; i<tabLayout.getTabCount(); i++)
            tabLayout.getTabAt(i).getIcon().setAlpha(127);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setAlpha(255);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(127);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        try { setSupportActionBar(toolbar); }
        catch (Exception e){}
    }


    private Intent createShareAppIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_message));
        return shareIntent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if(mShareActionProvider != null){
            mShareActionProvider.setShareIntent(createShareAppIntent());
        }
        else{
            Log.d("Sadd", "Share Action Provider is null?");
        }

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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        setTitle(titles[tab.getPosition()]);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        private  RecyclerView mEducationRV;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int layout_id = R.layout.tab_home;
            int i = getArguments().getInt(ARG_SECTION_NUMBER);

            switch (i){
                case 1:
                    layout_id = R.layout.tab_home;
                    break;
                case 2:
                    layout_id = R.layout.tab_about;
                    break;
                case 3:
                    layout_id= R.layout.tab_portfolio;
                    break;
                case 4:
                    layout_id= R.layout.tab_contact;
                    break;
                default:break;
            }

            View rootView = inflater.inflate(layout_id, container, false);

            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

    }
}
