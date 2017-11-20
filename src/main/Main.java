/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.dao.PrintableJPAController;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpa.provider.EMF_Provider;
import views.login.Login;
import views.login.LoginController;

/**
 *
 * @author taleb
 */
public class Main {

    public static void main(String[] args) {

        //<editor-fold defaultstate="collapsed" desc="default windows look and feel">
//        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//            System.out.println(info.getName());
//            if ("Metal".equals(info.getName())) {
//                try {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                   
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InstantiationException ex) {
//                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (UnsupportedLookAndFeelException ex) {
//                    Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//</editor-fold>
        try {

            final Path createFile = Paths.get("logs.txt");
            if(Files.notExists(Paths.get("logs.txt"))) Files.createFile(Paths.get("logs.txt"));

            Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
                System.out.println("*****Yeah, Caught the Exception*****");
                StackTraceElement[] stackTrace = e.getStackTrace(); // you can use e.printStackTrace ( printstream ps )
                for (StackTraceElement stackTraceElement : stackTrace) {
                    StringBuilder error = new StringBuilder();
                    error.append("Class of error : ")
                            .append(stackTraceElement.getClassName())
                            .append("\n\n\n")
                            .append(stackTraceElement.toString())
                            .append("\n");
                    
                    try (OutputStream out = new BufferedOutputStream(
                            Files.newOutputStream(createFile, CREATE, APPEND))) {
                        final byte[] data = error.toString().getBytes();
                        out.write(data, 0, data.length);
                    } catch (IOException x) {
                        System.err.println(x);
                    }
                    
                }
            });

                //-System.out.println(2 / 0);  // Throw the Exception 
            

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            PrintableJPAController printableJPAController = new PrintableJPAController(EMF_Provider.getCurrentSessionEMF());
            printableJPAController.count();

            LoginController loginController = new LoginController();

            new Login(loginController).setVisible(true);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
