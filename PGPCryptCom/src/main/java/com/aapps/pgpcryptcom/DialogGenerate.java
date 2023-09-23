/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package com.aapps.pgpcryptcom;

import Encryptor.KeyGenerator;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author adipu
 */
public class DialogGenerate extends java.awt.Dialog {

    /**
     * Creates new form DialogGenerate
     */
    public DialogGenerate(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    
    private final int EXTEND_FORM_HEIGHT = 300;
    
    private final String[] RSA_KEY_SIZE_LIST = {"8192", "4096", "3072"};
    private final String[] ECDSA_KEY_SIZE_LIST = {"P521", "P384", "P256", "SECP256K1", "BRAINPOOLP256R1", "BRAINPOOLP384R1", "BRAINPOOLP512R1"};
    private final String[] EDDSA_KEY_SIZE_LIST = {"Ed25519"};



    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jCheckBoxAdvancedOptions = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxPrimaryKey = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxSizePrimaryKey = new javax.swing.JComboBox<>();
        jCheckBoxEncryptionKey = new javax.swing.JCheckBox();
        jComboBoxEncSubkey = new javax.swing.JComboBox<>();
        jComboBoxEncKeySize = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jCheckBoxSigningKey = new javax.swing.JCheckBox();
        jComboBoxSignSubkey = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxSigningKeySize = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxSymmAlg = new javax.swing.JComboBox<>();
        jComboBoxHashAlg = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPasswordFieldPassphrase = new javax.swing.JPasswordField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();

        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(475, 201));

        jLabel1.setText("Name");

        jLabel2.setText("E-mail");

        jLabel3.setText("Passphrase");

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");

