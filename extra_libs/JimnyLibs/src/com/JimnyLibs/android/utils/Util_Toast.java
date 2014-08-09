package com.JimnyLibs.android.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jimny
 * on 14-7-20.
 */
public class Util_Toast {

    public static void Toast(Context context,String str){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
