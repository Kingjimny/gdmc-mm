package com.JimnyLibs.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.JimnyLibs.android.R;

/**
 * Created by Jimny
 * on 14-7-20.
 */
public class JimnyActionBarActivity extends ActionBarActivity {
    private View mFragmentContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentContentView = findViewById(getDummyContentId());
    }

    protected int getBaseLayoutId() {
        return R.layout.activity_bg_null;
    }

    protected int getBaseContent(){
        return R.id.baseContent;
    }
    protected int getDummyContentId() {
        return R.id.dummyContent;
    }


}
