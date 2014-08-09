package com.gdmcmm.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdmcmm.android.activity.R;
import com.gdmcmm.android.fragment.base.BaseFragment;

/**
 * Created by Jimny
 * on 14-7-20.
 */
public class FragmentLogin extends BaseFragment {

    public static Fragment newInstance(){
        return new FragmentLogin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,null);
    }
}
