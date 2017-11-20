/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.login;

import javax.swing.JOptionPane;
import views.principale.Acceuil;
import views.principale.AcceuilController;

/**
 *
 * @author taleb
 */
public class LoginController {
    
    public void doLogin(String username,String password){
        if(username.equals("admin") && password.equals("admin")){
            AcceuilController acceuilController = new AcceuilController();
            Acceuil acceuil = new Acceuil(acceuilController);
            acceuil.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Utilisateur ou mot de passe incorrect");
        }
    }
    
}
