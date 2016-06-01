package com.repsly.careline.model;

/**
 * Created by tosulc on 01.06.2016..
 */
public class TrackingEvent {
    public int EventTypeID;
    public String EventDateTime;
    public double Latitude;
    public double Longitude;
    public String RelatedScheduleRowid;

    public TrackingEvent(int eventTypeID, String eventDateTime, double latitude, double longitude,
                         String relatedScheduleRowid) {
        EventTypeID = eventTypeID;
        EventDateTime = eventDateTime;
        Latitude = latitude;
        Longitude = longitude;
        RelatedScheduleRowid = relatedScheduleRowid;
    }
}
