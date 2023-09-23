/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.DocumentSignatureType;
import org.pgpainless.decryption_verification.MessageInspector;
import org.pgpainless.encryption_signing.EncryptionOptions;
import org.pgpainless.encryption_signing.EncryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;
import org.pgpainless.encryption_signing.SigningOptions;
import org.pgpainless.key.info.KeyInfo;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.pgpainless.util.Passphrase;

/**
 *
 * @author adipu
 */
public class EncryptionHelper {
    
    public static byte[] EncryptForOther(String path, PublicKeyLocalizer kInfo) throws PGPException, IOException
    {
        
        byte[] plainText = null;
        try 
        {
            Path fileName = Path.of(path);
            plainText = Files.readAllBytes(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        
        ByteArrayOutputStream cipherStream = new ByteArrayOutputStream();
        EncryptionStream encStream = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherStream)
                .withOptions(ProducerOptions.encrypt(
                          EncryptionOptions.encryptDataAtRest()
                                        .addRecipient(kInfo.getKeyRing())
                        ).setAsciiArmor(true)
                );
        
        
        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plainText), encStream);
        encStream.close();
        byte[] encryptedBytes = cipherStream.toByteArray();
        
        return encryptedBytes;
        
    }
    
    public static byte[] EncryptForMe(String path, PrivateKeyLocalizer kInfo) throws PGPException, IOException
    {
        
        byte[] plainText = null;
        try 
        {
            Path fileName = Path.of(path);
            plainText = Files.readAllBytes(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        
        ByteArrayOutputStream cipherStream = new ByteArrayOutputStream();
        EncryptionStream encStream = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherStream)
                .withOptions(ProducerOptions.encrypt(
                          EncryptionOptions.encryptDataAtRest()
                                        .addRecipient(PGPainless.extractCertificate(kInfo.getKeyRing()))
                        ).setAsciiArmor(true)
                );
        Streams.pipeAll(new ByteArrayInputStream(plainText), encStream);
        encStream.close();
        byte[] encryptedBytes = cipherStream.toByteArray();
        return encryptedBytes;
                
    }
    
    public static byte[] EncryptForOtherAndSign(String path, PublicKeyLocalizer kInfo, PrivateKeyLocalizer kInfoSign, String passhphrase) throws PGPException, IOException
    {
        
        byte[] plainText = null;
        try 
        {
            Path fileName = Path.of(path);
            plainText = Files.readAllBytes(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
         var keyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase.fromPassword(passhphrase), kInfoSign.getKeyRing());
        
        
        ByteArrayOutputStream cipherStream = new ByteArrayOutputStream();
        EncryptionStream encStream = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherStream)
                .withOptions(ProducerOptions.signAndEncrypt(
                          EncryptionOptions.encryptDataAtRest()
                                        .addRecipient(kInfo.getKeyRing()),
                                        new SigningOptions().addInlineSignature(keyProtector, 
                                                kInfoSign.getKeyRing(),
                                             DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)
                        ).setAsciiArmor(true)
                );
        
        
        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plainText), encStream);
        encStream.close();
        byte[] encryptedBytes = cipherStream.toByteArray();
        return encryptedBytes;

        
    }
    
    public static byte[] EncryptForMeAndSign(String path, PrivateKeyLocalizer kInfo, PrivateKeyLocalizer kInfoSign, String passhphrase) throws PGPException, IOException
    {
        
        byte[] plainText = null;
        try 
        {
            Path fileName = Path.of(path);
            plainText = Files.readAllBytes(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        var keyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase.fromPassword(passhphrase), kInfoSign.getKeyRing());
        
        
        ByteArrayOutputStream cipherStream = new ByteArrayOutputStream();
        EncryptionStream encStream = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherStream)
                .withOptions(ProducerOptions.signAndEncrypt(
                          EncryptionOptions.encryptDataAtRest()
                                        .addRecipient(PGPainless.extractCertificate(kInfo.getKeyRing())),
                                        new SigningOptions().addInlineSignature(keyProtector, 
                                                kInfoSign.getKeyRing(),
                                             DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)
                        ).setAsciiArmor(true)
                );
        
        
        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plainText), encStream);
        encStream.close();
        byte[] encryptedBytes = cipherStream.toByteArray();
        return encryptedBytes;

        
    }
    
    public static byte[] Sign(String path, PrivateKeyLocalizer kInfo, String passhphrase) throws PGPException, IOException
    {
        
        byte[] plainText = null;
        try 
        {
            Path fileName = Path.of(path);
            plainText = Files.readAllBytes(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        var keyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase.fromPassword(passhphrase), kInfo.getKeyRing());
        
        ByteArrayOutputStream cipherStream = new ByteArrayOutputStream();
        EncryptionStream encStream = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherStream)
                .withOptions(ProducerOptions.sign(
                          SigningOptions.get()
                                    .addSignature(keyProtector, kInfo.getKeyRing())
                        ).setAsciiArmor(true)
                );
        
        
        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plainText), encStream);
        encStream.close();
        byte[] encryptedBytes = cipherStream.toByteArray();
        return encryptedBytes;

        
    }
    
    
}
