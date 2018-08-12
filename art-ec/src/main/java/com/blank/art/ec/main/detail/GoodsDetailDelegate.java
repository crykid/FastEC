package com.blank.art.ec.main.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.animation.BezierAnimation;
import com.blank.art.ec.animation.BezierUtil;
import com.blank.art.ec.entity.GoodsDetailEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.banner.HolderCreator;
import com.blank.art.ui.widget.CircleTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by:blank
 * Created on: 2018/7/14.1:04
 * Description:商品详情
 */
public class GoodsDetailDelegate extends ArtDelegate implements AppBarLayout.OnOffsetChangedListener, BezierUtil.AnimationListener {


    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    private final static String ARG_GOODS_ID = "ARG_GOODS_ID";

    private int mGoodsId = -1;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            //不要动画
            .dontAnimate()
            //重写大小
            .override(100, 100);


    @OnClick(R2.id.rl_add_shop_cart)
    void onClick(View view) {
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);
        BezierAnimation.addCart(this, mRlAddShopCart, mIconShopCart, animImg, this);
    }


    private void setShopCartCount(GoodsDetailEntity goodsDetail) {
        mGoodsThumbUrl = goodsDetail.goodsFrontImage;
        if (mShopCount == 0) {
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    public static GoodsDetailDelegate create(@NonNull int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate detailDelegate = new GoodsDetailDelegate();
        detailDelegate.setArguments(args);
        return detailDelegate;
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
            Toast.makeText(getContext(), "goods id =" + mGoodsId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //设置变换的颜色
        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        mCircleTextView.setCircleBackground(Color.RED);
        initData();
        initTabLayout();
    }


    private void initData() {
        RestClient.builder()
                .url("goods_detail/")
                .params("goods_id", mGoodsId)
                .loader(getContext())
                .success(new ISuccess<GoodsDetailEntity>() {
                    @Override
                    public void onSuccess(GoodsDetailEntity response) {
                        initBanner(response);
                        initGoodsInfo(response);
                        initPager(response);
                        setShopCartCount(response);
                    }
                })
                .build()
                .get();
    }

    private void initPager(GoodsDetailEntity response) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), response.tabs);
        mViewPager.setAdapter(adapter);

    }

    private void initGoodsInfo(GoodsDetailEntity data) {
        getSupportDelegate().
                loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(data));
    }

    private void initBanner(GoodsDetailEntity goodsdetail) {
        List<String> images = new ArrayList<>();
        List<GoodsDetailEntity.ImagesEntity> imagesEntities = goodsdetail.images;


        final int size = imagesEntities.size();
        for (int i = 0; i < size; i++) {
            images.add(imagesEntities.get(i).image);
        }
        mBanner.setPages(new HolderCreator(getContext()), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000)
                .setCanLoop(true);

    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置选中状态时底部线的颜色
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.theme));
        //设置字的颜色
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //打开动画，添加一个水平载入的动画
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimator())
                .duration(500)
                .playOn(mIconShopCart);
        mShopCount++;
        mCircleTextView.setVisibility(View.VISIBLE);
        mCircleTextView.setText(String.valueOf(mShopCount));
        //加入购物车操作

    }
}
