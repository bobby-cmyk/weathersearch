package vttp.batch5.ssf.weatherapi_workshop.models;

public class WeatherResponse {
    
    private boolean isCached;
    private String payload;

    public boolean isCached() {
        return isCached;
    }

    public void setCached(boolean isCached) {
        this.isCached = isCached;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public WeatherResponse(boolean isCached, String payload) {
        this.isCached = isCached;
        this.payload = payload;
    }

}
