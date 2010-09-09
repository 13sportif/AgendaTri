package models;
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Label extends Model {
 
    public String label;
    
    public Label(String label) {
        this.label = label;
    }
 
}
