/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import com.google.common.collect.HashBiMap;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.KeyFlag;
import org.pgpainless.key.generation.KeySpec;
import org.pgpainless.key.generation.type.KeyType;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.pgpainless.util.Passphrase;

/**
 *
 * @author adipu
 */
public class KeyModifier {
    
    private PrivateKeyLocalizer keyInfo;
    private Passphrase pass;
    private SecretKeyRingProtector protector;
    
    
    public KeyModifier(PrivateKeyLocalizer keyInfo, String passphrase)    
    {
        this.keyInfo = keyInfo;
        if (!passphrase.isEmpty())
        {
            pass = Passphrase.fromPassword(passphrase);
            protector = SecretKeyRingProtector.unlockEachKeyWith(pass, keyInfo.getKeyRing());
        }
        else{
            pass = Passphrase.emptyPassphrase();
            protector = SecretKeyRingProtector.unprotectedKeys();
        }

    }
    
    
    public void addUserId(String userId)
    {
        var key = keyInfo.getKeyRing();
        try {
            key = PGPainless.modifyKeyRing(key).addUserId(userId, protector).done();
        } catch (PGPException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String path = keyInfo.getPath();
            FileWriter writer = new FileWriter(path);
            writer.write(PGPainless.asciiArmor(key));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void removeUserId(String userId)
    {
        var key = keyInfo.getKeyRing();
        try {
            key = PGPainless.modifyKeyRing(key).revokeUserId(userId, protector).done();
        } catch (PGPException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String path = keyInfo.getPath();
            FileWriter writer = new FileWriter(path);
            writer.write(PGPainless.asciiArmor(key));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void changeExpirationDate(Date date)
    {
        var key = keyInfo.getKeyRing();
        try {
            //key = PGPainless.modifyKeyRing(key)..done();

            key = PGPainless.modifyKeyRing(key).setExpirationDate(date, protector).done();
        } catch (PGPException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String path = keyInfo.getPath();
            FileWriter writer = new FileWriter(path);
            writer.write(PGPainless.asciiArmor(key));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void changePassphrase(String newPass)
    {
        var key = keyInfo.getKeyRing();
        try {
            key = PGPainless.modifyKeyRing(key).changePassphraseFromOldPassphrase(pass)
                    .withSecureDefaultSettings()
                    .toNewPassphrase(Passphrase.fromPassword(newPass))
                    .done();
        } catch (PGPException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String path = keyInfo.getPath();
            FileWriter writer = new FileWriter(path);
            writer.write(PGPainless.asciiArmor(key));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void addSubkey(String strKeyType, String strKeySize, boolean canEncrypt, boolean canSign)
    {
        var key = keyInfo.getKeyRing();
        KeyType keyType = KeyAlgAndSize.valueOfString(strKeyType+strKeySize).keyType;
        KeySpec keySpec;
        if (canEncrypt && canSign)
            keySpec = KeySpec.getBuilder(keyType, KeyFlag.ENCRYPT_COMMS, KeyFlag.ENCRYPT_STORAGE, KeyFlag.SIGN_DATA).build();
        else if (canEncrypt)
            keySpec = KeySpec.getBuilder(keyType, KeyFlag.ENCRYPT_COMMS, KeyFlag.ENCRYPT_STORAGE).build();
        else
            keySpec = KeySpec.getBuilder(keyType, KeyFlag.SIGN_DATA).build();
        
        try {
            key = PGPainless.modifyKeyRing(key).addSubKey(keySpec,
                    pass,
                    protector).done();
            //key = PGPainless.modifyKeyRing(key).a
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String path = keyInfo.getPath();
            FileWriter writer = new FileWriter(path);
            writer.write(PGPainless.asciiArmor(key));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void revokeSubkey(int index)
    {
        var key = keyInfo.getKeyRing();
        int i = 0;
        //long keyId = -1;
        for (var it = key.getSecretKeys(); it.hasNext();++i)
        {
            var subkey = it.next();
            if (i == index)
            {
                try {
                    key = PGPainless.modifyKeyRing(key).revokeSubKey(subkey.getKeyID(), protector).done();
                } catch (PGPException ex) {
                    Logger.getLogger(KeyModifier.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }       
        }
        
        try {
            String path = keyInfo.getPath();
            FileWriter writer = new FileWriter(path);
            writer.write(PGPainless.asciiArmor(key));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    
}
