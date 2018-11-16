package com.dngwjy.app4.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.MasjidModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHold> {
    Context context;
   List<MasjidModel> masjidModels;

    public MainAdapter(Context context, List<MasjidModel> masjidModels) {
        this.context = context;
        this.masjidModels = masjidModels;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHold(LayoutInflater.from(context).inflate(R.layout.masjid_items,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold viewHold, int i) {
        viewHold.masjid.setText(masjidModels.get(i).getNama_masjid());
        viewHold.alamat.setText(masjidModels.get(i).getAlamat());
        Picasso.get().load(masjidModels.get(i).getImage()).into(viewHold.imageView);
    }

    @Override
    public int getItemCount() {
        return masjidModels.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        TextView masjid,alamat;
        ImageView imageView;
        public ViewHold(@NonNull View itemView) {
            super(itemView);
            masjid=(TextView)itemView.findViewById(R.id.namaMasjid);
            alamat=(TextView)itemView.findViewById(R.id.alamatMasjid);
            imageView=(ImageView)itemView.findViewById(R.id.imageMasjid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,masjid.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
