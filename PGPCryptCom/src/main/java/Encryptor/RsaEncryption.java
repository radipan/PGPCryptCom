/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import Model.GenericEntity;
import Model.ReceiverEntity;
import Model.SenderEntity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bouncycastle.asn1.x509.ObjectDigestInfo.publicKey;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.DocumentSignatureType;
import org.pgpainless.decryption_verification.ConsumerOptions;
import org.pgpainless.decryption_verification.DecryptionStream;
import org.pgpainless.decryption_verification.OpenPgpMetadata;
import org.pgpainless.encryption_signing.EncryptionOptions;
import org.pgpainless.encryption_signing.EncryptionResult;
import org.pgpainless.encryption_signing.EncryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;
import org.pgpainless.encryption_signing.SigningOptions;
import org.pgpainless.key.generation.type.rsa.RsaLength;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.pgpainless.util.Passphrase;

/**
 *
 * @author adipu
 */
public class RsaEncryption implements IEncryptionStrategy {

    
    
    public final GenericEntity genericEntity;
    public RsaEncryption(GenericEntity genericEntity)
    {
        this.genericEntity = genericEntity;
    }
    
    @Override
    public String GetPubKey() {
        String returnString = "";
        try {
            PGPSecretKeyRing secretKey = PGPainless.readKeyRing()
                    .secretKeyRing(genericEntity.getPrivateKey());
            PGPPublicKeyRing publicKey = PGPainless.extractCertificate(secretKey);
            returnString =  PGPainless.asciiArmor(publicKey);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnString;
    }

    @Override
    public String GetPrivKey() {
        String returnString = "";
        try {
            String userId = genericEntity.getName() + " <" + genericEntity.getEmail() + ">";
            RsaLength rsaLength = genericEntity.getKeySize();
            String passphrase = genericEntity.getPassphrase();
            PGPSecretKeyRing secretKeys = PGPainless.generateKeyRing()
                    .simpleRsaKeyRing(userId, rsaLength ,passphrase );
            returnString =  PGPainless.asciiArmor(secretKeys);
            
        //ByteArrayOutputStream binary = new ByteArrayOutputStream();
        //secretKeys.encode(binary);
        } catch (PGPException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnString;
    }

    @Override
    public String Encrypt(String message) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        OutputStream outputStream = new OutputStream() {
//            @Override
//            public void write(int b) throws IOException {
//                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };
        PGPSecretKeyRing secretKey;
        PGPPublicKeyRing publicKey = null;
        try {
            secretKey = PGPainless.readKeyRing()
                    .secretKeyRing(genericEntity.getPrivateKey());
            publicKey = PGPainless.extractCertificate(secretKey);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        String encryptedMessage = "";
        EncryptionStream encryptionStream = null;
        try {
            encryptionStream = PGPainless.encryptAndOrSign()
                    .onOutputStream(outputStream)
                    .withOptions(
                            ProducerOptions.signAndEncrypt(
                                    new EncryptionOptions()
                                            .addRecipient(publicKey),
//                                        // optionally encrypt to a passphrase
//                                        .addPassphrase(Passphrase.fromPassword("password123"))
//                                        // optionally override symmetric encryption algorithm
//                                        .overrideEncryptionAlgorithm(SymmetricKeyAlgorithm.AES_192),
                                    new SigningOptions()
//                                        // Sign in-line (using one-pass-signature packet)
//                                        .addInlineSignature(secretKeyDecryptor, aliceSecKey, signatureType)
//                                        // Sign using a detached signature
//                                        .addDetachedSignature(secretKeyDecryptor, aliceSecKey, signatureType)
//                                        // optionally override hash algorithm
//                                        .overrideHashAlgorithm(HashAlgorithm.SHA256)
                            ).setAsciiArmor(true) // Ascii armor or not
                    );
        } catch (PGPException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream plaintextInputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        try {
            Streams.pipeAll(plaintextInputStream, encryptionStream);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            encryptionStream.close();
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        encryptedMessage = outputStream.toString();
        // Information about the encryption (algorithms, detached signatures etc.)
        EncryptionResult result = encryptionStream.getResult();
        return encryptedMessage;
    }

    String Decrypt(String message) throws PGPException, IOException {
        
        String plaintext = "";
        ByteArrayInputStream encryptedInputStream = new ByteArrayInputStream(message.getBytes());
        
        PGPSecretKeyRing secretKey = null;
        try {
            secretKey = PGPainless.readKeyRing()
                    .secretKeyRing(genericEntity.getPrivateKey());
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        Passphrase pass = new Passphrase(genericEntity.getPassphrase().toCharArray());
        SecretKeyRingProtector keyProtector = SecretKeyRingProtector.unlockAnyKeyWith(pass);
        

        ConsumerOptions consumerOptions = new ConsumerOptions()
                .addDecryptionKey(secretKey, keyProtector); // add the decryption key ring

        ByteArrayOutputStream plaintextOut = new ByteArrayOutputStream();
        ByteArrayInputStream ciphertextIn = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));

        // The decryption stream is an input stream from which we read the decrypted data
        DecryptionStream decryptionStream = PGPainless.decryptAndOrVerify()
                .onInputStream(ciphertextIn)
                .withOptions(consumerOptions);

        Streams.pipeAll(decryptionStream, plaintextOut);
        decryptionStream.close(); // remember to close the stream!
        // Result contains information like signature status etc.
        plaintext = plaintextOut.toString();
        System.out.println(plaintext);
        return plaintext;
    }

    String SignAndEncrypt(String message, SenderEntity sender) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        OutputStream outputStream = new OutputStream() {
//            @Override
//            public void write(int b) throws IOException {
//                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };PGPSecretKeyRing secretKey = null;
        PGPSecretKeyRing senderPrivKey = null;
        try {
            senderPrivKey = PGPainless.readKeyRing()
                    .secretKeyRing(sender.getPrivateKey());
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        Passphrase pass = new Passphrase(sender.getPassphrase().toCharArray());
        SecretKeyRingProtector keyProtector = SecretKeyRingProtector.unlockAnyKeyWith(pass);

        PGPSecretKeyRing secretKey;
        PGPPublicKeyRing publicKey = null;
        try {
            secretKey = PGPainless.readKeyRing()
                    .secretKeyRing(genericEntity.getPrivateKey());
            publicKey = PGPainless.extractCertificate(secretKey);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        String encryptedMessage = "";
        EncryptionStream encryptionStream = null;
        try {
            encryptionStream = PGPainless.encryptAndOrSign()
                    .onOutputStream(outputStream)
                    .withOptions(
                            ProducerOptions.signAndEncrypt(
                                    new EncryptionOptions()
                                            .addRecipient(publicKey),
//                                        // optionally encrypt to a passphrase
//                                        .addPassphrase(Passphrase.fromPassword("password123"))
//                                        // optionally override symmetric encryption algorithm
//                                        .overrideEncryptionAlgorithm(SymmetricKeyAlgorithm.AES_192),
                                    new SigningOptions()
                                        .addInlineSignature(keyProtector, senderPrivKey, DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)
//                                        // Sign in-line (using one-pass-signature packet)
//                                        .addInlineSignature(secretKeyDecryptor, aliceSecKey, signatureType)
//                                        // Sign using a detached signature
//                                        .addDetachedSignature(secretKeyDecryptor, aliceSecKey, signatureType)
//                                        // optionally override hash algorithm
//                                        .overrideHashAlgorithm(HashAlgorithm.SHA256)
                            ).setAsciiArmor(true) // Ascii armor or not
                    );
        } catch (PGPException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream plaintextInputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        try {
            Streams.pipeAll(plaintextInputStream, encryptionStream);
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            encryptionStream.close();
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        encryptedMessage = outputStream.toString();
        // Information about the encryption (algorithms, detached signatures etc.)
        EncryptionResult result = encryptionStream.getResult();
        return encryptedMessage;
    }

    String DecryptAndVerify(String message, SenderEntity sender) throws IOException, PGPException {
        String plaintext = "";
        ByteArrayInputStream encryptedInputStream = new ByteArrayInputStream(message.getBytes());
        var verifyKey = PGPainless.readKeyRing().secretKeyRing(sender.getPrivateKey());
        // certificate is the public part of the key
        var certificate = PGPainless.extractCertificate(verifyKey);
        
        PGPSecretKeyRing secretKey = null;
        try {
            secretKey = PGPainless.readKeyRing()
                    .secretKeyRing(genericEntity.getPrivateKey());
        } catch (IOException ex) {
            Logger.getLogger(RsaEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        Passphrase pass = new Passphrase(genericEntity.getPassphrase().toCharArray());
        SecretKeyRingProtector keyProtector = SecretKeyRingProtector.unlockAnyKeyWith(pass);
        

        ConsumerOptions consumerOptions = new ConsumerOptions()
                .addDecryptionKey(secretKey, keyProtector)
                .addVerificationCert(certificate);; // add the decryption key ring

        ByteArrayOutputStream plaintextOut = new ByteArrayOutputStream();
        ByteArrayInputStream ciphertextIn = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));

        // The decryption stream is an input stream from which we read the decrypted data
        DecryptionStream decryptionStream = PGPainless.decryptAndOrVerify()
                .onInputStream(ciphertextIn)
                .withOptions(consumerOptions);

        Streams.pipeAll(decryptionStream, plaintextOut);
        decryptionStream.close(); // remember to close the stream!
        // Result contains information like signature status etc.
        plaintext = plaintextOut.toString();
        System.out.println(plaintext);
        return plaintext;
    }
    
}
