package pl.hubertgawrys.weather.models;

public class WeatherModel {
    private String cityName;
    private int temperature;
    private int pressure;
    private int humidity;
    private int clouds;
    private String weatherComment;
    private int lat;
    private int lng;
    private double value;
    private double precision;

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    private WeatherModel(Builder builder) {
        cityName = builder.getCityName();
        temperature = builder.getTemperature();
        pressure = builder.getPressure();
        humidity = builder.getHumidity();
        clouds = builder.getClouds();
        weatherComment = builder.getWeatherComment();
        value = builder.getValue();
        precision = builder.getPrecision();
        lat = builder.getLat();
        lng = builder.getLng();
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public String getWeatherComment() {
        return weatherComment;
    }

    public void setWeatherComment(String weatherComment) {
        this.weatherComment = weatherComment;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", clouds=" + clouds +
                ", weatherComment='" + weatherComment + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", value=" + value +
                ", precision=" + precision +
                '}';
    }

    public static class Builder{
        private String cityName;
        private int temperature;
        private int pressure;
        private int humidity;
        private int clouds;
        private String weatherComment;
        private double value;
        private double precision;
        private int lat;
        private int lng;

        public Builder(String cityName){
            this.cityName = cityName;
        }
        public Builder setLat(int lat){
            this.lat = lat;
            return this;
        }
        public Builder setLng(int lng){
            this.lng = lng;
            return this;
        }
        public Builder setPrecision(double precision){
            this.precision = precision;
            return this;
        }
        public Builder setValue(double value){
            this.value = value;
            return this;
        }

        public Builder setTemperature(int temp){
            this.temperature = temp;
            return this;
        }

        public Builder setPressure(int pressure){
            this.pressure = pressure;
            return this;
        }

        public Builder setHumidity(int humidity){
            this.humidity = humidity;
            return this;
        }

        public Builder setClouds(int clouds){
            this.clouds = clouds;
            return this;
        }

        public Builder setWeatherComment(String comment){
            this.weatherComment = comment;
            return this;
        }
        public int getLat() {
            return lat;
        }
        public int getLng() {
            return lng;
        }
        public double getValue() {
            return value;
        }
        public double getPrecision() {
            return precision;
        }
        public String getCityName() {
            return cityName;
        }

        public int getTemperature() {
            return temperature;
        }

        public int getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getClouds() {
            return clouds;
        }

        public String getWeatherComment() {
            return weatherComment;
        }

        public WeatherModel build() {
            return new WeatherModel(this);
        }
    }
}
