package com.gdmcmm.android;

import android.app.Application;
import android.support.v4.app.Fragment;

/**
 * Created by Jimny
 * on 14-7-25.
 */
public class AppInstance extends Application {

    public interface FragmentNavigateListener {

        public void addFragmentToActivityFragmentManager(Fragment toAdd);

        public void pushFragmentToActivityFragmentManager(Fragment toPush);

        public void pushNoBackStackFragmentToActivityFragmentManager(Fragment toPush);

        public boolean popFragmentFromActivityFragmentManager();

        public void popFragmentToBase();

        public Fragment getTopFragment();
    }
}
