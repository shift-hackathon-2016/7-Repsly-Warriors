package com.repsly.careline.helpers;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by tosulc on 31.05.2016..
 */
public class AnimationHelper {

    public static void fadeOutView(final View v, float from, float to) {
        AlphaAnimation animation = new AlphaAnimation(from, to);
        animation.setDuration(500);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                v.requestLayout();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        v.startAnimation(animation);
    }

}
