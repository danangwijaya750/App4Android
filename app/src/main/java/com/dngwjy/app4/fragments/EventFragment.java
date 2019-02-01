package com.dngwjy.app4.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.adapters.EventAdapter;
import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.presenters.EventPresenter;
import com.dngwjy.app4.utils.SetUpLayMan;
import com.dngwjy.app4.views.EventView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment implements EventView {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<EventModel> models = new ArrayList<>();
    private EventPresenter presenter;
    private SwipeRefreshLayout refreshLayout;
    private EditText searchEdit;
    public static  Fragment newInstance(){
        EventFragment fragment= new EventFragment();
        return  fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Gson gson= new Gson();
        searchEdit=view.findViewById(R.id.search);
        presenter= new EventPresenter(this,this.getContext(),gson);
        setRefreshLayout(view);
        setAdapter(view);
        setRecyclerView(view);
        presenter.getDataByObserve();
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                 presenter.searchData(searchEdit.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    void setRefreshLayout(View v){
        refreshLayout=v.findViewById(R.id.swiper);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataByObserve();
            }
        });
}
    void setRecyclerView(View v){
        recyclerView= v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(SetUpLayMan.linearLayoutManager(getContext().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setAdapter(adapter);
    }

    void setAdapter(View v){
        adapter= new EventAdapter(v.getContext(),models);
    }

    @Override
    public void ShowLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void FinishLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void LoadData(List<EventModel> data) {
        models.clear();
        models.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
