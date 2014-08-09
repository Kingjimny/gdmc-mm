package com.gdmcmm.android.activity;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.JimnyLibs.android.activity.JimnyActionBarActivity;
import com.JimnyLibs.android.listener.FragmentNavigateListener;

/**
 * Created by Jimny
 * on 14-7-19.
 */
public class BaseActionBarActivity extends JimnyActionBarActivity implements
        FragmentNavigateListener{

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(getBaseLayoutId());
        initActionbar();
    }

    private void initActionbar(){
        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        mActionBar.setTitle("123213123123");
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);
//        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.id.icon);
        mActionBar.setLogo(android.R.drawable.ic_menu_add);
    }

    public void addFragmentToActivityFragmentManager(Fragment fragment) {
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.add(getDummyContentId(), fragment).addToBackStack(null).commitAllowingStateLoss();
    }

    public void pushFragmentToActivityFragmentManager(Fragment fragment) {
        if (fragment == null) return;
//        mFragmentContentView.setClickable(true);
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(getDummyContentId(), fragment).addToBackStack(null).commitAllowingStateLoss();
    }

    public void pushNoBackStackFragmentToActivityFragmentManager(Fragment fragment) {
        if (fragment == null) return;
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(getDummyContentId(), fragment).commitAllowingStateLoss();
    }

    public boolean popFragmentFromActivityFragmentManager() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

    public void popFragmentToBase() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public Fragment getTopFragment() {
        return null;
    }

}
