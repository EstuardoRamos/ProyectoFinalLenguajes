/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sintactico;

/**
 * 
 * @author Estuardo Ramos
 */
public enum noTerminal {
    
    
        E,
        P,
        EP,
        S,
        A,
        X,
        XP,
        L,
        Q,
        N,
        T;
    
    

    public static noTerminal getP() {    
        return P;
    }

    public static noTerminal getEP() {
        return EP;
    }

    public static noTerminal getS() {
        return S;
    }

    public static noTerminal getA() {
        return A;
    }

    public static noTerminal getX() {
        return X;
    }

    public static noTerminal getXP() {
        return XP;
    }

    public static noTerminal getL() {
        return L;
    }

    public static noTerminal getQ() {
        return Q;
    }

    public static noTerminal getN() {
        return N;
    }

    public static noTerminal getT() {
        return T;
    }
}
