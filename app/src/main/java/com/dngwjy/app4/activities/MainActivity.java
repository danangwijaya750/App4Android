package com.dngwjy.app4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.adapters.MainAdapter;
import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.presenters.MainPresenter;
import com.dngwjy.app4.utils.SetUpLayMan;
import com.dngwjy.app4.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
private MainPresenter mainPresenter;
private  RecyclerView recyclerView;
private MainAdapter adapter;
private List<MasjidModel> masjidModels= new ArrayList<>();
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter= new MainPresenter(this);
        adapter=new MainAdapter(this,masjidModels);
        recyclerView=(RecyclerView)findViewById(R.id.recMasjid);
        afterCreate();
    }
    void afterCreate(){
        recyclerView.setLayoutManager(SetUpLayMan.linearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mainPresenter.getData();
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
        masjidModels.clear();
        masjidModels.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
