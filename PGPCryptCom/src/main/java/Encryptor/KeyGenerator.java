/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import Configuration.Parameters;
import InputOutput.FileHandler;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.CompressionAlgorithm;
import org.pgpainless.algorithm.HashAlgorithm;
import org.pgpainless.algorithm.KeyFlag;
import org.pgpainless.algorithm.SymmetricKeyAlgorithm;
import org.pgpainless.key.generation.KeySpec;
import org.pgpainless.key.generation.KeySpecBuilder;
import org.pgpainless.key.generation.type.KeyType;
import org.pgpainless.key.generation.type.ecc.EllipticCurve;
import org.pgpainless.key.generation.type.eddsa.EdDSACurve;
import org.pgpainless.key.generation.type.rsa.RsaLength;
import org.pgpainless.util.Passphrase;

/**
 *
 * @author adipu
 */
public class KeyGenerator {
    
    private String userId = null;
    private Passphrase passphrase = Passphrase.emptyPassphrase();
    
    private KeyType primaryKeyType = KeyType.RSA(RsaLength._8192);
    private KeyType encKeyType = KeyType.RSA(RsaLength._8192);
    private KeyType singingKeyType = null;
    
    private SymmetricKeyAlgorithm symmAlg = SymmetricKeyAlgorithm.fromId(SymmetricKeyAlgorithmTags.AES_256);
    private HashAlgorithm hashAlg = HashAlgorithm.fromName("SHA512");
    private PGPSecretKeyRing secretKeyRing;
    private Date dateExp;
    
