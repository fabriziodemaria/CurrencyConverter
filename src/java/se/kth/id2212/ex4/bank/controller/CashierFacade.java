package se.kth.id2212.ex4.bank.controller;

import java.io.IOException;
import java.util.List;
import se.kth.id2212.ex4.bank.model.ConversionRates;
import se.kth.id2212.ex4.bank.model.ConversionDTO;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 * A controller. All calls to the model that are executed because of an action taken by
 * the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CashierFacade {
    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    public Float findConversionRate(String curr1, String curr2) {
        List<ConversionRates> convObj1 = null;
        convObj1 = em.createNamedQuery("findConvRate", ConversionRates.class).
                    setParameter("curr1", curr1).setParameter("curr2", "Dollar").getResultList();
        
        if (convObj1.size()==0) {
            throw new EntityNotFoundException("No conv rate found with name " + curr1);
        }
        
        ConversionDTO found1 = convObj1.get(0);
        if (found1 == null) {
            throw new EntityNotFoundException("No conv rate found");
        }
        
        List<ConversionRates> convObj2 = em.createNamedQuery("findConvRate", ConversionRates.class).
                    setParameter("curr1", "Dollar").setParameter("curr2", curr2).getResultList();
        if (convObj2.size() == 0) {
            throw new EntityNotFoundException("No conv rate found with name " + curr2);
        }
        ConversionDTO found2 = convObj2.get(0);
        if (found2 == null) {
            throw new EntityNotFoundException("No conv rate found");
        }
//        List<ConversionDTO> tmp = new LinkedList<>();
//        tmp.add(found1);
//        tmp.add(found2);
        Float tmp = found1.getConvRate()*found2.getConvRate();
        return tmp;
        
    }
    
        public void update()  {
        singleAdd((float) 2.3,"Euro","Dollar");
        singleAdd((float) 2.1,"Pound","Dollar");
        singleAdd((float) 2.7,"Sek","Dollar"); 
        singleAdd((float) 1,"Dollar","Dollar");
    }
    
    protected void singleAdd(float value, String s1, String s2){
        ConversionRates newAcct = new ConversionRates(value, s1, s2);
        em.persist(newAcct);
        ConversionRates newAcct2 = new ConversionRates((float)1/value, s2, s1);
        em.persist(newAcct2);
    }


}
