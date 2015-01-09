/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package se.kth.id2212.ex4.bank.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabriziodemaria
 */
public class Administrator implements Runnable {
    CashierFacade myDBLink;
    
    public Administrator(CashierFacade aThis) throws IOException {
        myDBLink = aThis;
    }
    

    private Administrator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
        
        while (true)
        {
            System.out.print(">");
            String userInput = null;
            try {
                userInput = consoleIn.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(userInput.equals("ADD")){
                System.out.print("Insert curr name>");
                try {
                    userInput = consoleIn.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                }
                String name = userInput;
                System.out.print("Insert curr wrt Dollar>");
                try {
                    userInput = consoleIn.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                }
                Float currVal = new Float(userInput);
                
                myDBLink.singleAdd(currVal, name, "Dollar");
                
            } else if (userInput.equals("REMOVE")){
                //serInput = consoleIn.readLine();
            }
        }}
}
