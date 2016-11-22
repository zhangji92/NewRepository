package com.jun.financial.credit.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jun.financial.credit.R;

/**
 * Created by 张继 on 2016/11/17.
 * 对话框
 */

public class DialogUtils {

    public static Dialog showDefaultProDialog(Context context, final OnButtonCancelListener buttonCancelListener){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        final AlertDialog dialog=builder.create();

        dialog.setCanceledOnTouchOutside(false);
        if (buttonCancelListener!=null){
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if (buttonCancelListener!=null){
                        dialog.dismiss();
                        buttonCancelListener.onCancel();
                    }
                }
            });
        }else {
            dialog.setCancelable(false);
        }
        //LinearLayout layout = (LinearLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loan_pro_dialog_layout, null);
//        int screenWidth = ScreenUtil.getScreenWidth(context);
//        dialog.getWindow().setLayout(screenWidth * 1 / 3, WindowManager.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setContentView(layout);
        return dialog;
    }

    private interface OnButtonCancelListener {
        void onCancel();//取消

    }
}
