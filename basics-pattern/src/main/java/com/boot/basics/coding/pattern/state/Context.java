package com.boot.basics.coding.pattern.state;
/**
 * @Author cherrishccl
 * @Date 2020/9/1 17:38
 * @Version 1.0
 * @Description
 */
public class Context {
    private Weather weather;

    public String reportWeather(){
        return weather.getWether();
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
