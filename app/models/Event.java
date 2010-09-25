package models;
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.db.jpa.*;
import play.templates.JavaExtensions;
 
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"slug"}))
public class Event extends Model {
	
	@Required
    public String title;
    @Lob
    @Required
    @MaxSize(10000)
    public String description;
	@Required
    public Date dateEvt;
    @Email
    public String email;
    public String city;
    public String region;
    public String dept;
    public Double lat;
    public Double lng;
    @URL
    public String webSite;
    public String contact;
    @Email
    public String submitterEmail;
    public String submitterPwd;
	public String slug;
    public Boolean published;
    @OneToMany(mappedBy="event", cascade=CascadeType.ALL)
    public List<Competition> competitions;

    
    public Event(String title, String description, Date dateEvt) {
        this.competitions = new ArrayList<Competition>();
        this.title = title;
        this.description = description;
        this.dateEvt = dateEvt;
        this.published = Boolean.TRUE;
    }
 
    public Event addCompetition(Type type, Format format) {
    	Competition newCompetition = new Competition(this, type, format).save();
        this.competitions.add(newCompetition);
        this.save();
        return this;
    }
    
    @PreUpdate
    @PrePersist
    void putSlug() {
        this.slug = JavaExtensions.slugify(this.title) + "-"
        	+ JavaExtensions.format(dateEvt, "dd-MMMM-yyyy", "fr");
    }

    public String toString() {
    	return this.title;
    }    
}
