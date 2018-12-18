package com.dngwjy.app4.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.fragments.EventFragment;
import com.dngwjy.app4.fragments.MapsFragment;
import com.dngwjy.app4.views.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private FrameLayout frameLayout;
    private BottomNavigationView navigationView;
    private boolean inMain = true;
    private BottomNavigationView.OnNavigationItemSelectedListener onNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.event:
                    setFragment(EventFragment.newInstance());
                    inMain = true;
                    return true;
                case R.id.maping:
                    setFragment(MapsFragment.newInstance());
                    inMain = false;
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.deploy);
        navigationView = findViewById(R.id.navControl);
        navigationView.setOnNavigationItemSelectedListener(onNav);
        navigationView.setSelectedItemId(R.id.event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (inMain) {
            this.finish();
        } else {
            navigationView.setSelectedItemId(R.id.event);
        }
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.deploy, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showLoad() {
        Toast.makeText(this, "Loading data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        Toast.makeText(this, "Finish Load", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<MasjidModel> data) {

    }


}
