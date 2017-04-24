package com.desafio.christiane.githubreader.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.desafio.christiane.githubreader.R;

public class Util {

    public static final String SELECTED_REPOSITORY = "SELECTED_REPOSITORY";
    public static final String SELECTED_CREATOR = "SELECTED_CREATOR";

    private static ProgressDialog progressDialog;

    private Util() {
        //unused
    }

    public static void openDialog(Context context, @StringRes int titulo, @StringRes int
            mensagem) {
        progressDialog = ProgressDialog.show(context, context.getString(titulo), context
                .getString(mensagem), false, true);
    }

    public static void closeDialog() {
        progressDialog.dismiss();
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_home, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }
}