package edu.iu.habahram.weathermonitoring.controllers;

import edu.iu.habahram.weathermonitoring.model.CurrentConditionDisplay;
import edu.iu.habahram.weathermonitoring.model.ForecastDisplay;
import edu.iu.habahram.weathermonitoring.model.Observer;
import edu.iu.habahram.weathermonitoring.model.StatisticsDisplay;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/displays")
public class DisplayController {
    private CurrentConditionDisplay currentConditionDisplay;
    private StatisticsDisplay currentstatisticsDisplay;
    private ForecastDisplay currentForecastDisplay;
    public DisplayController(CurrentConditionDisplay currentConditionDisplay, StatisticsDisplay currentStatisticsDisplay, ForecastDisplay currentForecastDisplay
                             ) {
        this.currentConditionDisplay = currentConditionDisplay;
        this.currentstatisticsDisplay = currentStatisticsDisplay;
        this.currentForecastDisplay = currentForecastDisplay;
                            
    }

    @GetMapping
    public ResponseEntity index() {
        String html =
                String.format("<h1>Available screens:</h1>");
        html += "<ul>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", currentConditionDisplay.id(), currentConditionDisplay.name());
        html += "<br/>";
        html += String.format("<a href=/displays/%s>%s</a>", currentstatisticsDisplay.id(), currentstatisticsDisplay.name());
        html += "<br/>";
        html += String.format("<a href=/displays/%s>%s</a>", currentForecastDisplay.id(), currentForecastDisplay.name());
        html += "</li>";

        html += "</ul>";
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(html);
    }


    @GetMapping("/{id}")
    public ResponseEntity display(@PathVariable String id) {
        String html = "";
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            html = currentConditionDisplay.display();
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(currentstatisticsDisplay.id())){
            html = currentstatisticsDisplay.display();
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(currentForecastDisplay.id())){
            html = currentForecastDisplay.display();
            status = HttpStatus.FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }

    @GetMapping("/{id}/subscribe")
    public ResponseEntity subscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(currentstatisticsDisplay.id())){
            currentstatisticsDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(currentForecastDisplay.id())){
            currentForecastDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        }
        
        else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }

    @GetMapping("/{id}/unsubscribe")
    public ResponseEntity unsubscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(currentstatisticsDisplay.id())){
            currentstatisticsDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(currentForecastDisplay.id())){
            currentForecastDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        }
        else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }
}
