package se.kth.id2212.ex4.bank.view;

import se.kth.id2212.ex4.bank.controller.CashierFacade;
import se.kth.id2212.ex4.bank.model.ConversionDTO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("currManager")
@ConversationScoped
public class CurrManager implements Serializable {
    
    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CashierFacade cashierFacade;
    private String currentConv1;
    private String currentConv2;
    private Float valueConverted;
    private Exception transactionFailure;
    private Float valueToConvert;
    @Inject
    private Conversation conversation;
    private Float currentConvRateValue;
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public void setValueToConvert(Float num){
        this.valueToConvert = num;
    }
    
    public Float getValueToConvert(){
        return this.valueToConvert; 
    }
    
    public void setCurrentConv1(String conv1){
        this.currentConv1 = conv1;
    }
    
    public void setCurrentConv2(String conv2){
        this.currentConv2 = conv2;
    }
    
    public String getCurrentConv1(){
        return currentConv1;
    }
    
    public String getCurrentConv2(){
        return currentConv2;
    }
    
    public float getValueConverted(){
        if(valueConverted == null) return new Float(0.0);
        return valueConverted;
    }
    
    public String displayCurr(){
        
        try {
            //startConversation();
            transactionFailure = null;
            currentConvRateValue = cashierFacade.findConversionRate(currentConv1, currentConv2);
            //System.out.println(currentConvRate.getConvRate());
            valueConverted = valueToConvert*currentConvRateValue;
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    public void updateCurr(){
        startConversation();
        cashierFacade.update();
    }
    
    
    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return (transactionFailure == null);
        //return true;
    }
    
    
    
    
    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }
    
    /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.
     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }
    
    
}