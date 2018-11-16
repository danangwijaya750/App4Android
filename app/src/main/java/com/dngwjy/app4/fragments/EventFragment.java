package com.dngwjy.app4.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.adapters.EventAdapter;
import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.presenters.EventPresenter;
import com.dngwjy.app4.utils.SetUpLayMan;
import com.dngwjy.app4.views.EventView;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment implements EventView {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<EventModel> models = new ArrayList<>();
    private EventPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter= new EventPresenter(this);
        setAdapter(view);
        setRecyclerView(view);
        presenter.getData();
    }

    void setRecyclerView(View v){
        recyclerView= v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(SetUpLayMan.linearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    void setAdapter(View v){
        adapter= new EventAdapter(v.getContext(),models);
    }

    @Override
    public void ShowLoading() {
        Toast.makeText(getContext(),"Loading",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void FinishLoading() {
        Toast.makeText(getContext(),"finish",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoadData(List<EventModel> data) {
        models.clear();
        models.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
