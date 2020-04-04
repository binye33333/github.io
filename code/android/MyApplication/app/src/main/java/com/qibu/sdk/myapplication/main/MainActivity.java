package com.qibu.sdk.myapplication.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qibu.sdk.myapplication.R;
import com.qibu.sdk.myapplication.main.fragment.IndexFragment;
import com.qibu.sdk.myapplication.main.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {
    private String TAG = "MainActivity";
    private SparseArray<Fragment> mFragmentArray = new SparseArray<>();
    private int mCurrentTabIndex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_act_main);
        initBottomTab();

        showTab(0);
    }


    private void initBottomTab() {
        LinearLayout linearLayout = findViewById(R.id.bottom);
        Observable observable = Observable.create(new ObservableOnSubscribe<List<TabInfo>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<TabInfo>> emitter) throws Throwable {
                List<TabInfo> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    TabInfo tabInfo = new TabInfo();
                    tabInfo.name = "借钱";
                    tabInfo.unSelectId = R.mipmap.icon_tab_1_unselect;
                    tabInfo.selectId = R.mipmap.icon_tab_1_select;
                    list.add(tabInfo);
                }
                emitter.onNext(list);
            }
        }).subscribeOn(Schedulers.computation()).doOnNext(new Consumer<List<TabInfo>>() {
            @Override
            public void accept(List<TabInfo> tabInfos) throws Throwable {
                Log.d(TAG, "doOnNext 1 " + tabInfos.toString());
            }
        }).flatMap(new Function<List<TabInfo>, ObservableSource<TabInfo>>() {
            @Override
            public ObservableSource<TabInfo> apply(List<TabInfo> tabInfos) throws Throwable {
                Log.d(TAG, "flatMap 1 " + tabInfos.toString());
                TabInfo[] tabInfos1 = new TabInfo[tabInfos.size()];
                tabInfos.toArray(tabInfos1);
                return Observable.fromArray(tabInfos1);
            }
        });

        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<TabInfo>() {
            @Override
            public void accept(TabInfo tabInfo) throws Throwable {
                Log.d(TAG, "Consumer 1 " + tabInfo.toString());
                View item =
                        LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main_act_bottom_tab, linearLayout, false);
                TextView name = item.findViewById(R.id.item_name_tv);
                name.setText(tabInfo.name);
                ImageView imageView = item.findViewById(R.id.item_icon_iv);
                Glide.with(imageView).load(tabInfo.selectId).into(imageView);
                int index = linearLayout.getChildCount();
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTab(index);
                    }
                });
                linearLayout.addView(item);
            }
        });
    }


    private void showTab(int index) {
        if (index == mCurrentTabIndex) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment lastFragment = mFragmentArray.get(mCurrentTabIndex);
        if (lastFragment != null) {
            transaction.detach(lastFragment);
        }

        Fragment fragment = mFragmentArray.get(index);
        if (fragment == null) {
            if (index == 4) {
                fragment = new MeFragment();
            } else {
                fragment = new IndexFragment();
            }
            transaction.add(R.id.content, fragment);
        } else {
            transaction.attach(fragment);
        }
        transaction.commit();
        mCurrentTabIndex = index;
    }
}
