package com.demo.fstack.fstackapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.demo.fstack.fstackapp.R;

public class CustomProgressDialogUtil {
    private static AlertDialog sProgressAlertDialog;

    public static void show(Context context) {
        if (sProgressAlertDialog != null && sProgressAlertDialog.isShowing()) {
            sProgressAlertDialog.dismiss();
            sProgressAlertDialog = null;
        }

        AlertDialog.Builder progressBuilder = new AlertDialog.Builder(context, R.style.ProgressDialogTheme);

        View view = LayoutInflater.from(context).inflate(R.layout.custom_progress_bar, null);
        progressBuilder.setView(view);
        progressBuilder.setCancelable(false);

        sProgressAlertDialog = progressBuilder.create();
        sProgressAlertDialog.show();
    }

    public static void dismiss() {
        if (sProgressAlertDialog != null && sProgressAlertDialog.isShowing()) {
            sProgressAlertDialog.dismiss();
            sProgressAlertDialog = null;
        }
    }
}