    //simple key
    public KeyGenerator(String userName, String email, String passphrase)
    {
        userId = userName + "<" + email + ">";
        
        if (passphrase != null)
            this.passphrase = Passphrase.fromPassword(passphrase);
        
        KeySpecBuilder primaryKeySpec = KeySpec.getBuilder(
                primaryKeyType,
                KeyFlag.CERTIFY_OTHER,
                KeyFlag.SIGN_DATA)
                .overridePreferredHashAlgorithms(hashAlg)
                .overridePreferredSymmetricKeyAlgorithms(symmAlg);
        
        
        KeySpecBuilder encKeySpec = KeySpec.getBuilder(
                encKeyType,
                KeyFlag.ENCRYPT_COMMS,
                KeyFlag.ENCRYPT_STORAGE);
        
        try {
            secretKeyRing = PGPainless.buildKeyRing()
                    .setPrimaryKey(primaryKeySpec)
                    .addSubkey(encKeySpec)
                    .addUserId(userId)
                    .setPassphrase(this.passphrase)
                    .build();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String keyId = PGPainless.inspectKeyRing(secretKeyRing).getFingerprint().prettyPrint();
        String keyFileName = userName + " " + keyId +".pgp";
        
        //TODO do this in file writer class
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\" + keyFileName);
            writer.write(PGPainless.asciiArmor(secretKeyRing));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
//        FileHandler fh = new FileHandler();
//        fh.createFile(Parameters.Constants.RootPathForPrivKeys, keyFileName);
//        
//        
//        try {
//            fh.writeToFile(Parameters.Constants.RootPathForPrivKeys, keyFileName, PGPainless.asciiArmor(secretKeyRing));
//        } catch (IOException ex) {
//            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public KeyGenerator(String userName, String email, String passphrase, String primKeyType, String encKeyType, String signingKeyType, String symmAlg, String hashAlg, Date dateExp)
    {
        userId = userName + "<" + email + ">";
        
        if (passphrase != null)
            this.passphrase = Passphrase.fromPassword(passphrase);
        
        this.primaryKeyType = getKeyTypeAndLengthFromString(primKeyType);
        this.singingKeyType = getKeyTypeAndLengthFromString(signingKeyType);
        this.encKeyType = getKeyTypeAndLengthFromStringEncryptionKey(encKeyType);
        this.symmAlg = SymmetricKeyAlgorithm.valueOf(symmAlg);
        this.hashAlg = HashAlgorithm.fromName(hashAlg);
        this.dateExp = dateExp;
        
        KeySpecBuilder primaryKeySpec = KeySpec.getBuilder(
                primaryKeyType,
                KeyFlag.CERTIFY_OTHER)
                .overridePreferredHashAlgorithms(this.hashAlg)
                .overridePreferredSymmetricKeyAlgorithms(this.symmAlg);
        
        KeySpecBuilder encKeySpec = KeySpec.getBuilder(
                this.encKeyType,
                KeyFlag.ENCRYPT_COMMS,
                KeyFlag.ENCRYPT_STORAGE);
        
        KeySpecBuilder signingKeySpec = KeySpec.getBuilder(
                this.singingKeyType,
                KeyFlag.SIGN_DATA);
        
        try {
            secretKeyRing = PGPainless.buildKeyRing()
                    .setPrimaryKey(primaryKeySpec)
                    .addSubkey(encKeySpec)
                    .addSubkey(signingKeySpec)
                    .addUserId(userId)
                    .setPassphrase(this.passphrase)
                    .setExpirationDate(this.dateExp)
                    .build();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String keyId = PGPainless.inspectKeyRing(secretKeyRing).getFingerprint().prettyPrint();
        String keyFileName = userName + " " + keyId +".pgp";
        
        //TODO do this in file writer class
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\" + keyFileName);
            writer.write(PGPainless.asciiArmor(secretKeyRing));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
    public KeyGenerator(String userName, String email, String passphrase, String primKeyType, String symmAlg, String hashAlg, Date dateExp)
    {
        userId = userName + "<" + email + ">";
        
        if (passphrase != null)
            this.passphrase = Passphrase.fromPassword(passphrase);
        
        this.primaryKeyType = getKeyTypeAndLengthFromString(primKeyType);
        this.symmAlg = SymmetricKeyAlgorithm.valueOf(symmAlg);
        this.hashAlg = HashAlgorithm.fromName(hashAlg);
        this.dateExp = dateExp;
        
        KeySpecBuilder primaryKeySpec;
        
        if (this.primaryKeyType.canEncryptStorage())
        {
            primaryKeySpec = KeySpec.getBuilder(
                    primaryKeyType,
                    KeyFlag.CERTIFY_OTHER, KeyFlag.ENCRYPT_STORAGE, KeyFlag.ENCRYPT_COMMS, KeyFlag.SIGN_DATA)
                    .overridePreferredHashAlgorithms(this.hashAlg)
                    .overridePreferredSymmetricKeyAlgorithms(this.symmAlg);
        }
        else
        {
            primaryKeySpec = KeySpec.getBuilder(
                    primaryKeyType,
                    KeyFlag.CERTIFY_OTHER, KeyFlag.SIGN_DATA)
                    .overridePreferredHashAlgorithms(this.hashAlg)
                    .overridePreferredSymmetricKeyAlgorithms(this.symmAlg);
        }
                
        try {
            secretKeyRing = PGPainless.buildKeyRing()
                    .setPrimaryKey(primaryKeySpec)
                    .addUserId(userId)
                    .setPassphrase(this.passphrase)
                    .setExpirationDate(this.dateExp)
                    .build();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String keyId = PGPainless.inspectKeyRing(secretKeyRing).getFingerprint().prettyPrint();
        String keyFileName = userName + " " + keyId +".pgp";
        
        //TODO do this in file writer class
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\" + keyFileName);
            writer.write(PGPainless.asciiArmor(secretKeyRing));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
    public KeyGenerator(String userName, String email, String passphrase, String primKeyType, String subKeyType, String symmAlg, String hashAlg, Date dateExp, boolean isSigningOrEncrypting)
    {
        userId = userName + "<" + email + ">";
        
        if (passphrase != null)
            this.passphrase = Passphrase.fromPassword(passphrase);
        
        this.primaryKeyType = getKeyTypeAndLengthFromString(primKeyType);
        this.symmAlg = SymmetricKeyAlgorithm.valueOf(symmAlg);
        this.hashAlg = HashAlgorithm.fromName(hashAlg);
        this.dateExp = dateExp;
        
        KeySpecBuilder primaryKeySpec;
        
        KeySpecBuilder subKeySpec;
        
        if (isSigningOrEncrypting)
        {
            this.singingKeyType = getKeyTypeAndLengthFromString(subKeyType);
            subKeySpec = KeySpec.getBuilder(
                this.singingKeyType,
                KeyFlag.SIGN_DATA);
            
            
            if (this.primaryKeyType.canEncryptStorage())
            {
                primaryKeySpec = KeySpec.getBuilder(
                        primaryKeyType,
                        KeyFlag.CERTIFY_OTHER, KeyFlag.ENCRYPT_STORAGE, KeyFlag.ENCRYPT_COMMS)
                        .overridePreferredHashAlgorithms(this.hashAlg)
                        .overridePreferredSymmetricKeyAlgorithms(this.symmAlg);
            }
            else
            {
                primaryKeySpec = KeySpec.getBuilder(
                        primaryKeyType,
                        KeyFlag.CERTIFY_OTHER)
                        .overridePreferredHashAlgorithms(this.hashAlg)
                        .overridePreferredSymmetricKeyAlgorithms(this.symmAlg);
            }
        }
        else {
            this.encKeyType = getKeyTypeAndLengthFromStringEncryptionKey(subKeyType);
            subKeySpec = KeySpec.getBuilder(
                this.encKeyType,
                KeyFlag.ENCRYPT_COMMS,
                KeyFlag.ENCRYPT_STORAGE);
            
            primaryKeySpec = KeySpec.getBuilder(
                primaryKeyType,
                KeyFlag.CERTIFY_OTHER, KeyFlag.SIGN_DATA)
                .overridePreferredHashAlgorithms(this.hashAlg)
                .overridePreferredSymmetricKeyAlgorithms(this.symmAlg);
        }
        
        
                
        try {
            secretKeyRing = PGPainless.buildKeyRing()
                    .setPrimaryKey(primaryKeySpec)
                    .addSubkey(subKeySpec)
                    .addUserId(userId)
                    .setPassphrase(this.passphrase)
                    .setExpirationDate(this.dateExp)
                    .build();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String keyId = PGPainless.inspectKeyRing(secretKeyRing).getFingerprint().prettyPrint();
        String keyFileName = userName + " " + keyId +".pgp";
        
        //TODO do this in file writer class
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\" + keyFileName);
            writer.write(PGPainless.asciiArmor(secretKeyRing));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
    public KeyGenerator(String path)
    {
        String strPrivKeyRead = null;
        try 
        {
            strPrivKeyRead = Files.readString(Path.of(path));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        try {
            secretKeyRing = PGPainless.readKeyRing().secretKeyRing(strPrivKeyRead);
        } catch (IOException ex) {
            Logger.getLogger(PrivateKeyLocalizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String keyId = PGPainless.inspectKeyRing(secretKeyRing).getFingerprint().prettyPrint();
        String keyFileName = PGPainless.inspectKeyRing(secretKeyRing).getPrimaryUserId().replace('<', '-').replace('>', '-') + " " + keyId +".pgp";
        
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\" + keyFileName);
            writer.write(PGPainless.asciiArmor(secretKeyRing));
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    private KeyType getKeyTypeAndLengthFromString(String stringKeyType)
    {
        KeyType keyType = null;
        
        switch (stringKeyType)
        {
            case "3072" -> keyType = KeyType.RSA(RsaLength._3072);
            case "4096" -> keyType = KeyType.RSA(RsaLength._4096);
            case "8192" -> keyType = KeyType.RSA(RsaLength._8192);
            case "P521" -> keyType = KeyType.ECDSA(EllipticCurve._P521);
            case "P384" -> keyType = KeyType.ECDSA(EllipticCurve._P384);
            case "P256" -> keyType = KeyType.ECDSA(EllipticCurve._P256);
            case "SECP256K1" -> keyType = KeyType.ECDSA(EllipticCurve._SECP256K1);
            case "BRAINPOOLP256R1" -> keyType = KeyType.ECDSA(EllipticCurve._BRAINPOOLP256R1);
            case "BRAINPOOLP384R1" -> keyType = KeyType.ECDSA(EllipticCurve._BRAINPOOLP384R1);
            case "BRAINPOOLP512R1" -> keyType = KeyType.ECDSA(EllipticCurve._BRAINPOOLP512R1);
            case "Ed25519" -> keyType = KeyType.EDDSA(EdDSACurve._Ed25519);
        }
        
        return keyType;
    }

    private KeyType getKeyTypeAndLengthFromStringEncryptionKey(String stringKeyType)
    {
        KeyType keyType = null;
        
        switch (stringKeyType)
        {
            case "3072" -> keyType = KeyType.RSA(RsaLength._3072);
            case "4096" -> keyType = KeyType.RSA(RsaLength._4096);
            case "8192" -> keyType = KeyType.RSA(RsaLength._8192);
            case "P521" -> keyType = KeyType.ECDH(EllipticCurve._P521);
            case "P384" -> keyType = KeyType.ECDH(EllipticCurve._P384);
            case "P256" -> keyType = KeyType.ECDH(EllipticCurve._P256);
            case "SECP256K1" -> keyType = KeyType.ECDH(EllipticCurve._SECP256K1);
            case "BRAINPOOLP256R1" -> keyType = KeyType.ECDH(EllipticCurve._BRAINPOOLP256R1);
            case "BRAINPOOLP384R1" -> keyType = KeyType.ECDH(EllipticCurve._BRAINPOOLP384R1);
            case "BRAINPOOLP512R1" -> keyType = KeyType.ECDH(EllipticCurve._BRAINPOOLP512R1);
        }
        
        return keyType;
    }    
}
