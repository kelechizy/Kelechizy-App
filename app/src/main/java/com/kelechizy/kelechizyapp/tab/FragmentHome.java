package com.kelechizy.kelechizyapp.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.kelechizy.kelechizyapp.R;

/**
 * Created by Kelechi on 3/3/2016.
 */
public class FragmentHome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home, container, false);

        Animation _translateAnimation;
        ImageView _image;

        _image = (ImageView)view.findViewById(R.id.imageView7);

        _translateAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, -2000f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f);
        _translateAnimation.setDuration(32000);
        _translateAnimation.setRepeatCount(-1);
        _translateAnimation.setRepeatMode(Animation.REVERSE);
        _translateAnimation.setInterpolator(new LinearInterpolator());
        _image.setAnimation(_translateAnimation);

        return view;
    }

}
