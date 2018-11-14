package com.dngwjy.app4.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.adapters.MainAdapter;
import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.fragments.EventFragment;
import com.dngwjy.app4.fragments.MapsFragment;
import com.dngwjy.app4.presenters.MainPresenter;
import com.dngwjy.app4.utils.SetUpLayMan;
import com.dngwjy.app4.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
private TabLayout tabLayout;
private ViewPager viewPager;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.htab_tabs);
        viewPager=findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPage){
       ViewPagerPagerAdapter adapter= new ViewPagerPagerAdapter(getSupportFragmentManager());
       adapter.addFragment(new EventFragment(),"Event");
       adapter.addFragment(new MapsFragment(),"Maps");
        viewPage.setAdapter(adapter);
}

    @Override
    public void showLoad() {
        Toast.makeText(this,"Loading data",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        Toast.makeText(this,"Finish Load",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<MasjidModel> data) {

    }

    private class ViewPagerPagerAdapter extends FragmentPagerAdapter {
    final List<Fragment> fragmentList= new ArrayList<>();
    final List<String> titleList= new ArrayList<>();
        public ViewPagerPagerAdapter(FragmentManager supportFragmentManager) {
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

        void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
