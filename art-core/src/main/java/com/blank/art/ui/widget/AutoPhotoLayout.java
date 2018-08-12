package com.blank.art.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.blank.art.R;
import com.blank.art.delegates.ArtDelegate;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/11 at 12:20
 * Description:
 */

public class AutoPhotoLayout extends LinearLayoutCompat {

    private int mCurrentNum = 0;

    //总数量
    private int mMaxNum = 0;

    //每一行数量
    private int mMaxLineNum = 3;

    private IconTextView mIconAdd = null;

    private LayoutParams mParams = null;

    //要删除的图片id
    private int mDeletId = 0;
    private AppCompatImageView mTargetImageView = null;
    private int mImageViewMargin = 0;

    private ArtDelegate mDelegate = null;
    private List<View> mLineViews = null;

    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";

    private float mIconSize = 0;

    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();

    private static final List<Integer> LINE_HEIGHTS = new ArrayList<>();

    //防止多次的测量和布局过程
    private boolean mIsOnceInitOnMeasure = false;
    private boolean mHasInitOnLayout = false;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);

        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 1);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageViewMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getDimension(R.styleable.camera_flow_layout_icon_size, 20);

        typedArray.recycle();


    }

    public final void setDelegate(ArtDelegate delegate) {
        this.mDelegate = delegate;
    }


    public final void onCropTarget(Uri uri) {
        createNewImageView();
        Glide.with(mDelegate)
                .load(uri)
                .apply(OPTIONS)
                .into(mTargetImageView);
    }

    private void createNewImageView() {
        mTargetImageView = new AppCompatImageView(getContext());
        mTargetImageView.setId(mCurrentNum);
        mTargetImageView.setLayoutParams(mParams);
        mTargetImageView.setOnClickListener(v -> {
            //获取要删除的图片ID
            mDeletId = v.getId();
            mTargetDialog.show();
            final Window window = mTargetDialog.getWindow();
            if (window != null) {
                window.setContentView(R.layout.dialog_apl_click_panel);
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final WindowManager.LayoutParams params = window.getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                params.dimAmount = 0.5f;
                window.setAttributes(params);
                window.findViewById(R.id.dialog_image_clicked_btn_delete)
                        .setOnClickListener(v1 -> {
                            //得到要删除的图片
                            final AppCompatImageView deleteImageView = findViewById(mDeletId);
                            //设置图片逐渐消失的动画
                            final AlphaAnimation animation = new AlphaAnimation(1, 0);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            //动画执行完是否停留状态
                            animation.setFillAfter(true);
                            //开始等待时间
                            animation.setStartOffset(0);
                            deleteImageView.setAnimation(animation);
                            animation.start();
                            AutoPhotoLayout.this.removeView(deleteImageView);
                            mCurrentNum -= 1;
                            //当数量达到上限时隐藏添加按钮，不足时显示
                            if (mCurrentNum < mMaxNum) {
                                mIconAdd.setVisibility(VISIBLE);
                            }
                            mTargetDialog.cancel();
                        });
                window.findViewById(R.id.dialog_image_clicked_btn_undetermined)
                        .setOnClickListener(v2 -> {
                            mTargetDialog.cancel();
                        });
                window.findViewById(R.id.dialog_image_clicked_btn_cancel)
                        .setOnClickListener(v3 -> {
                            mTargetDialog.cancel();
                        });
            }
        });
        //添加子View的时候传入位置
        this.addView(mTargetImageView, mCurrentNum);
        mCurrentNum++;
        //当添加数目大于mMaxNum时候，自动隐藏添加按钮
        if (mCurrentNum >= mMaxNum) {
            mIconAdd.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        final int height = MeasureSpec.getSize(heightMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = 0;
        int h = 0;
        //记录每一行宽度与高度
        int lineWidth = 0;
        int lineHeight = 0;
        //得到内部元素个数
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            //测量子view
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //得到layoutparams
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //获取子view占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //子view占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //换行
            if (lineWidth + childWidth > width - getPaddingLeft() - getPaddingRight()) {

                //对比得到最大宽度
                w = Math.max(w, lineWidth);
                //重置lineWidth
                lineWidth = childWidth;
                h += lineHeight;
                lineHeight = childHeight;
            } else {
                //未换行
                //叠加行宽
                lineWidth += childWidth;
                //得到当前最大的高度
                lineHeight = Math.max(lineHeight, childHeight);
            }
            //最后一个子控件
            if (i == count - 1) {
                w = Math.max(lineWidth, w);
                //判断是否超过最大拍照限制
                h += lineHeight;
            }

        }
        setMeasuredDimension(
                widthMode == MeasureSpec.EXACTLY ? width : w + getPaddingRight() + getPaddingLeft(),
                heightMode == MeasureSpec.EXACTLY ? height : h + getPaddingTop() + getPaddingBottom()
        );
        final int imageSideLen = width / mMaxLineNum;
        //只初始化一次
        if (!mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imageSideLen, imageSideLen);
            mIsOnceInitOnMeasure = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        //当前ViewGroup的宽度
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;

        if (!mHasInitOnLayout) {
            mLineViews = new ArrayList<>();
            mHasInitOnLayout = true;
        }
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            //如果需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                //记录lineHeight
                LINE_HEIGHTS.add(lineHeight);
                //记录当前一行的Views
                ALL_VIEWS.add(mLineViews);
                //重置宽和高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置View集合
                mLineViews.clear();
            }
            lineWidth += childWidth + lp.rightMargin + lp.leftMargin;
            lineHeight = Math.max(lineHeight, lineHeight + lp.topMargin + lp.bottomMargin);
            mLineViews.add(child);

        }
        //处理最后一行
        LINE_HEIGHTS.add(lineHeight);
        ALL_VIEWS.add(mLineViews);
        //设置子view位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        //行数
        final int lineNum = ALL_VIEWS.size();
        for (int i = 0; i < lineNum; i++) {
            //当前行所有的view
            mLineViews = ALL_VIEWS.get(i);
            lineHeight = LINE_HEIGHTS.get(i);
            final int size = mLineViews.size();
            //循环每一行
            for (int j = 0; j < size; j++) {
                final View child = mLineViews.get(j);
                //判断child的状态
                if (child.getVisibility() == GONE) {
                    continue;
                }
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                //设置子View的边距
                final int lc = left + lp.leftMargin;
                final int tc = top + lp.topMargin;
                final int rc = lc + child.getMeasuredWidth() - mImageViewMargin;
                final int bc = tc + child.getMeasuredHeight();
                //为子View进行布局
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
        mIconAdd.setLayoutParams(mParams);
        mHasInitOnLayout = false;
    }

    private void initAddIcon() {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        mIconAdd.setOnClickListener(v -> {
            mDelegate.startCameraWithCheck();
        });
        this.addView(mIconAdd);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }
}
