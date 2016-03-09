import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public final class Weather {
    public final long latitude;
    public final double longitude;
    public final String timezone;
    public final long offset;
    public final Currently currently;
    public final Hourly hourly;
    public final Daily daily;
    public final Flags flags;

    @JsonCreator
    public Weather(@JsonProperty("latitude") long latitude, @JsonProperty("longitude") double longitude, @JsonProperty("timezone") String timezone, @JsonProperty("offset") long offset, @JsonProperty("currently") Currently currently, @JsonProperty("hourly") Hourly hourly, @JsonProperty("daily") Daily daily, @JsonProperty("flags") Flags flags){
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.offset = offset;
        this.currently = currently;
        this.hourly = hourly;
        this.daily = daily;
        this.flags = flags;
    }

    public static final class Currently {
        public final long time;
        public final String summary;
        public final String icon;
        public final double precipIntensity;
        public final double precipProbability;
        public final String precipType;
        public final double temperature;
        public final double apparentTemperature;
        public final double dewPoint;
        public final double humidity;
        public final double windSpeed;
        public final long windBearing;
        public final double visibility;
        public final double cloudCover;
        public final double pressure;
        public final String ozone;

        @JsonCreator
        public Currently(@JsonProperty("ozone") String ozone,@JsonProperty("time") long time, @JsonProperty("summary") String summary, @JsonProperty("icon") String icon, @JsonProperty("precipIntensity") double precipIntensity, @JsonProperty("precipProbability") double precipProbability, @JsonProperty("precipType") String precipType, @JsonProperty("temperature") double temperature, @JsonProperty("apparentTemperature") double apparentTemperature, @JsonProperty("dewPoint") double dewPoint, @JsonProperty("humidity") double humidity, @JsonProperty("windSpeed") double windSpeed, @JsonProperty("windBearing") long windBearing, @JsonProperty("visibility") double visibility, @JsonProperty("cloudCover") double cloudCover, @JsonProperty("pressure") double pressure){
            this.time = time;
            this.summary = summary;
            this.icon = icon;
            this.precipIntensity = precipIntensity;
            this.precipProbability = precipProbability;
            this.precipType = precipType;
            this.temperature = temperature;
            this.apparentTemperature = apparentTemperature;
            this.dewPoint = dewPoint;
            this.humidity = humidity;
            this.windSpeed = windSpeed;
            this.windBearing = windBearing;
            this.visibility = visibility;
            this.cloudCover = cloudCover;
            this.pressure = pressure;
            this.ozone=ozone;
        }
    }

    public static final class Hourly {
        public final String summary;
        public final String icon;
        public final Data data[];

        @JsonCreator
        public Hourly(@JsonProperty("summary") String summary, @JsonProperty("icon") String icon, @JsonProperty("data") Data[] data){
            this.summary = summary;
            this.icon = icon;
            this.data = data;
        }

        public static final class Data {
            public final long time;
            public final String summary;
            public final String icon;
            public final long precipIntensity;
            public final long precipProbability;
            public final double temperature;
            public final double apparentTemperature;
            public final double dewPoint;
            public final double humidity;
            public final double windSpeed;
            public final long windBearing;
            public final double visibility;
            public final double cloudCover;
            public final double pressure;
            public final String precipType;
            public final String precipAccumulation;
            public final String ozone;

            @JsonCreator
            public Data(@JsonProperty("ozone") String ozone,@JsonProperty("precipAccumulation") String precipAccumulation, @JsonProperty("time") long time, @JsonProperty("summary") String summary, @JsonProperty("icon") String icon, @JsonProperty("precipIntensity") long precipIntensity, @JsonProperty("precipProbability") long precipProbability, @JsonProperty("temperature") double temperature, @JsonProperty("apparentTemperature") double apparentTemperature, @JsonProperty("dewPoint") double dewPoint, @JsonProperty("humidity") double humidity, @JsonProperty("windSpeed") double windSpeed, @JsonProperty("windBearing") long windBearing, @JsonProperty("visibility") double visibility, @JsonProperty("cloudCover") double cloudCover, @JsonProperty("pressure") double pressure, @JsonProperty(value="precipType", required=false) String precipType){
                this.time = time;
                this.summary = summary;
                this.icon = icon;
                this.precipIntensity = precipIntensity;
                this.precipProbability = precipProbability;
                this.temperature = temperature;
                this.apparentTemperature = apparentTemperature;
                this.dewPoint = dewPoint;
                this.humidity = humidity;
                this.windSpeed = windSpeed;
                this.windBearing = windBearing;
                this.visibility = visibility;
                this.cloudCover = cloudCover;
                this.pressure = pressure;
                this.precipType = precipType;
                this.precipAccumulation = precipAccumulation;
                this. ozone=ozone;
            }
        }
    }
    @JsonIgnoreProperties(ignoreUnknown=true)
    public static final class Daily {
        public final Data data[];

        @JsonCreator
        public Daily(@JsonProperty("data") Data[] data){
            this.data = data;
        }

        @JsonIgnoreProperties(ignoreUnknown=true)
        public static final class Data {
            public final long time;
            public final String summary;
            public final String icon;
            public final long sunriseTime;
            public final long sunsetTime;
            public final double moonPhase;
            public final double precipIntensity;
            public final double precipIntensityMax;
            public final long precipIntensityMaxTime;
            public final double precipProbability;
            public final String precipType;
            public final double temperatureMin;
            public final long temperatureMinTime;
            public final double temperatureMax;
            public final long temperatureMaxTime;
            public final double apparentTemperatureMin;
            public final long apparentTemperatureMinTime;
            public final double apparentTemperatureMax;
            public final long apparentTemperatureMaxTime;
            public final double dewPoint;
            public final double humidity;
            public final double windSpeed;
            public final long windBearing;
            public final double visibility;
            public final double cloudCover;
            public final double pressure;
            public final String ozone;

            @JsonCreator
            public Data(@JsonProperty("ozone") String ozone,@JsonProperty("time") long time, @JsonProperty("summary") String summary, @JsonProperty("icon") String icon, @JsonProperty("sunriseTime") long sunriseTime, @JsonProperty("sunsetTime") long sunsetTime, @JsonProperty("moonPhase") double moonPhase, @JsonProperty("precipIntensity") double precipIntensity, @JsonProperty("precipIntensityMax") double precipIntensityMax, @JsonProperty("precipIntensityMaxTime") long precipIntensityMaxTime, @JsonProperty("precipProbability") double precipProbability, @JsonProperty("precipType") String precipType, @JsonProperty("temperatureMin") double temperatureMin, @JsonProperty("temperatureMinTime") long temperatureMinTime, @JsonProperty("temperatureMax") double temperatureMax, @JsonProperty("temperatureMaxTime") long temperatureMaxTime, @JsonProperty("apparentTemperatureMin") double apparentTemperatureMin, @JsonProperty("apparentTemperatureMinTime") long apparentTemperatureMinTime, @JsonProperty("apparentTemperatureMax") double apparentTemperatureMax, @JsonProperty("apparentTemperatureMaxTime") long apparentTemperatureMaxTime, @JsonProperty("dewPoint") double dewPoint, @JsonProperty("humidity") double humidity, @JsonProperty("windSpeed") double windSpeed, @JsonProperty("windBearing") long windBearing, @JsonProperty("visibility") double visibility, @JsonProperty("cloudCover") double cloudCover, @JsonProperty("pressure") double pressure){
                this.time = time;
                this.summary = summary;
                this.icon = icon;
                this.sunriseTime = sunriseTime;
                this.sunsetTime = sunsetTime;
                this.moonPhase = moonPhase;
                this.precipIntensity = precipIntensity;
                this.precipIntensityMax = precipIntensityMax;
                this.precipIntensityMaxTime = precipIntensityMaxTime;
                this.precipProbability = precipProbability;
                this.precipType = precipType;
                this.temperatureMin = temperatureMin;
                this.temperatureMinTime = temperatureMinTime;
                this.temperatureMax = temperatureMax;
                this.temperatureMaxTime = temperatureMaxTime;
                this.apparentTemperatureMin = apparentTemperatureMin;
                this.apparentTemperatureMinTime = apparentTemperatureMinTime;
                this.apparentTemperatureMax = apparentTemperatureMax;
                this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
                this.dewPoint = dewPoint;
                this.humidity = humidity;
                this.windSpeed = windSpeed;
                this.windBearing = windBearing;
                this.visibility = visibility;
                this.cloudCover = cloudCover;
                this.pressure = pressure;
                this.ozone=ozone;

            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static final class Flags {
        public final String[] sources;
        public final String[] isd_stations;
        public final String units;


        @JsonCreator
        public Flags(@JsonProperty("sources") String[] sources, @JsonProperty("isd-stations") String[] isd_stations, @JsonProperty("units") String units){
            this.sources = sources;
            this.isd_stations = isd_stations;
            this.units = units;
        }
    }
}