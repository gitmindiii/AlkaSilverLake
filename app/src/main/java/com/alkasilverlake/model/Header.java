package com.alkasilverlake.model;

/**
 * Created by mindiii on 15/11/18.
 */

public class Header
{
    private String waterId;
    private String water_name;

    public Header(String waterId, String water_name) {
        this.waterId = waterId;
        this.water_name = water_name;
    }

    public String getWaterId() {
        return waterId;
    }

    public void setWaterId(String waterId) {
        this.waterId = waterId;
    }

    public String getWater_name() {
        return water_name;
    }

    public void setWater_name(String water_name) {
        this.water_name = water_name;
    }
}