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
        Date reqDateEnd = new Date();
        if (month == null) {
            month = JavaExtensions.format(reqDate, "MM");
        }    
        if (year == null) {
            year = JavaExtensions.format(reqDate, "yyyy");
        }    
        String searchDate = year + "-" + month;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			reqDate = sdf.parse(searchDate + "-01");
			reqDateEnd = sdf.parse(searchDate + "-31");
		} catch (ParseException e) { 
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(reqDate);
		calendar.add(Calendar.MONTH, 1);
		Date nextMonth = calendar.getTime();
		calendar.add(Calendar.MONTH, -2);
		Date prevMonth = calendar.getTime();
		
        List<Event> events = Event.find("dateEvt >= ? and dateEvt <= ?  order by dateEvt", reqDate, reqDateEnd).fetch(100);
//        List<Event> events = Event.find("order by dateEvt desc").fetch(10);
        render(events, prevMonth, nextMonth, reqDate);
    }
    
    public static void show(String slug) {
    	Event event = Event.find("bySlug", slug).first();
    	notFoundIfNull(event);
        render(event);
    }
    
}
