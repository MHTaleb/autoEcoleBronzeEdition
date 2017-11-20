/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author taleb
 */
public enum CategoriePermis {
    A,B,C,D,E;
    
    
    public int getOrdinal(){
        switch(this){
            case A : return 0;
            case B : return 1;
            case C : return 2;
            case D : return 3;
            case E : return 4;
            default: return 1;
        }
                
    }
}
