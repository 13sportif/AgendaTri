package models;
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Format extends Model {
 
    public String format;
    
    public Format(String format) {
        this.format = format;
    }
 
    public String toString() {
    	return this.format;
    }
    
}
