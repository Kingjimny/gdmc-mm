package com.gdmcmm.android.activity;

import android.os.Bundle;

import com.gdmcmm.android.fragment.FragmentLogin;

public class MainActivity extends BaseActionBarActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pushNoBackStackFragmentToActivityFragmentManager(FragmentLogin.newInstance());
    }
}
