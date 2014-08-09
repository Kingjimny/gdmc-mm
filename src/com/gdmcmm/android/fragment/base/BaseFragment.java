package com.gdmcmm.android.fragment.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.JimnyLibs.android.utils.Util_Toast;
import com.gdmcmm.android.config.AppDebugConfig;
import com.JimnyLibs.android.listener.FragmentNavigateListener;

/**
 * Created by Jimny
 * on 14-7-25.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    protected FragmentNavigateListener mFragmentNavigateListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentNavigateListener) {
            mFragmentNavigateListener = (FragmentNavigateListener) activity;
        }
    }

    protected  void toast(String text) {
        Util_Toast.Toast(getActivity(), text);
    }

    protected boolean handleOnClick(View view) {
        return false;
    }

//    public void setSoftInputMode(int mode) {
//        if (getActivity() instanceof BaseActionBarActivity) {
//            ((BaseActionBarActivity) getActivity()).setSoftInputMode(mode);
//        }
//    }
//
//    public void setSlidingTouchMode(int touchMode) {
//        if (getActivity() instanceof BaseActionBarActivity) {
//            ((BaseActionBarActivity) getActivity()).setSlidingTouchMode(touchMode);
//        }
//    }
//
//    public void hideMenu() {
//        if (getActivity() instanceof BaseActionBarActivity) {
//            ((BaseActionBarActivity) getActivity()).hideMenu();
//        }
//    }

    protected void addFragmentToActivityFragmentManager(Fragment toAdd) {
        if (mFragmentNavigateListener != null) {
            mFragmentNavigateListener.addFragmentToActivityFragmentManager(toAdd);
        }
    }

    protected void pushFragmentToActivityFragmentManager(Fragment toPush) {
        if (mFragmentNavigateListener != null) {
            mFragmentNavigateListener.pushFragmentToActivityFragmentManager(toPush);
        }
    }

    protected boolean popFragmentFromActivityFragmentManager() {
        if (mFragmentNavigateListener != null) {
            return mFragmentNavigateListener.popFragmentFromActivityFragmentManager();
        }
        return false;
    }

    @Override
    public final void onClick(View view) {
        try {
            handleOnClick(view);
        } catch (Exception e) {
            if (AppDebugConfig.IS_DEBUG) {

            }
        }
    }
}
