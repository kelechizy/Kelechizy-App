package com.kelechizy.kelechizyapp.tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kelechizy.kelechizyapp.R;

/**
 * Created by Kelechi on 3/3/2016.
 */
public class FragmentContact extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_contact, container, false);

        LinearLayout address = (LinearLayout) view.findViewById(R.id.address);
        LinearLayout email = (LinearLayout) view.findViewById(R.id.email);
        LinearLayout phone = (LinearLayout) view.findViewById(R.id.phone);
        LinearLayout website = (LinearLayout) view.findViewById(R.id.website);
        ImageView facebook = (ImageView) view.findViewById(R.id.facebook);
        ImageView linkedin = (ImageView) view.findViewById(R.id.linkedin);
        //ImageView skype = (ImageView) view.findViewById(R.id.skype);
        ImageView googleplus = (ImageView) view.findViewById(R.id.googleplus);
        ImageView twitter = (ImageView) view.findViewById(R.id.twitter);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        address.setOnClickListener(myContactOnClickListener);
        email.setOnClickListener(myContactOnClickListener);
        phone.setOnClickListener(myContactOnClickListener);
        website.setOnClickListener(myContactOnClickListener);
        facebook.setOnClickListener(myContactOnClickListener);
        linkedin.setOnClickListener(myContactOnClickListener);
        googleplus.setOnClickListener(myContactOnClickListener);
        twitter.setOnClickListener(myContactOnClickListener);
        fab.setOnClickListener(myContactOnClickListener);

        return view;
    }


    private View.OnClickListener myContactOnClickListener =  new View.OnClickListener() {
        public void intentStarter(String url){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(url));
            startActivity(intent);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            String name = getContext().getResources().getString(R.string.contact_name);
            String address = getContext().getResources().getString(R.string.contact_address_url);
            String email = getContext().getResources().getString(R.string.contact_email);
            String phone = getContext().getResources().getString(R.string.contact_phone).trim();
            String website = getContext().getResources().getString(R.string.contact_website_url);
            String facebook = getContext().getResources().getString(R.string.contact_facebook_url);
            String linkedin = getContext().getResources().getString(R.string.contact_linkedin_url);
            String skype = getContext().getResources().getString(R.string.contact_skype_username);
            String googleplus = getContext().getResources().getString(R.string.contact_google_plus_url);
            String twitter = getContext().getResources().getString(R.string.contact_twitter_url);


            switch(id) {
                case R.id.address:
                    intentStarter(address);
                    break;

                case R.id.email:
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", email, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Kelechi, ");
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    break;

                case R.id.phone:
                    String uri = "tel:" + phone;
                    Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                    phoneIntent.setData(Uri.parse(uri));
                    startActivity(phoneIntent);
                    break;

                case R.id.website:
                    intentStarter(website);
                    break;

                case R.id.facebook:
                    intentStarter(facebook);
                    break;

                case R.id.linkedin:
                    intentStarter(linkedin);
                    break;

//                        case R.id.skype:
//                            Intent sky = new Intent("android.intent.action.VIEW");
//                            sky.setData(Uri.parse("skype:" + skype));
//                            startActivity(sky);
//                            break;

                case R.id.googleplus:
                    intentStarter(googleplus);
                    break;

                case R.id.twitter:
                    intentStarter(twitter);
                    break;

                case R.id.fab:
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                    startActivity(intent);
                    break;

                default:
                    break;

            }
        }
    };
}
