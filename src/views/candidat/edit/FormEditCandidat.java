/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.candidat.edit;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import projectTools.PlaceHolderDecorator;
import projectTools.RequiredFieldValidator;

/**
 *
 * @author taleb
 */
public class FormEditCandidat extends javax.swing.JDialog {

    private static final long serialVersionUID = -3725742557117266993L;

    /**
     * Creates new form FormCandidat
     */
    final private FormEditCondidatController controller;
    final private Long editCandidat;
    public FormEditCandidat(FormEditCondidatController condidatController,Long editCandidat) {
        initComponents();
        initFrame(condidatController);
        controller = condidatController;
        this.editCandidat = editCandidat;
    }

    @SuppressWarnings("empty-statement")
    public void setEditData(String nomMere, String nomPere, String adress, String lieuNaissance, String nationalite, String dateDepot, String dateNaissanc, String telephone, String nom, String prenom){
        jtfNom.setForeground(Color.black);
        jtfNom.setText(nom);
        
        jtfPrenom.setText(prenom);
        jtfPrenom.setForeground(Color.black);
        
        jtfNomMereValue.setText(nomMere);
        jtfNomMereValue.setForeground(Color.black);
        
        jtfNomPereValue.setText(nomPere);
        jtfNomPereValue.setForeground(Color.black);
        
        jtfNomPereValue.setForeground(Color.black);
        jtfNomPereValue.setForeground(Color.black);
        
        jtfAdresse.setText(adress);
        jtfAdresse.setForeground(Color.black);
        
        jtfLieuNaissance.setText(lieuNaissance);
        jtfLieuNaissance.setForeground(Color.black);
        
        jtfNationalite.setText(nationalite);
        jtfNationalite.setForeground(Color.black);
        
        jFTFDateDepot.setText(dateDepot);
        jFTFDateDepot.setForeground(Color.black);
        
        jFTFDateNaissance.setText(dateNaissanc);
        jFTFDateNaissance.setForeground(Color.black);
        
        jFTFNumTel.setText(telephone);
        jFTFNumTel.setForeground(Color.black);
        
    }
    
