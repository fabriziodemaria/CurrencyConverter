package se.kth.id2212.ex4.bank.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@NamedQueries({
        @NamedQuery(
                name = "findConvRate",
                query = "SELECT a FROM ConversionRates a WHERE a.curr1 LIKE :curr1 AND a.curr2 LIKE :curr2"
                //lockMode = LockModeType.OPTIMISTIC
        )
})


/**
 * A persistent representation of an account.
 */
@Entity
public class ConversionRates implements ConversionDTO, Serializable {

    private static final long serialVersionUID = 16247164401L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "convRate", nullable = false)
    private float convRate;
    @Column(name = "curr1", nullable = false)
    private String curr1;
    @Column(name = "curr2", nullable = false)
    private String curr2;
    
        
//    @Version
//    @Column(name = "OPTLOCK")
//    private int versionNum;

    /**
     * Creates a new instance of Account
     */
    public ConversionRates() {
    }

    public ConversionRates(float convRate, String curr1, String curr2) {
        this.convRate = convRate;
        this.curr1 = curr1;
        this.curr2 = curr2;
    }

    /**
     * Get the value of lastNAme
     *
     * @return the value of lastNAme
     */
        public float getConvRate() {
        return convRate;
    }

    

        public boolean equals(Object object) {
        if (!(object instanceof ConversionRates)) {
            return false;
        }
        ConversionRates other = (ConversionRates) object;
        return this.id == other.id;
    }


    public String toString() {
        return "bank.model.ConversionRate[id=" + id + "]";
    }

    public int getId() {
        return id;}

   
}
