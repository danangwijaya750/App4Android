package com.dngwjy.app4.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dngwjy.app4.R;
import com.dngwjy.app4.activities.ActivityDetaiEvent;
import com.dngwjy.app4.data.models.EventModel;

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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_items, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Binding(eventModels.get(i));
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, tanggal_event;
        ImageView imageView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.eventName);
            tanggal_event = itemView.findViewById(R.id.eventDate);
            imageView = itemView.findViewById(R.id.eventImage);
            view = itemView;
        }

        void Binding(final EventModel model) {
            judul.setText(model.getTitle());
            tanggal_event.setText(model.getEventDate());
            //Glide.with(itemView).load(model.getFeatureImage()).apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.ic_event_note_black_24dp)).into(imageView);
            Glide.with(context).load(model.getFeatureImage().toString()).apply(new RequestOptions().transform(new RoundedCorners(20)).error(R.drawable.ic_cloud_off_black_24dp)).into(imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ActivityDetaiEvent.class);
                    intent.putExtra("data", model);
                    context.startActivity(intent);
                }
            });
        }
    }
}