    private void initFrame(FormEditCondidatController condidatController) {
        final Color writingColor = new Color(45, 45, 45);
        final Color holdingColor = new Color(127, 127, 127);

       
     
        Component[] nationalitieMenuItems = jPPNationalité.getComponents();
        for (Component nationalitieMenuItem : nationalitieMenuItems) {
            if (nationalitieMenuItem instanceof JMenuItem) {
                ((JMenuItem) nationalitieMenuItem).setOpaque(true);
                ((JMenuItem) nationalitieMenuItem).setBackground(new Color(204,204,204));
                ((JMenuItem) nationalitieMenuItem).addActionListener((al) -> {
                    jtfNationalite.setText(((JMenuItem) nationalitieMenuItem).getText());
                    jtfNationalite.setForeground(writingColor);
                });
               
                    
                    
               
            }
        }

        setModal(true);


        fieldValidator = new RequiredFieldValidator();

        PlaceHolderDecorator.decorate(jtfNom, NOM_PLACE_HOLDER, holdingColor, writingColor);
        fieldValidator.add(jtfNom, NOM_PLACE_HOLDER);

        PlaceHolderDecorator.decorate(jtfNationalite, NATIONALITE, holdingColor, writingColor);
        fieldValidator.add(jtfNationalite, NATIONALITE);

        PlaceHolderDecorator.decorate(jtfAdresse, ADRESSE_RESIDENCE, holdingColor, writingColor);
        fieldValidator.add(jtfAdresse, ADRESSE_RESIDENCE);

        PlaceHolderDecorator.decorate(jtfNomMereValue, jtfNomMereValue.getText());
        fieldValidator.add(jtfNomMereValue, jtfNomMereValue.getText());
        
        PlaceHolderDecorator.decorate(jtfNomPereValue, jtfNomPereValue.getText());
        fieldValidator.add(jtfNomPereValue, jtfNomPereValue.getText());
        
        PlaceHolderDecorator.decorate(jtfPrenom, PRENOM_PLACE_HOLDER, holdingColor, writingColor);
        fieldValidator.add(jtfPrenom, PRENOM_PLACE_HOLDER);

        PlaceHolderDecorator.decorate(jFTFDateNaissance, DATE_DE_NAISSANCE_PLACE_HOLDER, holdingColor, writingColor, PlaceHolderDecorator.DATE_FIELD_P);
        fieldValidator.add(jFTFDateNaissance, DATE_DE_NAISSANCE_PLACE_HOLDER);

        PlaceHolderDecorator.decorate(jtfLieuNaissance, LIEU_DE_NAISSANCE_PLACE_HOLDER, holdingColor, writingColor);
        fieldValidator.add(jtfLieuNaissance, LIEU_DE_NAISSANCE_PLACE_HOLDER);

        PlaceHolderDecorator.decorate(jFTFNumTel, NUMERO_DE_PHONE_PLACE_HOLDER, holdingColor, writingColor, PlaceHolderDecorator.PHONE_FIELD_P);
        fieldValidator.add(jFTFNumTel, NUMERO_DE_PHONE_PLACE_HOLDER);

        PlaceHolderDecorator.decorate(jFTFDateDepot, DATE_DEPOT_PLACE_HOLDER, holdingColor, writingColor, PlaceHolderDecorator.DATE_FIELD_P);
        fieldValidator.add(jFTFDateDepot, DATE_DEPOT_PLACE_HOLDER);

        jlbShowNationalitiesPopup.add(jPPNationalité);
        jlbShowNationalitiesPopup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                jPPNationalité.show(jlbShowNationalitiesPopup, jlbShowNationalitiesPopup.getHeight(), jlbShowNationalitiesPopup.getWidth());
            }

        });

        
    }
    private static final String ADRESSE_RESIDENCE = "Adresse de résidence";
    private static final String NATIONALITE = "Nationalité";
    private static final String PRENOM_PLACE_HOLDER = "Prénom";
    private static final String NOM_PLACE_HOLDER = "Nom";
    private static final String LIEU_DE_NAISSANCE_PLACE_HOLDER = "Lieu de naissance";
    private static final String NUMERO_DE_PHONE_PLACE_HOLDER = "Numero de Téléphone";
    private static final String DATE_DE_NAISSANCE_PLACE_HOLDER = "Date de naissance";
    private static final String DATE_DEPOT_PLACE_HOLDER = "Date dépot du dossier";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbGroupPermisDemander = new javax.swing.ButtonGroup();
        jPPNationalité = new javax.swing.JPopupMenu();
        jMIAlgerie = new javax.swing.JMenuItem();
        jMITunisienne = new javax.swing.JMenuItem();
        jMIMarroc = new javax.swing.JMenuItem();
        jMIFrance = new javax.swing.JMenuItem();
        jMIEspagne = new javax.swing.JMenuItem();
        jMIEngleterre = new javax.swing.JMenuItem();
        jMIItalienne = new javax.swing.JMenuItem();
        jMICanadienne = new javax.swing.JMenuItem();
        jMI_USA = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jtfNom = new javax.swing.JTextField();
        jtfPrenom = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jFTFDateNaissance = new javax.swing.JFormattedTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jtfLieuNaissance = new javax.swing.JTextField();
        jChA = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jChB = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jChD = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jChC = new javax.swing.JCheckBox();
        jChE = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jFTFDateDepot = new javax.swing.JFormattedTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jFTFNumTel = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jtfAdresse = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jtfNationalite = new javax.swing.JTextField();
        jlbShowNationalitiesPopup = new javax.swing.JLabel();
        jtfNomPereValue = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jtfNomMereValue = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();

        jPPNationalité.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPPNationalité.setOpaque(false);

        jMIAlgerie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Algeria_16px.png"))); // NOI18N
        jMIAlgerie.setText("Algerienne");
        jPPNationalité.add(jMIAlgerie);

        jMITunisienne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Tunisia_16px_1.png"))); // NOI18N
        jMITunisienne.setText("Tunisienne");
        jPPNationalité.add(jMITunisienne);

        jMIMarroc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Morocco_16px_1.png"))); // NOI18N
        jMIMarroc.setText("Marrocaine");
        jPPNationalité.add(jMIMarroc);

        jMIFrance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_France_16px.png"))); // NOI18N
        jMIFrance.setText("Française");
        jPPNationalité.add(jMIFrance);

        jMIEspagne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Spain_2_16px_1.png"))); // NOI18N
        jMIEspagne.setText("Espagnol");
        jPPNationalité.add(jMIEspagne);

        jMIEngleterre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_England_16px_1.png"))); // NOI18N
        jMIEngleterre.setText("Englaise");
        jPPNationalité.add(jMIEngleterre);

        jMIItalienne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Italy_16px_3.png"))); // NOI18N
        jMIItalienne.setText("Italienne");
        jPPNationalité.add(jMIItalienne);

        jMICanadienne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Canada_16px_1.png"))); // NOI18N
        jMICanadienne.setText("Canadienne");
        jPPNationalité.add(jMICanadienne);

        jMI_USA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_USA_16px_1.png"))); // NOI18N
        jMI_USA.setText("Americaine");
        jPPNationalité.add(jMI_USA);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(45, 45, 45));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(235, 188, 0));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/icons8_Home_32px_1.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/icons8_Enter_32px.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/principale/icons8_Student_Male_32px.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/principale/icons8_Graduation_Cap_32px.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/5.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Name_32px_1.png"))); // NOI18N
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 45, 45));
        jLabel1.setText("البطاقة الفردية : المعالجة");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Right_Squared_32px.png"))); // NOI18N
        jLabel13.setText("Valider");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, -1, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Prev_32px.png"))); // NOI18N
        jLabel14.setText("Annuler");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, -1, -1));

        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 122, 174, -1));

        jtfNom.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfNom.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfNom.setText("اللقب");
        jtfNom.setBorder(null);
        jtfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomActionPerformed(evt);
            }
        });
        jPanel6.add(jtfNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 90, 190, -1));

        jtfPrenom.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfPrenom.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfPrenom.setText("الاسم");
        jtfPrenom.setBorder(null);
        jtfPrenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPrenomActionPerformed(evt);
            }
        });
        jPanel6.add(jtfPrenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 170, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Date_From_32px.png"))); // NOI18N
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 222, 176, -1));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Marker_32px.png"))); // NOI18N
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, 28));

        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 222, 221, -1));

        jFTFDateNaissance.setBorder(null);
        try {
            jFTFDateNaissance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFDateNaissance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFTFDateNaissance.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jPanel6.add(jFTFDateNaissance, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 189, 150, -1));

        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 122, 220, -1));

        jtfLieuNaissance.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfLieuNaissance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfLieuNaissance.setText("مكان الازدياد");
        jtfLieuNaissance.setBorder(null);
        jPanel6.add(jtfLieuNaissance, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 189, 28));

        jChA.setBackground(new java.awt.Color(255, 255, 255));
        jbGroupPermisDemander.add(jChA);
        jChA.setActionCommand("A");
        jChA.setAlignmentY(0.0F);
        jChA.setBorder(null);
        jChA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jChA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Unchecked_Checkbox_32px.png"))); // NOI18N
        jChA.setIconTextGap(0);
        jChA.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChA.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jChA, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 317, 42, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel2.setText("A");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 337, -1, -1));

        jChB.setBackground(new java.awt.Color(255, 255, 255));
        jbGroupPermisDemander.add(jChB);
        jChB.setActionCommand("B");
        jChB.setAlignmentY(0.0F);
        jChB.setBorder(null);
        jChB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jChB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Unchecked_Checkbox_32px.png"))); // NOI18N
        jChB.setIconTextGap(0);
        jChB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChB.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jChB, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 317, 42, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel3.setText("B");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 337, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel4.setText("D");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 337, -1, -1));

        jChD.setBackground(new java.awt.Color(255, 255, 255));
        jbGroupPermisDemander.add(jChD);
        jChD.setActionCommand("D");
        jChD.setAlignmentY(0.0F);
        jChD.setBorder(null);
        jChD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jChD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Unchecked_Checkbox_32px.png"))); // NOI18N
        jChD.setIconTextGap(0);
        jChD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChD.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jChD, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 317, 42, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel7.setText("C");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 337, -1, -1));

        jChC.setBackground(new java.awt.Color(255, 255, 255));
        jbGroupPermisDemander.add(jChC);
        jChC.setActionCommand("C");
        jChC.setAlignmentY(0.0F);
        jChC.setBorder(null);
        jChC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jChC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Unchecked_Checkbox_32px.png"))); // NOI18N
        jChC.setIconTextGap(0);
        jChC.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChC.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jChC, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 317, 42, 40));

        jChE.setBackground(new java.awt.Color(255, 255, 255));
        jbGroupPermisDemander.add(jChE);
        jChE.setActionCommand("E");
        jChE.setAlignmentY(0.0F);
        jChE.setBorder(null);
        jChE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChE.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jChE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Unchecked_Checkbox_32px.png"))); // NOI18N
        jChE.setIconTextGap(0);
        jChE.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChE.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Checked_Checkbox_32px_1.png"))); // NOI18N
        jChE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jChE, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 317, 42, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel8.setText("E");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 337, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Driver_License_32px.png"))); // NOI18N
        jLabel9.setText("Permis demandé");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 286, -1, 31));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Date_From_32px.png"))); // NOI18N
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 363, -1, -1));

        jFTFDateDepot.setBorder(null);
        try {
            jFTFDateDepot.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFDateDepot.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jPanel6.add(jFTFDateDepot, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 371, 214, 24));

        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 401, 246, -1));

        jFTFNumTel.setBorder(null);
        try {
            jFTFNumTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("0# ## ## ## ##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFNumTel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFTFNumTel.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jFTFNumTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTFNumTelActionPerformed(evt);
            }
        });
        jPanel6.add(jFTFNumTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 144, 30));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Two_Smartphones_32px.png"))); // NOI18N
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 273, 176, -1));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Marker_32px.png"))); // NOI18N
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 414, -1, -1));

        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 452, 419, -1));

        jtfAdresse.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfAdresse.setText("Adresse de résidence");
        jtfAdresse.setBorder(null);
        jPanel6.add(jtfAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 414, 389, 32));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Globe_32px.png"))); // NOI18N
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, -1, -1));

        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 273, 221, -1));

        jtfNationalite.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfNationalite.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfNationalite.setText("الجنسية");
        jtfNationalite.setBorder(null);
        jPanel6.add(jtfNationalite, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 164, -1));

        jlbShowNationalitiesPopup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbShowNationalitiesPopup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Sort_Down_24px.png"))); // NOI18N
        jlbShowNationalitiesPopup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbShowNationalitiesPopupMouseClicked(evt);
            }
        });
        jPanel6.add(jlbShowNationalitiesPopup, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 18, 20));

        jtfNomPereValue.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfNomPereValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfNomPereValue.setText("الأب");
        jtfNomPereValue.setBorder(null);
        jPanel6.add(jtfNomPereValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 140, 190, -1));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Family_32px_2.png"))); // NOI18N
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        jtfNomMereValue.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfNomMereValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfNomMereValue.setText("الأم");
        jtfNomMereValue.setBorder(null);
        jPanel6.add(jtfNomMereValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 160, -1));

        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 173, 174, -1));

        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));
        jPanel6.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 173, 218, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked
    private RequiredFieldValidator fieldValidator;

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        boolean validate = fieldValidator.validate();
        if (validate) {
            String nom = jtfNom.getText();
            String nomPere = jtfNomPereValue.getText();
            String nomMere = jtfNomMereValue.getText();
            String prenom = jtfPrenom.getText();
            String numeroTelephone = jFTFNumTel.getText().replaceAll("\\s", "");
            String lieuNaissance = jtfLieuNaissance.getText();
            String adresse = jtfAdresse.getText();
            String dateNaissance = jFTFDateNaissance.getText();
            String dateIns = jFTFDateDepot.getText();
            String nationalite = jtfNationalite.getText();
            String permisDemander = jbGroupPermisDemander.getSelection().getActionCommand();
            
            
            controller.edit(editCandidat,nom,prenom,nomPere,nomMere,numeroTelephone,lieuNaissance,adresse,dateIns,dateNaissance,nationalite,permisDemander);
            dispose();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jlbShowNationalitiesPopupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbShowNationalitiesPopupMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbShowNationalitiesPopupMouseClicked

    private void jFTFNumTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTFNumTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFTFNumTelActionPerformed

    private void jtfPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPrenomActionPerformed

    private void jtfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomActionPerformed

    private void setNationalite(ActionEvent evt) {
        // TODO add your handling code here:
        jtfNationalite.setText(((JMenuItem) evt.getSource()).getText());
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jChA;
    private javax.swing.JCheckBox jChB;
    private javax.swing.JCheckBox jChC;
    private javax.swing.JCheckBox jChD;
    private javax.swing.JCheckBox jChE;
    private javax.swing.JFormattedTextField jFTFDateDepot;
    private javax.swing.JFormattedTextField jFTFDateNaissance;
    private javax.swing.JFormattedTextField jFTFNumTel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMIAlgerie;
    private javax.swing.JMenuItem jMICanadienne;
    private javax.swing.JMenuItem jMIEngleterre;
    private javax.swing.JMenuItem jMIEspagne;
    private javax.swing.JMenuItem jMIFrance;
    private javax.swing.JMenuItem jMIItalienne;
    private javax.swing.JMenuItem jMIMarroc;
    private javax.swing.JMenuItem jMITunisienne;
    private javax.swing.JMenuItem jMI_USA;
    private javax.swing.JPopupMenu jPPNationalité;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.ButtonGroup jbGroupPermisDemander;
    private javax.swing.JLabel jlbShowNationalitiesPopup;
    private javax.swing.JTextField jtfAdresse;
    private javax.swing.JTextField jtfLieuNaissance;
    private javax.swing.JTextField jtfNationalite;
    private javax.swing.JTextField jtfNom;
    private javax.swing.JTextField jtfNomMereValue;
    private javax.swing.JTextField jtfNomPereValue;
    private javax.swing.JTextField jtfPrenom;
    // End of variables declaration//GEN-END:variables

    public void setSelectedPermis(int categoriePermis) {
      switch(categoriePermis){
          case 0 : jChA.setSelected(true);
          break;
          case 1 : jChB.setSelected(true);
          break;
          case 2 : jChC.setSelected(true);
          break;
          case 3 : jChD.setSelected(true);
          break;
          case 4 : jChE.setSelected(true);
          break;
      }
    }

}
