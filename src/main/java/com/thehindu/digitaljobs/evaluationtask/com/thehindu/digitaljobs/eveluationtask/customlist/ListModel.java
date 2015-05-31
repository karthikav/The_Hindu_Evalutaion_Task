package com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.eveluationtask.customlist;

/**
 * Created by karthika on 29-05-2015.
 */
public class ListModel {
    private String id;
    private String projectName;
    private Double latitude;
    private Double longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ListModel(String id, String projectName, Double latitude, Double longitude) {
        this.id = id;
        this.projectName = projectName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
