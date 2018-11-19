package com.dngwjy.app4.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.EventModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EventModel> eventModels;

    public EventAdapter(Context context, List<EventModel> eventModels) {
        this.context = context;
        this.eventModels = eventModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_items, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.judul.setText(eventModels.get(i).getTitle());
        viewHolder.tanggal_event.setText(eventModels.get(i).getEventDate());
        Picasso.get().load(eventModels.get(i).getFeatureImage()).noFade().into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, tanggal_event;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.eventName);
            tanggal_event = itemView.findViewById(R.id.eventDate);
            imageView = itemView.findViewById(R.id.eventImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,judul.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
