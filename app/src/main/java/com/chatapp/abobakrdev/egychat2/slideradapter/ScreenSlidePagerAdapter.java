package com.chatapp.abobakrdev.egychat2.slideradapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Random;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
public class ScreenSlidePagerAdapter  extends FragmentStatePagerAdapter {

        private  int NUM_PAGES ;

        public ScreenSlidePagerAdapter(FragmentManager fm, int num ) {
            super(fm);
            this.NUM_PAGES=num;
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("test", new Random().nextInt());
            fragment.setArguments(bundle);
            return fragment;
        }




    @Override
        public int getCount() {
            return NUM_PAGES;
        }

}