        jCheckBoxAdvancedOptions.setText("Advanced Options");
        jCheckBoxAdvancedOptions.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxAdvancedOptionsStateChanged(evt);
            }
        });
        jCheckBoxAdvancedOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdvancedOptionsActionPerformed(evt);
            }
        });

        jLabel4.setText("Primary Key:");

        jComboBoxPrimaryKey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RSA", "ECDSA", "EDDSA" }));
        jComboBoxPrimaryKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrimaryKeyActionPerformed(evt);
            }
        });

        jLabel5.setText("Size:");

        jComboBoxSizePrimaryKey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8192", "4096", "3072" }));

        jCheckBoxEncryptionKey.setSelected(true);
        jCheckBoxEncryptionKey.setText("Encryption Subkey");
        jCheckBoxEncryptionKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEncryptionKeyActionPerformed(evt);
            }
        });

        jComboBoxEncSubkey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RSA", "ECDH" }));
        jComboBoxEncSubkey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEncSubkeyActionPerformed(evt);
            }
        });

        jComboBoxEncKeySize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8192", "4096", "3072" }));

        jLabel6.setText("Size:");

        jCheckBoxSigningKey.setText("Signing Subkey");
        jCheckBoxSigningKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSigningKeyActionPerformed(evt);
            }
        });

        jComboBoxSignSubkey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RSA", "ECDSA", "EDDSA" }));
        jComboBoxSignSubkey.setEnabled(false);
        jComboBoxSignSubkey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSignSubkeyActionPerformed(evt);
            }
        });

        jLabel7.setText("Size:");

        jComboBoxSigningKeySize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8192", "4096", "3072" }));
        jComboBoxSigningKeySize.setEnabled(false);

        jLabel8.setText("Symmetric Algorithm:");

        jComboBoxSymmAlg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AES_256", "AES_192", "AES_128", "BLOWFISH", "CAMELLIA_128", "CAMELLIA_192", "CAMELLIA_256", "CAST5", "DES", "IDEA", "TRIPLE_DES", "TWOFISH" }));

        jComboBoxHashAlg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SHA512", "SHA384", "SHA256", "SHA224", "SHA1", "SHA3-256", "SHA3-512" }));

        jLabel9.setText("Hash");

        jLabel10.setText("Expiration Date:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxAdvancedOptions)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                            .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                            .addComponent(jPasswordFieldPassphrase))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jCheckBoxEncryptionKey)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                                .addComponent(jComboBoxEncSubkey, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBoxPrimaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBoxSizePrimaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBoxEncKeySize, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(24, 24, 24))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCheckBoxSigningKey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jComboBoxSignSubkey, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jComboBoxSymmAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel9))
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxHashAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxSigningKeySize, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldPassphrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAdvancedOptions)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonOK)
                            .addComponent(jButtonCancel))
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxPrimaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxSizePrimaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxEncryptionKey)
                            .addComponent(jComboBoxEncSubkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxEncKeySize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxSigningKey)
                            .addComponent(jComboBoxSignSubkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxSigningKeySize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSymmAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxHashAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jCheckBoxAdvancedOptionsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxAdvancedOptionsStateChanged
        //this.setSize(this.getSize().width, this.getSize().height + 30);
    }//GEN-LAST:event_jCheckBoxAdvancedOptionsStateChanged

    private void jCheckBoxAdvancedOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdvancedOptionsActionPerformed
        if (jCheckBoxAdvancedOptions.isSelected())
        this.setSize(this.getSize().width, this.getSize().height + EXTEND_FORM_HEIGHT);
        else
        this.setSize(this.getSize().width, this.getSize().height - EXTEND_FORM_HEIGHT);
    }//GEN-LAST:event_jCheckBoxAdvancedOptionsActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        if (jCheckBoxAdvancedOptions.isSelected())
        {
            if (jCheckBoxEncryptionKey.isSelected() && jCheckBoxSigningKey.isSelected())
            {
                KeyGenerator kg = new KeyGenerator(jTextFieldName.getText(),
                        jTextFieldEmail.getText(),
                        String.valueOf(jPasswordFieldPassphrase.getPassword()),
                        jComboBoxSizePrimaryKey.getSelectedItem().toString(),
                        jComboBoxEncKeySize.getSelectedItem().toString(),
                        jComboBoxSigningKeySize.getSelectedItem().toString(),
                        jComboBoxSymmAlg.getSelectedItem().toString(),
                        jComboBoxHashAlg.getSelectedItem().toString(),
                        jDateChooser1.getDate());
            }
            else if (!jCheckBoxSigningKey.isSelected() && !jCheckBoxEncryptionKey.isSelected())
            {
                KeyGenerator kg = new KeyGenerator(jTextFieldName.getText(),
                        jTextFieldEmail.getText(),
                        String.valueOf(jPasswordFieldPassphrase.getPassword()),
                        jComboBoxSizePrimaryKey.getSelectedItem().toString(),
                        jComboBoxSymmAlg.getSelectedItem().toString(),
                        jComboBoxHashAlg.getSelectedItem().toString(),
                        jDateChooser1.getDate());
            }
            else if (jCheckBoxSigningKey.isSelected() && !jCheckBoxEncryptionKey.isSelected())
            {
                KeyGenerator kg = new KeyGenerator(jTextFieldName.getText(),
                        jTextFieldEmail.getText(),
                        String.valueOf(jPasswordFieldPassphrase.getPassword()),
                        jComboBoxSizePrimaryKey.getSelectedItem().toString(),
                        jComboBoxSigningKeySize.getSelectedItem().toString(),
                        jComboBoxSymmAlg.getSelectedItem().toString(),
                        jComboBoxHashAlg.getSelectedItem().toString(),
                        jDateChooser1.getDate(),
                        true);
            }
            else if (!jCheckBoxSigningKey.isSelected() && jCheckBoxEncryptionKey.isSelected())
            {
                KeyGenerator kg = new KeyGenerator(jTextFieldName.getText(),
                        jTextFieldEmail.getText(),
                        String.valueOf(jPasswordFieldPassphrase.getPassword()),
                        jComboBoxSizePrimaryKey.getSelectedItem().toString(),
                        jComboBoxEncKeySize.getSelectedItem().toString(),
                        jComboBoxSymmAlg.getSelectedItem().toString(),
                        jComboBoxHashAlg.getSelectedItem().toString(),
                        jDateChooser1.getDate(),
                        true);
            }
        }
        else
        {
            KeyGenerator kg = new KeyGenerator(jTextFieldName.getText(), jTextFieldEmail.getText(), String.valueOf(jPasswordFieldPassphrase.getPassword()));
        }
        
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jComboBoxPrimaryKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrimaryKeyActionPerformed
        switch (jComboBoxPrimaryKey.getSelectedItem().toString())
        {
            case "RSA" -> jComboBoxSizePrimaryKey.setModel(new DefaultComboBoxModel<>(RSA_KEY_SIZE_LIST));
            case "ECDSA" -> jComboBoxSizePrimaryKey.setModel(new DefaultComboBoxModel<>(ECDSA_KEY_SIZE_LIST));
            case "EDDSA" -> jComboBoxSizePrimaryKey.setModel(new DefaultComboBoxModel<>(EDDSA_KEY_SIZE_LIST));
            default -> {
            }
                
        }
    }//GEN-LAST:event_jComboBoxPrimaryKeyActionPerformed

    private void jComboBoxEncSubkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEncSubkeyActionPerformed
        switch (jComboBoxEncSubkey.getSelectedItem().toString())
        {
            case "RSA" -> jComboBoxEncKeySize.setModel(new DefaultComboBoxModel<>(RSA_KEY_SIZE_LIST));
            case "ECDH" -> jComboBoxEncKeySize.setModel(new DefaultComboBoxModel<>(ECDSA_KEY_SIZE_LIST));
            default -> {
            }
                
        }
    }//GEN-LAST:event_jComboBoxEncSubkeyActionPerformed

    private void jComboBoxSignSubkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSignSubkeyActionPerformed
        switch (jComboBoxSignSubkey.getSelectedItem().toString())
        {
            case "RSA" -> jComboBoxSigningKeySize.setModel(new DefaultComboBoxModel<>(RSA_KEY_SIZE_LIST));
            case "ECDSA" -> jComboBoxSigningKeySize.setModel(new DefaultComboBoxModel<>(ECDSA_KEY_SIZE_LIST));
            case "EDDSA" -> jComboBoxSigningKeySize.setModel(new DefaultComboBoxModel<>(EDDSA_KEY_SIZE_LIST));
            default -> {
            }
                
        }
    }//GEN-LAST:event_jComboBoxSignSubkeyActionPerformed

    private void jCheckBoxEncryptionKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEncryptionKeyActionPerformed
        if (jCheckBoxEncryptionKey.isSelected())
        {
            jComboBoxEncSubkey.setEnabled(true);
            jComboBoxEncKeySize.setEnabled(true);
        }
        else
        { 
            jComboBoxEncSubkey.setEnabled(false);
            jComboBoxEncKeySize.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxEncryptionKeyActionPerformed

    private void jCheckBoxSigningKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSigningKeyActionPerformed
        if (jCheckBoxSigningKey.isSelected())
        {
            jComboBoxSignSubkey.setEnabled(true);
            jComboBoxSigningKeySize.setEnabled(true);
        }
        else
        { 
            jComboBoxSignSubkey.setEnabled(false);
            jComboBoxSigningKeySize.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxSigningKeyActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DialogGenerate dialog = new DialogGenerate(new java.awt.Frame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JCheckBox jCheckBoxAdvancedOptions;
    private javax.swing.JCheckBox jCheckBoxEncryptionKey;
    private javax.swing.JCheckBox jCheckBoxSigningKey;
    private javax.swing.JComboBox<String> jComboBoxEncKeySize;
    private javax.swing.JComboBox<String> jComboBoxEncSubkey;
    private javax.swing.JComboBox<String> jComboBoxHashAlg;
    private javax.swing.JComboBox<String> jComboBoxPrimaryKey;
    private javax.swing.JComboBox<String> jComboBoxSignSubkey;
    private javax.swing.JComboBox<String> jComboBoxSigningKeySize;
    private javax.swing.JComboBox<String> jComboBoxSizePrimaryKey;
    private javax.swing.JComboBox<String> jComboBoxSymmAlg;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldPassphrase;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
