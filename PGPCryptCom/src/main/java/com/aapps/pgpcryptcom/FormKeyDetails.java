/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aapps.pgpcryptcom;

import Configuration.Parameters;
import Encryptor.KeyModifier;
import Encryptor.PrivateKeyLocalizer;
import com.toedter.calendar.JCalendar;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.bouncycastle.bcpg.ECPublicBCPGKey;
import org.bouncycastle.crypto.examples.JPAKEExample;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.PublicKeyAlgorithm;
import org.pgpainless.key.OpenPgpV4Fingerprint;
import org.pgpainless.key.generation.KeySpec;
import org.pgpainless.key.info.KeyInfo;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/**
 *
 * @author adipu
 */
public class FormKeyDetails extends javax.swing.JFrame {

    /**
     * Creates new form FormKeyDetails
     */
    private PrivateKeyLocalizer keyInfo;
    private List<PGPSecretKey> listSubkeys;
    
    public FormKeyDetails(PrivateKeyLocalizer keyInfo) {
        
        initComponents();
        this.keyInfo = keyInfo;
        refresh();
    }
    
    private void refresh()
    {
        keyInfo = new PrivateKeyLocalizer(keyInfo.getPath());
        
        populateUserIds();
        populateTextAreaKeys();
        populateSubKeyTable();
        populateKeyInfoTable();
    }
    
    private void populateUserIds()
    {
        ArrayList<String> userIds = (ArrayList<String>) PGPainless.inspectKeyRing(keyInfo.getKeyRing()).getValidUserIds();
        jListUserIds = new JList<>(userIds.toArray(new String[userIds.size()]));
        jScrollPane1.setViewportView(jListUserIds);
    }
    
