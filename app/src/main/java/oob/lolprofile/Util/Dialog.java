package oob.lolprofile.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class Dialog {
    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  String positiveActionText,
                                  DialogInterface.OnClickListener positiveCallback,
                                  String negativeActionText,
                                  DialogInterface.OnClickListener negativeCallback) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveActionText, positiveCallback)
                .setNegativeButton(negativeActionText, negativeCallback)
                .show();
    }
}
