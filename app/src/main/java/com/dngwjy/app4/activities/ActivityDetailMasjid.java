package com.dngwjy.app4.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.fragments.MasjidDescFragment;
import com.dngwjy.app4.fragments.MasjidEventFragment;
import com.dngwjy.app4.fragments.MasjidKasFragment;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailMasjid extends AppCompatActivity {
    ImageView imageView;
    MasjidModel model;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_masjid);
        tabLayout = findViewById(R.id.teamTab);
        setViewPager();
        tabLayout.setupWithViewPager(viewPager);
        initData();
        imageView = findViewById(R.id.masjidImage);
        Glide.with(this).load(model.getImage()).into(imageView);
        MasjidEventFragment.idMasjid = model.getId();
    }

    void initData() {
        model = getIntent().getExtras().getParcelable("data");
        Log.d("init", "initData: " + model.getNama_masjid());

    }

    void setViewPager() {
        viewPager = findViewById(R.id.pagger);
        setupViewPage(viewPager);
    }

    private void setupViewPage(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment("Detail", new MasjidDescFragment());
        adapter.addFragment("Keuangan", new MasjidKasFragment());
        adapter.addFragment("Event", new MasjidEventFragment());
        viewPager.setAdapter(adapter);
    }


    private class ViewPageAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> title = new ArrayList<>();

        public ViewPageAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);

        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(String title, Fragment fragment) {
            this.title.add(title);
            this.fragmentList.add(fragment);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);

        }
    }
}
