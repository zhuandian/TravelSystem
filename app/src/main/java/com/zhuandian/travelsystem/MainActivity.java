package com.zhuandian.travelsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.base.BaseFragment;
import com.zhuandian.travelsystem.adapter.HomePageAdapter;
import com.zhuandian.travelsystem.business.fragment.HomeFragment;
import com.zhuandian.travelsystem.business.fragment.HotelFragment;
import com.zhuandian.travelsystem.business.fragment.MyFragment;
import com.zhuandian.travelsystem.business.fragment.SceneryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_home)
    ViewPager vpHome;
    @BindView(R.id.tab_bottom)
    BottomNavigationView tabBottom;
    private static final int PAGE_HOME = 0;
    private static final int PAGE_SCENERY = 1;
    private static final int PAGE_HOTEL = 2;
    private static final int PAGE_MY = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SceneryFragment());
        fragmentList.add(new HotelFragment());
        fragmentList.add(new MyFragment());

        vpHome.setAdapter(new HomePageAdapter(getSupportFragmentManager(), fragmentList));
        vpHome.setOffscreenPageLimit(4);

        vpHome.setCurrentItem(PAGE_HOME);
        initBottomTab();

    }

    private void initBottomTab() {
        vpHome.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabBottom.getMenu().getItem(position).setChecked(true);
            }
        });
        tabBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_home:
                        vpHome.setCurrentItem(PAGE_HOME);
                        break;
                    case R.id.tab_scenery:
                        vpHome.setCurrentItem(PAGE_SCENERY);
                        break;
                    case R.id.tab_hotel:
                        vpHome.setCurrentItem(PAGE_HOTEL);
                        break;
                    case R.id.tab_my:
                        vpHome.setCurrentItem(PAGE_MY);
                        break;
                }
                return true;
            }
        });
    }


}
