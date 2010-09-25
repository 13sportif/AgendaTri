package models;
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Competition extends Model {
 
	@ManyToOne
	public Type type;
	@ManyToOne
	public Format format;
	@ManyToOne
	public Label label;
    
    @ManyToOne
    public Event event;
    
    public Competition(Event event, Type type, Format format) {
        this.event = event;
        this.type = type;
        this.format = format;
    }
 
    public String toString() {
    	return this.event + " - " + this.type + " " + this.format;
    }    
}
