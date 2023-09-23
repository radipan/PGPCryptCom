/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import Model.EncryptionAlgorithm;
import Model.SenderEntity;

/**
 *
 * @author adipu
 */
public class EncryptionFactory implements IEncryptionFactory{

    public final SenderEntity senderEntity;
    public EncryptionFactory(SenderEntity senderEntity)
    {
        this.senderEntity = senderEntity;
    }
    public IEncryptionStrategy GetEncryptionStrategy(EncryptionAlgorithm encryptionAlgorithm)
    {
        IEncryptionStrategy encryptionStrategy = null;
        switch (encryptionAlgorithm)
        {
            case RSA:
                encryptionStrategy = new RsaEncryption(senderEntity);
                break;
            case DSA:
                encryptionStrategy = new DsaEncryption(senderEntity);
                break;
            case EC:
                encryptionStrategy = new ECEncryption(senderEntity);
                break;
            default:
                break;
        }
        return encryptionStrategy;
    }
    
}
