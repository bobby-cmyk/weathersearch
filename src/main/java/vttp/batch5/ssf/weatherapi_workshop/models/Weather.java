package vttp.batch5.ssf.weatherapi_workshop.models;

import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Logger;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Weather {

    // Define the logger
    private static final Logger logger = Logger.getLogger(Weather.class.getName());
    
    private String weatherId;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;

    private float mainTemp;
    private float mainFeelsLike;
    private float mainTempMin;
    private float mainTempMax;
    private int mainPressure;
    private int mainHumidity;
    private int mainSeaLevel;
    private int mainGrndLevel;

    private int visibility;

    private float windSpeed;
    private int windDeg;

    private int cloudsAll;
    
    //Where available
    private float rain1hr;

    private float snow1hr;

    private int dataCalTimeUnix;

    private String countryCode;

    private int sunriseUnix;

    private int sunsetUnix;

    private int timezone;

    private String cityId;

    private String cityName;

    @Override
    public String toString() {
        return "Weather [weatherId=" + weatherId + ", weatherMain=" + weatherMain 
                + ", weatherDescription=" + weatherDescription + ", weatherIcon=" + weatherIcon 
                
                + ", mainTemp=" + getMainTempRound() + ", mainFeelsLike=" + getMainFeelsLikeRound()
                + ", mainTempMin=" + getMainTempMinRound() + ", mainTempMax=" + getMainTempMaxRound()
                + ", mainPressure=" + mainPressure + ", mainHumidity=" + mainHumidity 
                + ", mainSeaLevel=" + mainSeaLevel + ", mainGrndLevel=" + mainGrndLevel 
                
                + ", visibility=" + visibility 
                
                + ", windSpeed=" + windSpeed + ", windDeg=" + windDeg 
                
                + ", cloudsAll=" + cloudsAll 
                + ", rain1hr=" + rain1hr 
                + ", snow1hr=" + snow1hr 
            
                + ", dataCalTimeLocal=" + getDataCalTimeLocal()
                + ", sunriseLocal=" + getSunriseLocal() 
                + ", sunsetLocal=" + getSunsetLocal() 

                + ", countryCode=" + countryCode
                + ", timezone=" + timezone 
                + ", cityId=" + cityId
                + ", cityName=" + cityName
                + ", countryName=" + getCountryName()  + "]";
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public float getMainTemp() {
        return mainTemp;
    }

    public int getMainTempRound() {
        return Math.round(mainTemp);
    }

    public void setMainTemp(float mainTemp) {
        this.mainTemp = mainTemp;
    }

    public float getMainFeelsLike() {
        return mainFeelsLike;
    }

    public int getMainFeelsLikeRound() {
        return Math.round(mainFeelsLike);
    }

    public void setMainFeelsLike(float mainFeelsLike) {
        this.mainFeelsLike = mainFeelsLike;
    }

    public float getMainTempMin() {
        return mainTempMin;
    }

    public int getMainTempMinRound() {
        return Math.round(mainTempMin);
    }

    public void setMainTempMin(float mainTempMin) {
        this.mainTempMin = mainTempMin;
    }

    public float getMainTempMax() {
        return mainTempMax;
    }
    public int getMainTempMaxRound() {
        return Math.round(mainTempMax);
    }

    public void setMainTempMax(float mainTempMax) {
        this.mainTempMax = mainTempMax;
    }

    public int getMainPressure() {
        return mainPressure;
    }

    public void setMainPressure(int mainPressure) {
        this.mainPressure = mainPressure;
    }

    public int getMainHumidity() {
        return mainHumidity;
    }

    public void setMainHumidity(int mainHumidity) {
        this.mainHumidity = mainHumidity;
    }

    public int getMainSeaLevel() {
        return mainSeaLevel;
    }

    public void setMainSeaLevel(int mainSeaLevel) {
        this.mainSeaLevel = mainSeaLevel;
    }

    public int getMainGrndLevel() {
        return mainGrndLevel;
    }

    public void setMainGrndLevel(int mainGrndLevel) {
        this.mainGrndLevel = mainGrndLevel;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public int getWindSpeedRound() {
        return Math.round(windSpeed);
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }
    

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public int getCloudsAll() {
        return cloudsAll;
    }

    public void setCloudsAll(int cloudsAll) {
        this.cloudsAll = cloudsAll;
    }

    public float getRain1hr() {
        return rain1hr;
    }

    public void setRain1hr(float rain1hr) {
        this.rain1hr = rain1hr;
    }

    public float getSnow1hr() {
        return snow1hr;
    }

    public void setSnow1hr(float snow1hr) {
        this.snow1hr = snow1hr;
    }

    public int getDataCalTimeUnix() {
        return dataCalTimeUnix;
    }

    public String getDataCalTimeUTC() {

        LocalDateTime datetime = Instant.ofEpochSecond(dataCalTimeUnix).atZone(ZoneOffset.UTC).toLocalDateTime();

        return datetimeToString(datetime);
    }

    public String getDataCalTimeLocal() {
        
        LocalDateTime datetime = Instant.ofEpochSecond(dataCalTimeUnix + timezone).atZone(ZoneOffset.UTC).toLocalDateTime();

        return datetimeToString(datetime);
    }

    public void setDataCalTimeUnix(int dataCalTimeUnix) {
        this.dataCalTimeUnix = dataCalTimeUnix;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getSunriseUnix() {
        return sunriseUnix;
    }

    public String getSunriseUTC() {

        LocalTime time = Instant.ofEpochSecond(sunriseUnix).atZone(ZoneOffset.UTC).toLocalTime();

        return timeToString(time);
    }

    public String getSunriseLocal() {

        LocalTime time = Instant.ofEpochSecond(sunriseUnix + timezone).atZone(ZoneOffset.UTC).toLocalTime();

        return timeToString(time);
    }

    public String getSunriseLocalTime() {

        LocalTime time = Instant.ofEpochSecond(sunriseUnix + timezone).atZone(ZoneOffset.UTC).toLocalTime();
        String timeWithUnit = timeToString(time);
        String[] array = timeWithUnit.split(" ");
        String timeString = array[0];
        return timeString;
    }

    public String getSunriseLocalUnit() {
        
        LocalTime time = Instant.ofEpochSecond(sunriseUnix + timezone).atZone(ZoneOffset.UTC).toLocalTime();
        String timeWithUnit = timeToString(time);
        String[] array = timeWithUnit.split(" ");
        String unitString = array[1];
        return unitString;
    }

    public void setSunriseUnix(int sunriseUnix) {
        this.sunriseUnix = sunriseUnix;
    }

    public int getSunsetUnix() {
        return sunsetUnix;
    }

    public String getSunsetUTC() {

        LocalTime time = Instant.ofEpochSecond(sunsetUnix).atZone(ZoneOffset.UTC).toLocalTime();

        return timeToString(time);
    }

    public String getSunsetLocal() {

        LocalTime time = Instant.ofEpochSecond(sunsetUnix + timezone).atZone(ZoneOffset.UTC).toLocalTime();

        return timeToString(time);
    }

    public String getSunsetLocalTime() {

        LocalTime time = Instant.ofEpochSecond(sunsetUnix + timezone).atZone(ZoneOffset.UTC).toLocalTime();
        String timeWithUnit = timeToString(time);
        String[] array = timeWithUnit.split(" ");
        String timeString = array[0];
        return timeString;
    }

    public String getSunsetLocalUnit() {

        LocalTime time = Instant.ofEpochSecond(sunsetUnix + timezone).atZone(ZoneOffset.UTC).toLocalTime();
        String timeWithUnit = timeToString(time);
        String[] array = timeWithUnit.split(" ");
        String unitString = array[1];
        return unitString;
    }

    public String getCurrentLocalTime() {

        LocalDateTime datetime =Instant.now().plusSeconds(timezone).atZone(ZoneOffset.UTC).toLocalDateTime();
         
        return datetimeToString(datetime);
    }

    private String datetimeToString(LocalDateTime datetime) {

        int month  = datetime.getMonthValue();

        // https://docs.oracle.com/javase/10/docs/api/java/time/format/TextStyle.html
        String monthString = Month.of(month).getDisplayName(TextStyle.SHORT_STANDALONE , Locale.ENGLISH);

        int day = datetime.getDayOfMonth();
        int year = datetime.getYear();

        String date = day + " " + monthString + " " + year;
        String time = timeToString(datetime.toLocalTime());

        return date + ", " + time;
    }

    private String timeToString(LocalTime time) {
        int hour = time.getHour();
        int min = time.getMinute();

        String timeString = "";

        if (hour == 0) {
            // Handle midnight (12:00 AM)
            timeString = "12:" + String.format("%02d", min) + " am";
        } else if (hour < 12) {
            // Morning hours (1 AM to 11:59 AM)
            timeString = hour + ":" + String.format("%02d", min) + " am";
        } else if (hour == 12) {
            // Noon (12:00 PM)
            timeString = "12:" + String.format("%02d", min) + " pm";
        } else {
            // Afternoon/evening hours (1 PM to 11:59 PM)
            timeString = (hour - 12) + ":" + String.format("%02d", min) + " pm";
        }
        
        return timeString;
    }

    public void setSunsetUnix(int sunsetUnix) {
        this.sunsetUnix = sunsetUnix;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityNameLowercase() {
        return cityName.toLowerCase();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {

        return new Locale.Builder().setRegion(this.countryCode).build().getDisplayName();
    }

    // factory method, does not require 
    public static Weather toWeather(String jsonPayload) {

        
        Weather weather = new Weather();

        try {
            JsonReader reader = Json.createReader(new StringReader(jsonPayload));

            JsonObject jsonObj = reader.readObject();

            JsonArray weatherArr = jsonObj.getJsonArray("weather");

            // Only one weather so 0 index in array -> unless breaks lol
            JsonObject weatherObj = weatherArr.getJsonObject(0);

            weather.setWeatherId(String.valueOf(weatherObj.getInt("id")));
            weather.setWeatherMain(weatherObj.getString("main"));
            weather.setWeatherDescription(weatherObj.getString("description"));
            weather.setWeatherIcon(weatherObj.getString("icon"));

            JsonObject mainObj = jsonObj.getJsonObject("main");

            weather.setMainTemp((float) mainObj.getJsonNumber("temp").doubleValue());
            weather.setMainFeelsLike((float) mainObj.getJsonNumber("feels_like").doubleValue());
            weather.setMainTempMin((float) mainObj.getJsonNumber("temp_min").doubleValue());
            weather.setMainTempMax((float) mainObj.getJsonNumber("temp_max").doubleValue());
            weather.setMainPressure(mainObj.getInt("pressure"));
            weather.setMainHumidity(mainObj.getInt("humidity"));
            weather.setMainSeaLevel(mainObj.getInt("sea_level"));
            weather.setMainGrndLevel(mainObj.getInt("grnd_level"));

            weather.setVisibility(jsonObj.getInt("visibility"));

            JsonObject windObj = jsonObj.getJsonObject("wind");

            weather.setWindSpeed((float) windObj.getJsonNumber("speed").doubleValue());
            weather.setWindDeg(windObj.getInt("deg"));
            // Does not exist anymore
            //weather.setWindGust((float) windObj.getJsonNumber("gust").doubleValue());

            weather.setCloudsAll(jsonObj.getJsonObject("clouds").getInt("all"));

            try {
                weather.setRain1hr((float) jsonObj.getJsonObject("rain").getJsonNumber("1h").doubleValue());
            }

            catch (Exception e)
            {
                logger.info("Rain info not available.");
            }

            try {
                weather.setSnow1hr((float) jsonObj.getJsonObject("snow").getJsonNumber("1h").doubleValue());
            }

            catch (Exception e)
            {
                logger.info("Snow info not available.");
            }

            weather.setDataCalTimeUnix(jsonObj.getInt("dt"));

            JsonObject sysObj = jsonObj.getJsonObject("sys");

            weather.setCountryCode(sysObj.getString("country"));

            weather.setSunriseUnix(sysObj.getInt("sunrise"));

            weather.setSunsetUnix(sysObj.getInt("sunset"));

            weather.setTimezone(jsonObj.getInt("timezone"));

            weather.setCityId(String.valueOf(jsonObj.getInt("id")));

            weather.setCityName(jsonObj.getString("name"));

        }

        catch (Exception e) {
            logger.warning("Error while parsing JSON to weather object - Error message: %s.".formatted(e.getMessage()));       
        }

        return weather;
    }
}