    private void populateTextAreaKeys()
    {
        try {
            jTextAreaKey.setText(PGPainless.asciiArmor(keyInfo.getKeyRing()));
            //jTextAreaKey.setText("wfasf");
            
            //jTextAreaKey.repaint();
        } catch (IOException ex) {
            Logger.getLogger(FormKeyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateKeyInfoTable()
    {
        DefaultTableModel model = (DefaultTableModel) jTableKeyInfo.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"Expiration Date", PGPainless.inspectKeyRing(keyInfo.getKeyRing()).getPrimaryKeyExpirationDate()});
    }
    
    private void populateSubKeyTable()
    {
        //PGPainless.inspectKeyRing(keyInfo.getKeyRing()).getUserIdRevocation(getName()).
        //JOptionPane.showMessageDialog(null, PGPainless.inspectKeyRing(keyInfo.getKeyRing()).getAlgorithm().toString());
        listSubkeys = new ArrayList<PGPSecretKey>();
        DefaultTableModel model = (DefaultTableModel) jTableSubKeys.getModel();
        model.setRowCount(0);
        for (var it = keyInfo.getKeyRing().getSecretKeys(); it.hasNext();)
        {
            var subkey = it.next();
            if (!subkey.getPublicKey().hasRevocation())
                listSubkeys.add(subkey);
            //model.addRow(new Object[]{subkey.getKeyEncryptionAlgorithm(), subkey.toString(),subkey.isMasterKey(),subkey.isSigningKey(),subkey.getPublicKey().isEncryptionKey(),(new OpenPgpV4Fingerprint(subkey)).prettyPrint()});
        }
        for (PGPSecretKey subkey : listSubkeys)
        {
            
            //if (subkey.getPublicKey().getPublicKeyPacket().getKey() instanceof ECPublicBCPGKey)
            //    JOptionPane.showMessageDialog(null, ((ECPublicBCPGKey)subkey.getPublicKey().getPublicKeyPacket().getKey()). getCurveOID());
            model.addRow(new Object[]{PublicKeyAlgorithm.fromId(subkey.getPublicKey().getAlgorithm()).toString().replace("_GENERAL", ""),
                subkey.getPublicKey().getBitStrength(),
                subkey.isMasterKey(),
                (PGPainless.inspectKeyRing(keyInfo.getKeyRing()).getKeyFlagsOf(subkey.getKeyID())).toString().contains("SIGN"),
                (PGPainless.inspectKeyRing(keyInfo.getKeyRing()).getKeyFlagsOf(subkey.getKeyID())).toString().contains("ENCRYPT"),
                (new OpenPgpV4Fingerprint(subkey)).prettyPrint()});
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListUserIds = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSubKeys = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButtonAddUser = new javax.swing.JButton();
        jButtonRemoveUser = new javax.swing.JButton();
        jButtonExpDate = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaKey = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButtonPassphrase = new javax.swing.JButton();
        jButtonAddSubkey = new javax.swing.JButton();
        jButtonRevokeSubkey = new javax.swing.JButton();
        jButtonCopy = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableKeyInfo = new javax.swing.JTable();
        jButtonExportPub = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(jListUserIds);

        jLabel1.setText("User IDs");

        jTableSubKeys.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Algorithm", "Size", "Primary", "Can Sign", "Can Encrypt", "Key-ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableSubKeys);

        jLabel2.setText("Subkeys");

        jButtonAddUser.setText("Add User ID");
        jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUserActionPerformed(evt);
            }
        });

        jButtonRemoveUser.setText("Remove Selected User");
        jButtonRemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveUserActionPerformed(evt);
            }
        });

        jButtonExpDate.setText("Change Expiration Date");
        jButtonExpDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExpDateActionPerformed(evt);
            }
        });

        jTextAreaKey.setEditable(false);
        jTextAreaKey.setColumns(20);
        jTextAreaKey.setRows(5);
        jScrollPane3.setViewportView(jTextAreaKey);

        jLabel3.setText("Key Content");

        jButtonPassphrase.setText("Change Passphrase");
        jButtonPassphrase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPassphraseActionPerformed(evt);
            }
        });

        jButtonAddSubkey.setText("Add Subkey");
        jButtonAddSubkey.setToolTipText("");
        jButtonAddSubkey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSubkeyActionPerformed(evt);
            }
        });

        jButtonRevokeSubkey.setText("Revoke SubKey");
        jButtonRevokeSubkey.setToolTipText("");
        jButtonRevokeSubkey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRevokeSubkeyActionPerformed(evt);
            }
        });

        jButtonCopy.setText("Copy To Clipboard");
        jButtonCopy.setToolTipText("");
        jButtonCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCopyActionPerformed(evt);
            }
        });

        Delete.setText("Delete Key");
        Delete.setToolTipText("");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jButtonExport.setText("Export");
        jButtonExport.setToolTipText("");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jLabel4.setText("Key Info:");

        jTableKeyInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attribute", "Value"
            }
        ));
        jScrollPane4.setViewportView(jTableKeyInfo);

        jButtonExportPub.setText("Export public key");
        jButtonExportPub.setToolTipText("");
        jButtonExportPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportPubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRevokeSubkey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddSubkey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemoveUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExpDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButtonPassphrase, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jButtonCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButtonExportPub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                .addComponent(Delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButtonAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 611, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 109, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddUser)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButtonRemoveUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExpDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPassphrase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddSubkey)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRevokeSubkey)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExportPub)
                        .addGap(12, 12, 12)
                        .addComponent(Delete)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButtonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUserActionPerformed
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        JLabel labelUser = new JLabel("UserId: ");
        JTextField jTextFieldUser = new JTextField(30) ;
        panel.add(labelUser);
        panel.add(jTextFieldUser);
        //panel.add(Box.createHorizontalStrut(15));
        //panel.setSize(20, 20);
        JLabel label = new JLabel("Passphrase: ");
        JPasswordField passField = new JPasswordField(30);
        panel.add(label);
        panel.add(passField);
        String[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(this, panel,
                "UserID and Passphrase", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        if (option == 0)
        {
            //JOptionPane.showMessageDialog(this, jTextFieldUser.getText());
            //JOptionPane.showMessageDialog(this, String.valueOf(passField.getPassword()));
            KeyModifier km = new KeyModifier(keyInfo, String.valueOf(passField.getPassword()));
            km.addUserId(jTextFieldUser.getText());
            refresh();
        }
    }//GEN-LAST:event_jButtonAddUserActionPerformed

    private void jButtonRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveUserActionPerformed
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Passphrase: ");
        JPasswordField passField = new JPasswordField(30);
        panel.add(label);
        panel.add(passField);
        
        String[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(this, panel,
                "Passphrase", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        if (option == 0)
        {
            //JOptionPane.showMessageDialog(this, jTextFieldUser.getText());
            //JOptionPane.showMessageDialog(this, String.valueOf(passField.getPassword()));
            KeyModifier km = new KeyModifier(keyInfo, String.valueOf(passField.getPassword()));
            km.removeUserId(jListUserIds.getSelectedValue());
            refresh();
        }
    }//GEN-LAST:event_jButtonRemoveUserActionPerformed

    private void jButtonExpDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExpDateActionPerformed
        
        
        String[] options = {"OK", "Cancel"};
        
        PanelExpDate panelExpDate = new PanelExpDate();
        
        int option = JOptionPane.showOptionDialog(this, panelExpDate,
                "Date and Passphrase", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        
        if (option == 0)
        {
            
            //JOptionPane.showMessageDialog(this, panelExpDate.getSelectedDate());
            //JOptionPane.showMessageDialog(this, panelExpDate.getPass());
            KeyModifier km = new KeyModifier(keyInfo, panelExpDate.getPass());
            km.changeExpirationDate(panelExpDate.getSelectedDate());
            refresh();
        }
    }//GEN-LAST:event_jButtonExpDateActionPerformed

    private void jButtonPassphraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPassphraseActionPerformed
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        JLabel labelOld = new JLabel("Current Passphrase: ");
        JPasswordField passOld = new JPasswordField(30);
        panel.add(labelOld);
        panel.add(passOld);
        
        JLabel labelNew = new JLabel("New Passphrase: ");
        JPasswordField passNew = new JPasswordField(30);
        panel.add(labelNew);
        panel.add(passNew);
        
        String[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(this, panel,
                "Passphrase", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        if (option == 0)
        {
            KeyModifier km = new KeyModifier(keyInfo, String.valueOf(passOld.getPassword()));
            km.changePassphrase(String.valueOf(passNew.getPassword()));
            refresh();
        }
    }//GEN-LAST:event_jButtonPassphraseActionPerformed

    private void jButtonAddSubkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSubkeyActionPerformed
        PanelAddSubkey panel = new PanelAddSubkey();
        String[] options = {"OK", "Cancel"};
        
        int option = JOptionPane.showOptionDialog(this, panel,
                "Date and Passphrase", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        
        if (option == 0)
        {
            KeyModifier km = new KeyModifier(keyInfo, panel.getPassphrase());
            km.addSubkey(panel.getKeyAlg(), panel.getKeySize(), panel.getCanEncrypt(), panel.getCanSign());
            refresh();
        }
    }//GEN-LAST:event_jButtonAddSubkeyActionPerformed

    private void jButtonRevokeSubkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRevokeSubkeyActionPerformed
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Passphrase: ");
        JPasswordField passField = new JPasswordField(30);
        panel.add(label);
        panel.add(passField);
        
        String[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(this, panel,
                "Passphrase", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        if (option == 0)
        {
            //JOptionPane.showMessageDialog(this, jTextFieldUser.getText());
            //JOptionPane.showMessageDialog(this, String.valueOf(passField.getPassword()));
            KeyModifier km = new KeyModifier(keyInfo, String.valueOf(passField.getPassword()));
            if (jTableSubKeys.getSelectedRow() != -1)
                km.revokeSubkey(jTableSubKeys.getSelectedRow());
            refresh();
        }
    }//GEN-LAST:event_jButtonRevokeSubkeyActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        JFileChooser saveDialog = new JFileChooser();
        if (saveDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File fileExport = saveDialog.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(fileExport);
                writer.write(PGPainless.asciiArmor(keyInfo.getKeyRing()));
                writer.close();
            //log write success
            } catch (IOException e) {
                //log
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            refresh();
        }
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopyActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(jTextAreaKey.getText()),null);
    }//GEN-LAST:event_jButtonCopyActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        File keyFile = new File(keyInfo.getPath());
        keyFile.delete();
        this.dispose();
    }//GEN-LAST:event_DeleteActionPerformed

    private void jButtonExportPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportPubActionPerformed
        JFileChooser saveDialog = new JFileChooser();
        if (saveDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File fileExport = saveDialog.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(fileExport);
                writer.write(PGPainless.asciiArmor(PGPainless.extractCertificate(keyInfo.getKeyRing())));
                writer.close();
            //log write success
            } catch (IOException e) {
                //log
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonExportPubActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormKeyDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormKeyDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormKeyDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormKeyDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormKeyDetails().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton jButtonAddSubkey;
    private javax.swing.JButton jButtonAddUser;
    private javax.swing.JButton jButtonCopy;
    private javax.swing.JButton jButtonExpDate;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonExportPub;
    private javax.swing.JButton jButtonPassphrase;
    private javax.swing.JButton jButtonRemoveUser;
    private javax.swing.JButton jButtonRevokeSubkey;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jListUserIds;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableKeyInfo;
    private javax.swing.JTable jTableSubKeys;
    private javax.swing.JTextArea jTextAreaKey;
    // End of variables declaration//GEN-END:variables
}
