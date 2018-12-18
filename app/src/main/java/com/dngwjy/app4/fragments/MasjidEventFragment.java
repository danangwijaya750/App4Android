package com.dngwjy.app4.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.adapters.EventAdapter;
import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.presenters.MasjidEventPresenter;
import com.dngwjy.app4.utils.SetUpLayMan;
import com.dngwjy.app4.views.MasjidEventView;

import java.util.ArrayList;
import java.util.List;

public class MasjidEventFragment extends Fragment implements MasjidEventView {
    public static String idMasjid = "";
    MasjidEventPresenter presenter;
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<EventModel> models = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_masjid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MasjidEventPresenter(this, this.getContext());
        setRefreshLayout(view);
        setAdapter(view);
        setRecyclerView(view);
        presenter.getData(idMasjid);
    }

    void setRefreshLayout(View v) {
        refreshLayout = v.findViewById(R.id.swiper);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // presenter.getData();
                presenter.getData(idMasjid);
            }
        });
    }

    void setRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(SetUpLayMan.linearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setAdapter(adapter);
    }

    void setAdapter(View v) {
        adapter = new EventAdapter(v.getContext(), models);
    }

    @Override
    public void ShowLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void HideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void ShowData(List<EventModel> data) {
        models.clear();
        models.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
