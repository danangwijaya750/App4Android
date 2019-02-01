package com.dngwjy.app4.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.MasjidModel;

public class MasjidDescFragment extends Fragment {
    public static MasjidModel model;
    private EditText masjidName;
    private EditText alamatMasjid;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_masjid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alamatMasjid=view.findViewById(R.id.alamatMasjid);
        masjidName=view.findViewById(R.id.namaMasjid);
        alamatMasjid.setText(model.getAlamat());
        masjidName.setText(model.getNama_masjid());
    }
}
