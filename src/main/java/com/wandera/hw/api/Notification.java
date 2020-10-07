package com.wandera.hw.api;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Notification implements Comparable<Notification> {

    private String notificationGuid;
    private String deviceGuid;
    private String userGuid;
    private String eventType;
    private String eventSubtype;
    private Double geofenceLat;
    private Double geofenceLon;
    private Double geofenceRadiusMetres;
    private String title;
    private String content;
    private Date eventTimestamp;
    private Date sentTimestamp;
    private Boolean displayedStatus;

    public Notification() {
    }

    public Notification(List<String> paramList) {
        this.notificationGuid = paramList.get(0);
        this.deviceGuid = paramList.get(1);
        this.userGuid = paramList.get(2);
        this.eventType = paramList.get(3);
        this.eventSubtype = paramList.get(4);
        this.geofenceLat = (paramList.get(5).isEmpty() ? null : new Double(paramList.get(5)));
        this.geofenceLon = (paramList.get(6).isEmpty() ? null : new Double(paramList.get(6)));
        this.geofenceRadiusMetres = (paramList.get(7).isEmpty() ? null : new Double(paramList.get(7)));
        this.title = paramList.get(8);
        this.content = paramList.get(9);
        this.eventTimestamp = new Date(new Long(paramList.get(10)));
        this.sentTimestamp = new Date(new Long(paramList.get(11)));
        this.displayedStatus = false;
    }

    @JsonProperty
    public String getNotificationGuid() {
        return notificationGuid;
    }

    @JsonProperty
    public void setNotificationGuid(String notificationGuid) {
        this.notificationGuid = notificationGuid;
    }

    @JsonProperty
    public String getDeviceGuid() {
        return deviceGuid;
    }

    @JsonProperty
    public void setDeviceGuid(String deviceGuid) {
        this.deviceGuid = deviceGuid;
    }

    @JsonProperty
    public String getUserGuid() {
        return userGuid;
    }

    @JsonProperty
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    @JsonProperty
    public String getEventType() {
        return eventType;
    }

    @JsonProperty
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty
    public String getEventSubtype() {
        return eventSubtype;
    }

    @JsonProperty
    public void setEventSubtype(String eventSubtype) {
        this.eventSubtype = eventSubtype;
    }

    @JsonProperty
    public Double getGeofenceLat() {
        return geofenceLat;
    }

    @JsonProperty
    public void setGeofenceLat(Double geofenceLat) {
        this.geofenceLat = geofenceLat;
    }

    @JsonProperty
    public Double getGeofenceLon() {
        return geofenceLon;
    }

    @JsonProperty
    public void setGeofenceLon(Double geofenceLon) {
        this.geofenceLon = geofenceLon;
    }

    @JsonProperty
    public Double getGeofenceRadiusMetres() {
        return geofenceRadiusMetres;
    }

    @JsonProperty
    public void setGeofenceRadiusMetres(Double geofenceRadiusMetres) {
        this.geofenceRadiusMetres = geofenceRadiusMetres;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty
    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    @JsonProperty
    public void setEventTimestamp(Long eventTimestamp) {
        this.eventTimestamp = new Date(eventTimestamp);
    }

    @JsonProperty
    public Date getSentTimestamp() {
        return sentTimestamp;
    }

    @JsonProperty
    public void setSentTimestamp(Long sentTimestamp) {
        this.sentTimestamp = new Date(sentTimestamp);
    }

    @JsonProperty
    public Boolean getDisplayedStatus() {
        return displayedStatus;
    }

    @JsonProperty
    public void setDisplayedStatus(Boolean displayedStatus) {
        this.displayedStatus = displayedStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        return notificationGuid.equals(that.notificationGuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationGuid);
    }

    @Override
    public int compareTo(Notification o) {

        return eventTimestamp.compareTo(o.eventTimestamp);
    }
}
