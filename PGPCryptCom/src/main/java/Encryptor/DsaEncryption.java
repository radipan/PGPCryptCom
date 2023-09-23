/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import Model.SenderEntity;

/**
 *
 * @author adipu
 */
public class DsaEncryption implements IEncryptionStrategy{

    public final SenderEntity senderEntity;
    public DsaEncryption(SenderEntity senderEntity)
    {
        this.senderEntity = senderEntity;
    }

    @Override
    public String GetPubKey() {
        return "DsaPubKey";
    }

    @Override
    public String GetPrivKey() {
        return "DsaPrivKey";
    }

    @Override
    public String Encrypt(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
