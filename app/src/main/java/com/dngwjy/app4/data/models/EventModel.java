package com.dngwjy.app4.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventModel implements Parcelable {

    public static final Creator<EventModel> CREATOR = new Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel in) {
            return new EventModel(in);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_masjid")
    @Expose
    private String idMasjid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("feature_image")
    @Expose
    private String featureImage;

    protected EventModel(Parcel in) {
        id = in.readString();
        idMasjid = in.readString();
        title = in.readString();
        body = in.readString();
        created = in.readString();
        eventDate = in.readString();
        featureImage = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMasjid() {
        return idMasjid;
    }

    public void setIdMasjid(String idMasjid) {
        this.idMasjid = idMasjid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idMasjid);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(created);
        dest.writeString(eventDate);
        dest.writeString(featureImage);
    }
}
