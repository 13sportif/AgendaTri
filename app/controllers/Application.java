package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import play.mvc.*;
import play.templates.*;

import models.*;

public class Application extends Controller {

    public static void index(String year, String month) {
        Date reqDate = new Date();
        if (month == null) {
            month = JavaExtensions.format(reqDate, "MM");
        }    
        if (year == null) {
            year = JavaExtensions.format(reqDate, "yyyy");
        }    
        String searchDate = year + "-" + month + "-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			reqDate = sdf.parse(searchDate);
		} catch (ParseException e) { 
		}
		
        List<Event> events = Event.find("dateEvt > ? order by dateEvt", reqDate).fetch(10);
//        List<Event> events = Event.find("order by dateEvt desc").fetch(10);
        render(events, year, month, reqDate);
    }
    
    public static void show(String slug) {
    	Event event = Event.find("bySlug", slug).first();
    	notFoundIfNull(event);
        render(event);
    }
    
}
