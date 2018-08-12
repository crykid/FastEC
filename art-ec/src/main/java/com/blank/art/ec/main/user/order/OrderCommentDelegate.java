package com.blank.art.ec.main.user.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ui.widget.AutoPhotoLayout;
import com.blank.art.ui.widget.StarLayout;
import com.blank.art.util.callback.CallbackManager;
import com.blank.art.util.callback.CallbackType;
import com.blank.art.util.callback.IGlobalCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 22:58
 * Description:订单评价
 */

public class OrderCommentDelegate extends ArtDelegate {
    @BindView(R2.id.top_tv_comment_commit)
    AppCompatTextView atvCommit;
    @BindView(R2.id.tv_comment_title)
    TextView tvTitle;
    @BindView(R2.id.starl_ordercomment_star)
    StarLayout starlStar;
    @BindView(R2.id.et_order_comment)
    AppCompatEditText etComment;

    @BindView(R2.id.custom_apl_ordercomment)
    AutoPhotoLayout aplPhotoLayout;

    @Override
    public Object getLyout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        aplPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@NonNull Uri args) {
                        aplPhotoLayout.onCropTarget(args);
                    }
                });
    }


    @OnClick({R2.id.top_tv_comment_commit})
    public void onViewClicked(View view) {
        final int id = view.getId();
        if (id == R.id.top_tv_comment_commit) {
            Toast.makeText(getContext(), etComment.getText().toString() + " stars :" + starlStar.getStarCount(), Toast.LENGTH_SHORT).show();
        }
    }
}
