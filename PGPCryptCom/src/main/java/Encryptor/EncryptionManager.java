/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import Model.SenderEntity;
import Model.EncryptionAlgorithm;

/**
 *
 * @author adipu
 */
public class EncryptionManager implements IEncryptionManager{

    @Override
    public void GenerateKey(SenderEntity senderEntity) {
        switch (senderEntity.getEncryptionAlgorithm())
            {
            case RSA:
                break;
            case DSA:
                break;
            case EC:
                break;
            default:
                throw new AssertionError(senderEntity.getEncryptionAlgorithm().name());
                
            }
    }
    
}
