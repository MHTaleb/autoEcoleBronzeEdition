/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printable;

import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;

/**
 *
 * @author taleb
 */
public class PrintJob {

    public PrintJob(String documentPath , Connection connection ) {
        this.documentPath = documentPath;
        this.connection = connection;
    }

    
    
    
    public void showReport(Map<String, Object> params) throws JRException, HeadlessException {
        try {
            
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(documentPath), params,connection);
        
        JRViewer jrViewer = new JRViewer(jasperPrint);
        jrViewer.setSaveContributors(new JRSaveContributor[] { new JRPdfSaveContributor(Locale.getDefault(), null) });
        
        JDialog jDialog = new JDialog();
        jDialog.setLocationRelativeTo(null);
        jDialog.getContentPane().add(jrViewer);
        jDialog.pack();
        jDialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        
        jDialog.setModal(true);
        jDialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
        }
    }
    private final String documentPath;
    private final Connection connection;
    
}
