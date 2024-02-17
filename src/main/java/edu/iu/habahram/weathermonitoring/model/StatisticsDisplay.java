package edu.iu.habahram.weathermonitoring.model;
import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;

    private float hiTemp;
    private float lowTemp;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData){
        this.weatherData = weatherData;
    }


    @Override
    public String display(){
        String html = "";
        html += String.format("<div style=\"background-image: " +
        "url(/images/cartoon.jpg); " +
        "height: 300px; " +
        "width: 400px;" +
        "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
        "\">");

        html += "<section>";
        html += "<h3 style = vertical-align:top>Weather</h3>";
        html+= "<h4> Stats</h4><br />";
        html += String.format("<label>Average temp: %s</label><br />", temperature);
        html += String.format("<label>Min. Temp: %s</label><br />", lowTemp);
        html += String.format("<label>Max Temp: %s</label>", hiTemp);
        html += "</section>";
        html += "</div>";


        return html;
    }

    @Override
    public String name(){
        return "Statistics Display";
    }

    @Override
    public String id(){
        return "statistics";
    }

    @Override
    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void subscribe(){
        weatherData.registerObserver(this);
    }

    public void unsubscribe(){
        weatherData.removeObserver(this);
    }
}
