package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = "Theater.findAll", query = "SELECT t FROM Theater t"),
    @NamedQuery(name = "Theater.findByTheaterID", query = "SELECT t FROM Theater t WHERE t.theaterID = :theaterID"),
    @NamedQuery(name = "Theater.findByTheaterName", query = "SELECT t FROM Theater t WHERE t.theaterName = :theaterName"),
    @NamedQuery(name = "Theater.findByTheaterAddress", query = "SELECT t FROM Theater t WHERE t.theaterAddress = :theaterAddress"),
    @NamedQuery(name = "Theater.findByTheaterZip", query = "SELECT t FROM Theater t WHERE t.theaterZip = :theaterZip")})
public class Theater implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long theaterID;

    @NotNull
    @Size(min=1,max=50)
    private String theaterName;
    @NotNull
    @Size(min=1,max=80)
    private String theaterAddress;
    @NotNull
    @Size(min=5,max=5)
    private String theaterZip;
    
    //getters and setters 
    public String getTheaterZip() {
        return theaterZip;
    }

    public void setTheaterZip(String theaterZip) {
        this.theaterZip = theaterZip;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterAddress() {
        return theaterAddress;
    }

    public void setTheaterAddress(String theaterAddress) {
        this.theaterAddress = theaterAddress;
    }

    public Long getTheaterId() {
        return theaterID;
    }

    public void setTheaterId(Long id) {
        this.theaterID = id;
    }
    
    
    //generated code

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (theaterID != null ? theaterID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the theaterID fields are not set
        if (!(object instanceof Theater)) {
            return false;
        }
        Theater other = (Theater) object;
        if ((this.theaterID == null && other.theaterID != null) || (this.theaterID != null && !this.theaterID.equals(other.theaterID))||(this.theaterZip == null && other.theaterZip != null) || (this.theaterZip != null && !this.theaterZip.equals(other.theaterZip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Theater[ id=" + theaterID + " ]";
    }
    
}
