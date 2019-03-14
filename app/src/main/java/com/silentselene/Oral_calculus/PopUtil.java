package com.silentselene.Oral_calculus;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

class PopUtil extends PopupWindow {

    View mPopWindow;
    TextView textView;
    private Context mContext;

    PopUtil(Context context) {
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mPopWindow = inflater.inflate(R.layout.toast, null);
        textView = mPopWindow.findViewById(R.id.txtToastMessage);
        setPopWindow();
    }

    private static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void setPopWindow() {
        // 把View添加到PopWindow中
        this.setContentView(mPopWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(dip2px(mContext, 60));
        //  设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        //   设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }
}
