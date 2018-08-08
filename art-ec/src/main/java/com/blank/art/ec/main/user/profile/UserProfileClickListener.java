package com.blank.art.ec.main.user.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.main.user.list.ListBean;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.util.callback.CallbackManager;
import com.blank.art.util.callback.CallbackType;
import com.blank.art.util.callback.IGlobalCallback;
import com.blank.art.util.dialog.DateDialogUtil;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by : blank
 * Created on : 2018/8/7 at 21:50
 * Description: 个人信息--列表点击事件处理类
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final ArtDelegate DELEGATE;
    private final String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(ArtDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开启相机或选择图片

                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
                                Log.d(TAG, "executeCallback: " + args.toString());
                                final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);
                                RestClient.builder()
                                        .url("文件服务器地址")
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(Object response) {
                                                //1.通知app服务器更新信息
                                                //2.更新本地用户数据

                                            }
                                        })
                                        .build()
                                        .upload();

                            }

                        });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                //
                final ArtDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog((dialog, which) -> {
                    final TextView textView = view.findViewById(R.id.atv_item_user_arrow_value);
                    textView.setText(mGenders[which]);
                    dialog.cancel();
                });

                break;
            case 4:
                DateDialogUtil dialogUtil = new DateDialogUtil();
                dialogUtil.setListener(date ->
                        ((AppCompatTextView) view.findViewById(R.id.atv_item_user_arrow_text)).setText(date)
                );
                dialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }
}
