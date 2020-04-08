package com.qibu.sdk.myapplication.main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.qibu.sdk.myapplication.R;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.util.BannerUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter {

    Context context;
    private List<ItemBean> mList;
    private Fragment mFragment;
    LinkedList<WeakReference<ViewTarget>> linkedList = new LinkedList<>();

    public MainListAdapter(Context c, Fragment fragment) {
        mFragment = fragment;
        context = c;
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ItemBean bean = new ItemBean();
            bean.imageUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image" +
                    "&quality=100&size=b4000_4000&sec=1585132582&di" +
                    "=d4044967683c0c066bfa1a98640ed2da&src=http://a3.att.hudong" + ".com/68/61" + "/300000839764127060614318218_950.jpg";
            bean.name = "item " + i;

            if (i == 0) {
                bean.type = 0;
            } else if (i == 1) {
                bean.type = 1;
            } else {
                bean.type = 2;
            }
            mList.add(bean);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View root = LayoutInflater.from(context).inflate(R.layout.item_act_list_banner,
                    parent, false);
            return new BannerHolder(root);
        } else if (viewType == 1) {
            View root = LayoutInflater.from(context).inflate(R.layout.item_nine_grid_layout,
                    parent, false);
            return new NineGrid(root);
        } else {
            View root = LayoutInflater.from(context).inflate(R.layout.item_act_list, parent, false);
            return new VHolder(root);
        }
    }


    public void onHide() {
        for (WeakReference<ViewTarget> reference : linkedList) {
            if (reference.get() != null) {
                reference.get().onStop();
            }
        }
    }

    public void onShow() {
        for (WeakReference<ViewTarget> reference : linkedList) {
            if (reference.get() != null) {
                reference.get().onStart();
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHolder) {
            VHolder vholder = (VHolder) holder;
            vholder.textView.setText("" + position);
            Glide.with(context).load(mList.get(position).imageUrl).into(vholder.imageView);
        } else if (holder instanceof BannerHolder) {


        } else if (holder instanceof NineGrid) {
            NineGrid vholder = (NineGrid) holder;
            String url1 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585719968365&di=f1ea620109b48b31a814d669a5154495&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg2.imgtn.bdimg" + ".com%2Fit%2Fu%3D1411544067" + "%2C3700879975%26fm%3D214%26gp" + "%3D0.jpg";
            String url2 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585719967591&di=f5b0e3aa5ff1e3df2a3a728dedece3c2&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg.jk51.com%2Fimg_jk51%2F122916876.jpeg";

            String url3 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734696697&di=3b943e8b012ebe2a0ae831e0db2660d1&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg.jk51.com%2Fimg_jk51%2F41066038.jpeg";


            String url4 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734770818&di=d3cc7e291be11f39131411838a50699b&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg3.imgtn.bdimg" + ".com%2Fit%2Fu%3D3557532973" + "%2C2881545285%26fm%3D214%26gp" + "%3D0.jpg";
            String url5 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734696696&di=965f522fceb96bd8611bd00a1cbddd0e&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg.jk51.com%2Fimg_jk51%2F27116238.jpeg";
            String url6 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734696696&di=3e3d3acbd60150b6a61486cd10c93c50&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fwww.foxweixin.com%2Fzmtpcss%2Fimages%2Fd-1.gif";
            String url7 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734696695&di=55f59b0367bf1e1bbd5fd6286d2ecf05&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg.zcool" + ".cn%2Fcommunity" +
                            "%2F01587a59097edba801214550826979.gif";
            String url8 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734696695&di=e0e50075b2f3bc60454b2d404165b571&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fhbimg.b0.upaiyun" + ".com" +
                            "%2F9cc272f63d725dcd94789289f4445bccb73a12027cb22" + "-wne1YB_fw658";
            String url9 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734696695&di=76a3f9ff296439bddbf919ad71fca4b1&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg.jk51.com%2Fimg_jk51%2F44279816.jpeg";
            String url10 =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                            "=1585734845100&di=74637504b9f869e95ca8f7be5da99dc9&imgtype=0&src" +
                            "=http%3A%2F" + "%2Fimg3.imgtn.bdimg" + ".com%2Fit%2Fu%3D2157963198" +
                            "%2C2684051640%26fm%3D214%26gp" + "%3D0.jpg";
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url1).into(vholder.image1)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url2).into(vholder.image2)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url3).into(vholder.image3)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url4).into(vholder.image4)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url5).into(vholder.image5)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url6).into(vholder.image6)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url7).into(vholder.image7)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url8).into(vholder.image8)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url9).into(vholder.image9)));
            linkedList.add(new WeakReference<>(Glide.with(mFragment).asGif().load(url10).into(vholder.image10)));
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }
}


class VHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    public VHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.text);
    }
}

class NineGrid extends RecyclerView.ViewHolder {

    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public ImageView image5;
    public ImageView image6;
    public ImageView image7;
    public ImageView image8;
    public ImageView image9;
    public ImageView image10;

    public NineGrid(@NonNull View itemView) {
        super(itemView);

        image1 = itemView.findViewById(R.id.image1);
        image2 = itemView.findViewById(R.id.image2);
        image3 = itemView.findViewById(R.id.image3);
        image4 = itemView.findViewById(R.id.image4);
        image5 = itemView.findViewById(R.id.image5);
        image6 = itemView.findViewById(R.id.image6);
        image7 = itemView.findViewById(R.id.image7);
        image8 = itemView.findViewById(R.id.image8);
        image9 = itemView.findViewById(R.id.image9);
        image10 = itemView.findViewById(R.id.image10);
    }
}


class BannerHolder extends RecyclerView.ViewHolder {

    TextView head;
    BottomView bottom;

    public BannerHolder(@NonNull View itemView) {
        super(itemView);
        initBanner(itemView);

        head = itemView.findViewById(R.id.head);
        bottom = itemView.findViewById(R.id.banner_bg_bottom);
    }

    private void initBanner(View root) {
        Banner banner = (Banner) root.findViewById(R.id.banner);

        //--------------------------详细使用-------------------------------
        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(root.getContext()));
        banner.setIndicatorSelectedColorRes(R.color.colorPrimary);
        banner.setIndicatorNormalColorRes(R.color.colorAccent);
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT);
        banner.setIndicatorSpace((int) BannerUtils.dp2px(20));
        banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)));
        banner.setIndicatorWidth(10, 20);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object o, int i) {

            }
        });
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i % 2 == 0) {
                    head.setBackgroundColor(Color.parseColor("#FFFF00BB"));
                    bottom.setColor("#FFFF00BB");
                } else {
                    head.setBackgroundColor(Color.parseColor("#FF5500BB"));
                    bottom.setColor("#FF5500BB");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
