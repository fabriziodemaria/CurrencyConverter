package se.kth.id2212.ex4.bank.model;

/**
 * Thrown when withdrawal would result in a negative balance.
 */
public class OverdraftException extends Exception {
    private static final long serialVersionUID = 16247164402L;

    public OverdraftException(String msg) {
        super(msg);
    }

}
