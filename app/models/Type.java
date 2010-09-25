package models;
import java.util.*;
import javax.persistence.*;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;
 
import play.db.jpa.*;
 
@Entity
public class Type extends Model {
 
    public String type;
    
    public Type(String type) {
        this.type = type;
    }
 
    public String toString() {
    	return this.type;
    }
}
