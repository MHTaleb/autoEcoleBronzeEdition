/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.examen.editExam;

import entities.Examen;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import javafx.util.converter.DateStringConverter;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import projectTools.ModalInputTextDialog;
import projectTools.PlaceHolderDecorator;
import projectTools.RequiredFieldValidator;
import views.examen.addExam.ExamAddFormController;

/**
 *
 * @author taleb
 */
public class ExamenEditForm extends javax.swing.JDialog implements ExamEditCallback {

     @Override
    public void setCount(int count) {
        jlbCount.setText("" + count);
    }

    @Override
    public void updateList(ExamAddFormController.RequestedListModel model) {
        controller.showAllCandidat(jList1);
        switch (model) {
            case CIRCUIT: {
                controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CIRCUIT);
                break;
            }

            case CODE: {
                controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CODE);
                break;
            }

            case CRENO: {
                controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CRENO);
                break;
            }
        }
    }

    @Override
    public void refreshExaminateurs() {

        setupExaminateurs();
        System.out.println("refresh done");
    }
  private ModalInputTextDialog mitd;

    private void setupExaminateurs() {
        Component[] components = jPMExaminateurList.getComponents();
        for (Component component : components) {
            if (component instanceof JMenuItem) {
                JMenuItem jMenuItem = (JMenuItem) component;
                jPMExaminateurList.remove(jMenuItem);
            }
        }
        JMenuItem jmi = new JMenuItem("إضافة");
        jmi.setHorizontalAlignment(JMenuItem.RIGHT);
        jmi.addActionListener(al -> {
            mitd = new ModalInputTextDialog(null, true);
            mitd.setLabelArabic("اللقب - الاسم :");
            mitd.setPromptText("اللقب - الاسم", JTextField.RIGHT);
            mitd.setVisible(true);
            int userAnswer = mitd.getUserAnswer();
            if (userAnswer == ModalInputTextDialog.USER_YES) {
                controller.quickAddExaminateur(mitd.getValue());
            }
        });
        jPMExaminateurList.add(jmi);
        jPMExaminateurList.add(new JSeparator());
        HashMap<Long, String> examinateurs;
        examinateurs = controller.refreshAllExaminateurs();

        examinateurs.entrySet().stream().forEach(pair -> {
            JMenuItem item = new JMenuItem(pair.getValue());
            item.addActionListener(al -> {
                selectedExamintorId = pair.getKey();
                jtfNomExaminateur.setText(pair.getValue());
                jtfNomExaminateur.setForeground(Color.WHITE);
            });
            item.setHorizontalAlignment(JMenuItem.RIGHT);
            jPMExaminateurList.add(item);
        });
    }

    
    private static final long serialVersionUID = -4491053531712347684L;

    /**
     * Creates new form ExamenEditForm
     */
   ExamEditFormController controller;

    public ExamenEditForm(ExamEditFormController examEditFormController) {
       initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        controller = examEditFormController;
        initFrame();
    }

       private void initFrame() {
         final Examen selectedExamen = controller.getSelectedExamen();

        
        PlaceHolderDecorator.decorate(jTextFieldGlobalSearch, jTextFieldGlobalSearch.getText());
        PlaceHolderDecorator.decorate(jTextFieldSpecificSearch, jTextFieldSpecificSearch.getText());

        final Color writingColor = new Color(255, 255, 255);
        final Color holdingColor = new Color(187, 187, 187);

        setModal(true);
        requiredFieldValidator = new RequiredFieldValidator();

        String DATE_DE_NAISSANCE_PLACE_HOLDER = "تاريخ الامتحان";
        PlaceHolderDecorator.decorate(jFTFDateExamen, DATE_DE_NAISSANCE_PLACE_HOLDER, holdingColor, writingColor, PlaceHolderDecorator.DATE_FIELD_P);
        requiredFieldValidator.add(jFTFDateExamen, DATE_DE_NAISSANCE_PLACE_HOLDER);

        PlaceHolderDecorator.decorate(jtfNomExaminateur, jtfNomExaminateur.getText(), holdingColor, writingColor);
        requiredFieldValidator.add(jtfNomExaminateur, jtfNomExaminateur.getText());

        setupExaminateurs();

        selectedExamintorId = selectedExamen.getExaminateur().getId();
        jtfNomExaminateur.setText(selectedExamen.getExaminateur().getExaminateurInfo().toString());
        jtfNomExaminateur.setForeground(Color.white);
        jFTFDateExamen.setText(new DateStringConverter("dd-MM-yyyy").toString(selectedExamen.getDateExamen()));
        jFTFDateExamen.setForeground(Color.white);
        controller.showAllCandidat(jList1);
        controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CODE);
        controller.registerListener(this);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPMExaminateurList = new javax.swing.JPopupMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldGlobalSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldSpecificSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabelCode = new javax.swing.JLabel();
        jLabelCreno = new javax.swing.JLabel();
        jLabelCircuit = new javax.swing.JLabel();
        jLabelTitreExamen = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jtfNomExaminateur = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jFTFDateExamen = new javax.swing.JFormattedTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jlbCount = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(235, 188, 0));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18))
        );

        jPanel1.setBackground(new java.awt.Color(20, 6, 0));
        jPanel1.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldGlobalSearch.setFont(new java.awt.Font("Simplified Arabic", 1, 14)); // NOI18N
        jTextFieldGlobalSearch.setForeground(new java.awt.Color(20, 6, 0));
        jTextFieldGlobalSearch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldGlobalSearch.setText("البحث");
        jTextFieldGlobalSearch.setBorder(null);
        jTextFieldGlobalSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldGlobalSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldGlobalSearchKeyTyped(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/icons8_Search_32px_1.png"))); // NOI18N

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jList1.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(45, 45, 45));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "جاري التحميل ..............         " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.setSelectionBackground(new java.awt.Color(247, 243, 178));
        jList1.setSelectionForeground(new java.awt.Color(8, 8, 8));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel7.setFont(new java.awt.Font("Simplified Arabic", 3, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("لائحة المنخرطين");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldGlobalSearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldGlobalSearch))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(20, 80, 261, 500);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldSpecificSearch.setFont(new java.awt.Font("Simplified Arabic", 1, 14)); // NOI18N
        jTextFieldSpecificSearch.setForeground(new java.awt.Color(20, 6, 0));
        jTextFieldSpecificSearch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldSpecificSearch.setText("البحث");
        jTextFieldSpecificSearch.setBorder(null);
        jTextFieldSpecificSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSpecificSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSpecificSearchKeyTyped(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/icons8_Search_32px_1.png"))); // NOI18N

        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 45, 45)));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jList2.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        jList2.setForeground(new java.awt.Color(45, 45, 45));
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "جاري التحميل ..............         " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList2.setSelectionBackground(new java.awt.Color(247, 243, 178));
        jList2.setSelectionForeground(new java.awt.Color(8, 8, 8));
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jLabelCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/examen/addExam/icons8_Google_Code_32px_2.png"))); // NOI18N
        jLabelCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCodeMouseClicked(evt);
            }
        });

        jLabelCreno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/examen/addExam/icons8_Parking_32px_1.png"))); // NOI18N
        jLabelCreno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCrenoMouseClicked(evt);
            }
        });

        jLabelCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/examen/addExam/icons8_Traffic_Jam_32px_1.png"))); // NOI18N
        jLabelCircuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCircuitMouseClicked(evt);
            }
        });

        jLabelTitreExamen.setFont(new java.awt.Font("Simplified Arabic", 3, 24)); // NOI18N
        jLabelTitreExamen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitreExamen.setText("قائمة القانون");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSpecificSearch))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabelCode)
                .addGap(21, 21, 21)
                .addComponent(jLabelCreno)
                .addGap(21, 21, 21)
                .addComponent(jLabelCircuit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitreExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelTitreExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldSpecificSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCreno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCircuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel7);
        jPanel7.setBounds(300, 80, 261, 500);

        jLabel4.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("العدد الاجمالي :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(660, 390, 170, 40);

        jSeparator3.setBackground(new java.awt.Color(235, 188, 0));
        jSeparator3.setForeground(new java.awt.Color(235, 188, 0));
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(30, 60, 850, 2);

        jLabel9.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(235, 188, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText(" واجهة إعداد الامتحانات");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(300, 20, 310, 40);

        jtfNomExaminateur.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jtfNomExaminateur.setForeground(new java.awt.Color(255, 255, 255));
        jtfNomExaminateur.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfNomExaminateur.setText("الممتحن");
        jtfNomExaminateur.setBorder(null);
        jtfNomExaminateur.setOpaque(false);
        jtfNomExaminateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomExaminateurActionPerformed(evt);
            }
        });
        jPanel1.add(jtfNomExaminateur);
        jtfNomExaminateur.setBounds(640, 210, 160, 30);
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(620, 430, 210, 10);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/examen/addExam/icons8_Date_From_32px_1.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(810, 290, 40, 40);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/examen/addExam/icons8_Sort_Down_24px_1.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11);
        jLabel11.setBounds(610, 220, 20, 20);

        jFTFDateExamen.setBorder(null);
        jFTFDateExamen.setForeground(new java.awt.Color(255, 255, 255));
        try {
            jFTFDateExamen.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFDateExamen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFTFDateExamen.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        jFTFDateExamen.setOpaque(false);
        jPanel1.add(jFTFDateExamen);
        jFTFDateExamen.setBounds(620, 300, 180, 31);
        jPanel1.add(jSeparator5);
        jSeparator5.setBounds(620, 240, 210, 10);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/examen/addExam/icons8_Freddy_Krueger_32px.png"))); // NOI18N
        jPanel1.add(jLabel12);
        jLabel12.setBounds(810, 200, 40, 40);

        jSeparator6.setBackground(new java.awt.Color(235, 188, 0));
        jSeparator6.setForeground(new java.awt.Color(235, 188, 0));
        jPanel1.add(jSeparator6);
        jSeparator6.setBounds(590, 170, 270, 10);

        jlbCount.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 18)); // NOI18N
        jlbCount.setForeground(new java.awt.Color(235, 188, 0));
        jlbCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbCount.setText("0");
        jPanel1.add(jlbCount);
        jlbCount.setBounds(620, 390, 40, 40);
        jPanel1.add(jSeparator7);
        jSeparator7.setBounds(620, 330, 210, 10);

        jLabel14.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(235, 188, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("المعلومات الأولية");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(610, 130, 224, 40);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Right_Squared_32px.png"))); // NOI18N
        jLabel15.setText("موافق");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setOpaque(true);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel15);
        jLabel15.setBounds(770, 530, 110, 32);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/candidat/add/icons8_Prev_32px.png"))); // NOI18N
        jLabel16.setText("إلغاء");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel16);
        jLabel16.setBounds(580, 530, 110, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jTextFieldGlobalSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGlobalSearchKeyReleased
        // TODO add your handling code here:

        controller.doSearchForCandidat(jTextFieldGlobalSearch, jList1);
    }//GEN-LAST:event_jTextFieldGlobalSearchKeyReleased

    private void jTextFieldGlobalSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGlobalSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGlobalSearchKeyTyped

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        int clickCount = evt.getClickCount();
        if (clickCount > 1) {
            System.out.println("one event launched");
            controller.moveSelectedElement(jList1, ExamAddFormController.ADD);
            evt.consume();
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        final int selectedIndex = jList1.getSelectedIndex();
        // TODO add your handling code here:
        if (selectedIndex == -1) {
            return;
        }

        //        controller.showSelectedExamInfo(jList1,jlNombreCandidat, jLExaminateur ,jLDateExam);
        //        controller.showSelectedCandidat(jList1, selectedIndex, jlbAdresseValue, jlbNomValue,
            //            jlbdtNaissanceValue, jlbNomPereValue, jlbNomMereValue, jlbNumTelValue, jlbExamenValue,
            //            jlbPermisValue, jlbdtInscValue, jlbLieuNaissanceValue, jlbNationaliteValue3);
        pack();
    }//GEN-LAST:event_jList1ValueChanged

    private void jTextFieldSpecificSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSpecificSearchKeyReleased
        // TODO add your handling code here:
        controller.doSearchForCandidatExamin(jTextFieldSpecificSearch, jList2);
    }//GEN-LAST:event_jTextFieldSpecificSearchKeyReleased

    private void jTextFieldSpecificSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSpecificSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSpecificSearchKeyTyped

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        // TODO add your handling code here:
        int clickCount = evt.getClickCount();
        if (clickCount > 1) {
            controller.moveSelectedElement(jList2, ExamAddFormController.REMOVE);
        }
    }//GEN-LAST:event_jList2MouseClicked

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList2ValueChanged

    private void jLabelCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCodeMouseClicked
        // TODO add your handling code here:
        jLabelTitreExamen.setText("قائمة القانون");
        controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CODE);
    }//GEN-LAST:event_jLabelCodeMouseClicked

    private void jLabelCrenoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCrenoMouseClicked
        // TODO add your handling code here:
        jLabelTitreExamen.setText("قائمة المناورة");
        controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CRENO);
    }//GEN-LAST:event_jLabelCrenoMouseClicked

    private void jLabelCircuitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCircuitMouseClicked
        // TODO add your handling code here:
        jLabelTitreExamen.setText("قائمة السياقة"
            + "");
        controller.showListCandidat(jList2, ExamAddFormController.RequestedListModel.CIRCUIT);
    }//GEN-LAST:event_jLabelCircuitMouseClicked

    private void jtfNomExaminateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomExaminateurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomExaminateurActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        jPMExaminateurList.show(jLabel11, jLabel11.getWidth(), jLabel11.getHeight());
    }//GEN-LAST:event_jLabel11MouseClicked

    private RequiredFieldValidator requiredFieldValidator;

    private Long selectedExamintorId;

    
    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        boolean validate = requiredFieldValidator.validate();
        if (validate) {

            String dtExamen = jFTFDateExamen.getText();
            controller.addExamen(selectedExamintorId,dtExamen);
            dispose();
        }
        //            String nom = jtfNom.getText();
        //            String prenom = jtfPrenom.getText();
        //            String numeroTelephone = jFTFNumTel.getText().replaceAll("\\s", "");
        //            String lieuNaissance = jtfLieuNaissance.getText();
        //            String adresse = jtfAdresse.getText();
        //            String dateNaissance = jFTFDateNaissance.getText();
        //            String dateIns = jFTFDateDepot.getText();
        //            String nationalite = jtfNationalite.getText();
        //            String permisDemander = jbGroupPermisDemander.getSelection().getActionCommand();
        //            String nomPere = jtfNomPere.getText();
        //            String nomMere = jtfNomMere.getText();
        //
        //            controller.addCandidat(nom,prenom,nomPere,nomMere,adresse,numeroTelephone,dateNaissance,lieuNaissance,nationalite,dateIns,permisDemander);

        //        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFTFDateExamen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCircuit;
    private javax.swing.JLabel jLabelCode;
    private javax.swing.JLabel jLabelCreno;
    private javax.swing.JLabel jLabelTitreExamen;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPopupMenu jPMExaminateurList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTextFieldGlobalSearch;
    private javax.swing.JTextField jTextFieldSpecificSearch;
    private javax.swing.JLabel jlbCount;
    private javax.swing.JTextField jtfNomExaminateur;
    // End of variables declaration//GEN-END:variables



}
