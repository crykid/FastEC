package com.blank.art.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by : blank
 * Created on : 2018/8/7 at 22:12
 * Description:
 */

public class DateDialogUtil {
    public interface IDateListener {
        void onDateChange(String date);
    }

    private IDateListener mIDateListener = null;

    public void setListener(IDateListener listener) {
        this.mIDateListener = listener;
    }

    public void showDialog(Context context) {
        final LinearLayout ll = new LinearLayout(context);
        final DatePicker picker = new DatePicker(context);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        picker.setLayoutParams(lp);

        picker.init(1990, 1, 1, (view, year, monthOfYear, dayOfMonth) -> {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);

            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            final String date = format.format(calendar.getTime());
            if (mIDateListener != null) {
                mIDateListener.onDateChange(date);
            }
        });
        ll.addView(picker);

        new AlertDialog.Builder(context)
                .setTitle("选择riq")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
