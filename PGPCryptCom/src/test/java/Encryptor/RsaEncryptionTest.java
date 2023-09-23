/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Encryptor;

import InputOutput.FileHandler;
import InputOutput.FileManager;
import Model.SenderEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author adipu
 */
import Model.EncryptionAlgorithm;
import Model.GenericEntity;
import Model.ReceiverEntity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import static org.mockito.Mockito.mock;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.KeyFlag;
import org.pgpainless.key.generation.KeySpec;
import org.pgpainless.key.generation.type.rsa.RsaLength;
import org.bouncycastle.openpgp.*;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.*;
import org.pgpainless.key.*;
import org.pgpainless.key.generation.KeySpecBuilder;
import org.pgpainless.key.generation.type.KeyType;
import org.pgpainless.key.generation.type.ecc.EllipticCurve;
import org.pgpainless.key.protection.PasswordBasedSecretKeyRingProtector;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.pgpainless.util.ArmorUtils;
import org.pgpainless.util.DateUtil;
import org.pgpainless.util.Passphrase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.decryption_verification.ConsumerOptions;
import org.pgpainless.decryption_verification.DecryptionStream;
import org.pgpainless.decryption_verification.MessageInspector;
import org.pgpainless.encryption_signing.EncryptionOptions;
import org.pgpainless.encryption_signing.EncryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;
import org.pgpainless.encryption_signing.SigningOptions;
import org.pgpainless.key.generation.type.eddsa.EdDSACurve;
import org.pgpainless.key.generation.type.xdh.XDHSpec;


@TestMethodOrder(OrderAnnotation.class)
public class RsaEncryptionTest {
    
    static SenderEntity senderEntity= new SenderEntity();
    static ReceiverEntity receiverEntity = new ReceiverEntity();

    
    
    public RsaEncryptionTest() {
          //senderEntity = new SenderEntity();
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
//    
//     /**
//     * Test of GetPrivKey method, of class RsaEncryption.
//     */
//    @Test
//    @Order(1)
//    public void testGetPrivKey_SenderEntity() {
//          System.out.println("GetPrivKey");
//          //senderEntity = mock(SenderEntity.cass);senderEntity = new SenderEntity();
//          senderEntity.setName("adi");
//          senderEntity.setEmail("adi@yahoo.com");
//          senderEntity.setEncryptionAlgorithm(EncryptionAlgorithm.RSA);
//          senderEntity.setKeySize(RsaLength._4096);
//          senderEntity.setPassphrase("parola");
//          RsaEncryption instance = new RsaEncryption((GenericEntity)senderEntity);
//          //String expResult = "GetPrivKey";
//          String result = instance.GetPrivKey();
//          senderEntity.setPrivateKey(result);
//          System.out.println(result);
//          assertTrue(!result.isEmpty());
//    }
//    
//    /**
//     * Test of GetPubKey method, of class RsaEncryption.
//     */
//    @Test
//    @Order(2)
//    public void testGetPubKey_SenderEntity() {
//          System.out.println("GetPubKey");
//          //senderEntity = mock(SenderEntity.class);
//          //senderEntity = new SenderEntity();
//          senderEntity.setName("adi");
//          senderEntity.setEmail("adi@yahoo.com");
//          senderEntity.setEncryptionAlgorithm(EncryptionAlgorithm.RSA);
//          senderEntity.setKeySize(RsaLength._4096);
//          senderEntity.setPassphrase("parola");
//          RsaEncryption instance = new RsaEncryption((GenericEntity)senderEntity);
//          String expResult = instance.GetPubKey();
//          String result = instance.GetPubKey();
//          senderEntity.setPublicKey(result);
//          System.out.println(result);
//          assertEquals(expResult, result);
//          FileManager fileManager = new FileManager();
//          fileManager.createSenderKeyFiles(senderEntity);
//    }
//
//    @Test
//    @Order(3)
//    public void testGetPrivKey_RecieverEntity() {
//          System.out.println("GetPrivKey");
//          //senderEntity = mock(SenderEntity.cass);senderEntity = new SenderEntity();
//          receiverEntity.setName("spe");
//          receiverEntity.setEmail("spe@yahoo.com");
//          receiverEntity.setEncryptionAlgorithm(EncryptionAlgorithm.RSA);
//          receiverEntity.setKeySize(RsaLength._4096);
//          receiverEntity.setPassphrase("parola123");
//          RsaEncryption instance = new RsaEncryption((GenericEntity)receiverEntity);
//          //String expResult = "GetPrivKey";
//          String result = instance.GetPrivKey();
//          receiverEntity.setPrivateKey(result);
//          System.out.println(result);
//          assertTrue(!result.isEmpty());
//    }
//    
//    /**
//     * Test of GetPubKey method, of class RsaEncryption.
//     */
//    @Test
//    @Order(4)
//    public void testGetPubKey_RecieverEntity() {
//          System.out.println("GetPubKey");
//          //senderEntity = mock(SenderEntity.class);
//          //senderEntity = new SenderEntity();
//          receiverEntity.setName("spe");
//          receiverEntity.setEmail("spe@yahoo.com");
//          receiverEntity.setEncryptionAlgorithm(EncryptionAlgorithm.RSA);
//          receiverEntity.setKeySize(RsaLength._4096);
//          receiverEntity.setPassphrase("parola123");
//          RsaEncryption instance = new RsaEncryption((GenericEntity)receiverEntity);
//          String expResult = instance.GetPubKey();
//          String result = instance.GetPubKey();
//          receiverEntity.setPublicKey(result);
//          System.out.println(result);
//          assertEquals(expResult, result);
//          FileManager fileManager = new FileManager();
//          fileManager.createReceiverKeyFiles(receiverEntity);
//    }
//
//    
//    /*
//    scenario:
//    precondition
//    1. sender and receiver has pub and ppk files 2x + 2x
//    action
//    1. sender encrypts txt file with pub key of the receiver
//    2. receiver decrypts txt file with his ppk file 
//    3. we assert on expected received message
//    */
//    @Test
//    @Order(5)
//    public void Encrypt() {
//          System.out.println("Encrypt");
//          String path = "C:\\PGPCryptCom\\Messages";
//          String name = "spe.txt";          
//          FileHandler fh = new FileHandler();
//          var message = fh.readFromFile(path, name);
//          System.out.println(message);
//          //action 1
//          var enc = new RsaEncryption(receiverEntity);
//          String encryptedMsg = enc.Encrypt(message);
//          FileManager fm = new FileManager();
//          fm.createEncryptedMessage(encryptedMsg, name);
//          System.out.println(encryptedMsg);
//    }
//    
//    
//
//    @Test
//    @Order(6)
//    public void Decrypt() {
//          System.out.println("Decrypt");
//          String path = "C:\\PGPCryptCom\\Messages";
//          String name = "spe.txt.pgp";          
//          FileHandler fh = new FileHandler();
//          var message = fh.readFromFile(path, name);
//          System.out.println(message);
//          //action 1
//          var enc = new RsaEncryption(receiverEntity);
//          String plaintextMessage = "";
//        try {
//            plaintextMessage = enc.Decrypt(message);
//        } catch (PGPException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          FileManager fm = new FileManager();
//          fm.createPlaintextMessage(plaintextMessage, name);
//          System.out.println(plaintextMessage);
//    }
//    
//    /*
//    scenario:
//    precondition
//    1. sender and receiver has pub and ppk files 2x + 2x
//    action
//    1. sender encrypts txt file with pub key of the receiver
//    2. and signs with sender priv key *
//    3. receiver verifies key with sender public key *
//    4. receiver decrypts txt file with his ppk file 
//    5. we assert on expected received message
//    */
//    @Test
//    @Order(7)
//    public void SignAndEncrypt() {
//          System.out.println("SignAndEncrypt");
////          String path = "C:\\PGPCryptCom\\Messages";
////          String name = "spe.txt";
////          FileHandler fh = new FileHandler();
////          var message = fh.readFromFile(path, name);
////          System.out.println(message);
////          //action 1
////          var enc = new RsaEncryption(receiverEntity);
////          String encryptedMsg = enc.Encrypt(message);
////          FileManager fm = new FileManager();
////          fm.createEncryptedMessage(encryptedMsg, name);
////          System.out.println(encryptedMsg);
//
//          String path = "C:\\PGPCryptCom\\Messages";
//          String name = "spe.txt";          
//          FileHandler fh = new FileHandler();
//          var message = fh.readFromFile(path, name);
//          System.out.println(message);
//          //action 1
//          var enc = new RsaEncryption(receiverEntity);
//          String encryptedMsg = enc.SignAndEncrypt(message, senderEntity);
//          FileManager fm = new FileManager();
//          fm.createEncryptedAndSignedMessage(encryptedMsg, name);
//          System.out.println(encryptedMsg);
//    }
//    
//    
//    @Test
//    @Order(8)
//    public void DecryptAndVerify() {
//          System.out.println("DecryptAndVerify");
//          String path = "C:\\PGPCryptCom\\Messages";
//          String name = "signed_spe.txt.pgp";          
//          FileHandler fh = new FileHandler();
//          var message = fh.readFromFile(path, name);
//          System.out.println(message);
//          //action 1
//          var enc = new RsaEncryption(receiverEntity);
//          String plaintextMessage = "";
//        try {
//            plaintextMessage = enc.DecryptAndVerify(message, senderEntity);
//        } catch (PGPException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          FileManager fm = new FileManager();
//          fm.createPlaintextVerifiedMessage(plaintextMessage, name);
//          System.out.println(plaintextMessage);
//    }
//    
    //@Test
    //@Order(1)
    public void generatePGPKeyPair() throws PGPException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IOException {

//         public static void generatePGPKeyPair(String userId, char[] passPhrase) throws PGPException {
//
//        // Generate a secure random number generator
//        SecureRandom random = new SecureRandom();
//
//        // Generate the master keypair
//        PGPKeyPair masterKeyPair = PGPainless.generateKeyRing()
//                .setPrimaryKey(KeySpec.getBuilder(KeyType.RSA(RSAKeyLength._4096), KeyFlag.CERTIFY_OTHER, KeyFlag.SIGN_DATA)
//                        .withDefaultKeyPreferences()
//                        .withPassphrase(Passphrase.fromPassword(passPhrase))
//                        .withKeyExpirationInDays(365)
//                        .build())
//                .setUserId(userId)
//                .generate();
//
//        // Generate the subkeypair
//        PGPKeyPair subKeyPair = PGPainless.generateKeyRing()
//                .setSubKey(KeySpec.getBuilder(KeyType.RSA(RSAKeyLength._4096), KeyFlag.ENCRYPT_COMMS)
//                        .withDefaultKeyPreferences()
//                        .withPassphrase(Passphrase.fromPassword(passPhrase))
//                        .withKeyExpirationInDays(365)
//                        .build())
//                .setUserId(userId)
//                .generateSubKeyPair(masterKeyPair);
//
//        // Build the keyring
//        PGPSecretKeyRing secretKeyRing = PGPainless.generateKeyRing()
//                .addKeys(masterKeyPair.getSecretKey(), subKeyPair.getSecretKey())
//                .build();
//
//        // Save the keyring to a file
//        PGPainless.writeKeyRing()
//                .secretKeyRing(secretKeyRing)
//                .toFile("myKeyRing.asc");
//    }
        System.out.println("generatePGPKeyPair");
        
        // Generate a secure random number generator

        
                // Specification for primary key
        KeySpecBuilder primaryKeySpec = KeySpec.getBuilder(
                KeyType.RSA(RsaLength._8192),                               // 8192 bits RSA key
                KeyFlag.CERTIFY_OTHER)                                      // used for certification
                // optionally override algorithm preferences
                .overridePreferredCompressionAlgorithms(CompressionAlgorithm.ZLIB)
                .overridePreferredHashAlgorithms(HashAlgorithm.SHA512, HashAlgorithm.SHA384)
                .overridePreferredSymmetricKeyAlgorithms(SymmetricKeyAlgorithm.requireFromId(SymmetricKeyAlgorithmTags.AES_256));
//
//        KeySpecBuilder testKeySpec = KeySpec.getBuilder(
//                KeyType.EDDSA(EdDSACurve._Ed25519),
//                KeyFlag.ENCRYPT_COMMS, KeyFlag.ENCRYPT_STORAGE, KeyFlag.CERTIFY_OTHER)
//                .overridePreferredSymmetricKeyAlgorithms(SymmetricKeyAlgorithm.fromId(SymmetricKeyAlgorithmTags.AES_256))
//                .overridePreferredCompressionAlgorithms(CompressionAlgorithm.ZIP)
//                .overridePreferredHashAlgorithms(HashAlgorithm.SHA512);
//        
//        PGPSecretKeyRing testSecretKeyRing = PGPainless.buildKeyRing()
//                .setPrimaryKey(testKeySpec)
//                .build();
//                
//        
        // Specification for a signing subkey
        KeySpecBuilder signingSubKeySpec = KeySpec.getBuilder(
                KeyType.ECDSA(EllipticCurve._P256),                         // P-256 ECDSA key
                KeyFlag.SIGN_DATA);                                         // Used for signing

        // Specification for an encryption subkey
        KeySpecBuilder encryptionSubKeySpec = KeySpec.getBuilder(
                KeyType.ECDH(EllipticCurve._P256),
                KeyFlag.ENCRYPT_COMMS, KeyFlag.ENCRYPT_STORAGE);

        // Build the key itself
        PGPSecretKeyRing secretKey = PGPainless.buildKeyRing()
                .setPrimaryKey(primaryKeySpec)
                .addSubkey(signingSubKeySpec)
                .addSubkey(encryptionSubKeySpec)
                .addUserId("Juliet <juliet@montague.lit>")                  // Primary User-ID
                .addUserId("xmpp:juliet@capulet.lit")                       // Additional User-ID
                .setPassphrase(Passphrase.fromPassword("romeo_oh_Romeo<3")) // passphrase protection
                .build();
        
        //Write key to file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("C:\\PGPCryptCom\\Keys\\PrivKeys\\julieta.pgp"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArmoredOutputStream aos = ArmorUtils.toAsciiArmoredStream(secretKey, fos);
        secretKey.encode(aos);
        
        
                
        
        
        aos.close();
        fos.close();
        
        
        String strprivKeyMaster = PGPainless.asciiArmor(secretKey);
        
        //var binStream = new ByteArrayOutputStream();
        
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietatest.pgp");
            writer.write(strprivKeyMaster);
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        
        
         //Write key to file
        FileOutputStream fos1 = null;
        try {
            fos1 = new FileOutputStream(new File("C:\\PGPCryptCom\\Keys\\PrivKeys\\julieta1.pgp"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                
        var keys = secretKey.getSecretKeys();
        
        var key0 = keys.next();
        var key1 = keys.next();
        var key2 = keys.next();
        //var key3 = keys.next();
        
        //ArmorUtils.toAsciiArmoredString(bytes)
        
        //aos1.close();
        
//        
        OpenPgpV4Fingerprint fgp = new OpenPgpV4Fingerprint(secretKey.getPublicKey().getFingerprint());
        OpenPgpV4Fingerprint fgp1 = new OpenPgpV4Fingerprint(key1.getPublicKey().getFingerprint());
        OpenPgpV4Fingerprint fgp2 = new OpenPgpV4Fingerprint(key2.getPublicKey().getFingerprint());
        
        
        System.out.println(fgp.toString());
        System.out.println(fgp1.toString());
        System.out.println(fgp2.toString());

        //System.out.println(new String(secretKey.getSecretKey().getPublicKey().getFingerprint(), Charset.forName("UTF-8")));

        //System.out.println(new String(key0.getPublicKey().getFingerprint(), Charset.forName("UTF-8")));

        //System.out.println(key3.getKeyID());

        //PGPSecretKey subKey = secretKey.getSecretKeys().next();
        var strSubKey1 = ArmorUtils.toAsciiArmoredString(key1);
        
        fos1.write(strSubKey1.getBytes());
        
        //ArmoredOutputStream aos1 = ArmorUtils.toAsciiArmoredStream(subKey, fos);
        
        
        //secretKey.encode(aos);
        
        //aos1.close();
        fos1.close();
        
        
        
        
        var pubKeys = secretKey.getPublicKeys();
        
        var pubKeyMaster = pubKeys.next();
        var pubKeySub1 = pubKeys.next();
        var pubKeySub2 = pubKeys.next();
        
        
        //TODO: Easier way to write to files::::!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        var pubKeyRingMaster = PGPainless.extractCertificate(secretKey);
        
        String strPubKeyMaster = PGPainless.asciiArmor(pubKeyRingMaster);
        
        //var binStream = new ByteArrayOutputStream();
        
        try {
            FileWriter writer = new FileWriter("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietaPubMaster.pgp");
            writer.write(strPubKeyMaster);
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
//        
//        FileOutputStream fosPub0 = null;
//        try {
//            fosPub0 = new FileOutputStream(new File("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietapub0.pgp"));
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        FileOutputStream fosPub1 = null;
//        try {
//            fosPub1 = new FileOutputStream(new File("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietapub1.pgp"));
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        FileOutputStream fosPub2 = null;
//        try {
//            fosPub2 = new FileOutputStream(new File("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietapub2.pgp"));
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(RsaEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        ArmoredOutputStream aosPub0 = new ArmoredOutputStream(fosPub0);
//        ArmoredOutputStream aosPub1 = new ArmoredOutputStream(fosPub1);
//        ArmoredOutputStream aosPub2 = new ArmoredOutputStream(fosPub2);
//
//        pubKeyMaster.encode(aosPub0, true);
//        pubKeySub1.encode(aosPub1, true);
//        pubKeySub2.encode(aosPub2, true);
//
//        
//        aosPub0.close();
//        aosPub1.close();
//        aosPub2.close();
//
//        fosPub0.close();
//        fosPub1.close();
//        fosPub2.close();
        
        //Readkey:
        
        String strPrivKeyRead = "";
        try 
        {
            strPrivKeyRead = Files.readString(Path.of("C:\\PGPCryptCom\\Keys\\PrivKeys\\julieta.pgp"));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
        }
        
        var secretKeyRead = PGPainless.readKeyRing().secretKeyRing(strPrivKeyRead);
        String strSecKeyRead = PGPainless.asciiArmor(secretKeyRead);
        
        assertEquals(strprivKeyMaster, strSecKeyRead);
        
        //read and test public key:
        
         
        String strPubKeyRead = "";
        try 
        {
            strPubKeyRead = Files.readString(Path.of("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietaPubMaster.pgp"));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
        }
        
        var pubKeyRead = PGPainless.readKeyRing().publicKeyRing(strPubKeyRead);
        String strPubKeyReadGenerated = PGPainless.asciiArmor(pubKeyRead);
        
        assertEquals(strPubKeyRead, strPubKeyReadGenerated);
        

    }
    
    
    @Test
    @Order(2)
    public void ModifyKey() throws IOException, PGPException, InvalidAlgorithmParameterException, NoSuchAlgorithmException
    {        
        // add expiration date
        
        System.out.println("ModifyKey:");
        String strKeyRead = "";
        try 
        {
            strKeyRead = Files.readString(Path.of("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietaREAD.pgp"));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
            
        }
        
        var initialKey = PGPainless.readKeyRing().secretKeyRing(strKeyRead);
        var keyInfo = PGPainless.inspectKeyRing(initialKey);
        
        assertNull(keyInfo.getPrimaryKeyExpirationDate());

        Date now = new Date();
        Date date = DateUtil.parseUTCDate("2023-11-22 15:00:00 UTC");
        
        
        var keyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase.fromPassword("romeo_oh_Romeo<3"), initialKey);
        
        var keyWithExpDate = PGPainless.modifyKeyRing(initialKey).setExpirationDate(date, keyProtector).done();
        
        var keyInfoWithExpDate = PGPainless.inspectKeyRing(keyWithExpDate);
        
        assertNotNull(keyInfoWithExpDate.getPrimaryKeyExpirationDate());
        
        System.out.println(keyInfoWithExpDate.getPrimaryKeyExpirationDate());
        
                
        //get fingerprints
        
        var keys = keyWithExpDate.getPublicKeys();
        keys.next();
        
        OpenPgpV4Fingerprint fgp1 = new OpenPgpV4Fingerprint(keys.next().getFingerprint());
        OpenPgpV4Fingerprint fgp2 = new OpenPgpV4Fingerprint(keys.next().getFingerprint());
        
        assertNull(keyInfoWithExpDate.getSubkeyExpirationDate(fgp1));
        
        
        //remove date
        
        var keyWithoutExpDate = PGPainless.modifyKeyRing(keyWithExpDate).setExpirationDate(null, keyProtector).done();
        
        var keyInfoWithoutExpDate = PGPainless.inspectKeyRing(keyWithoutExpDate);
        assertNull(keyInfoWithoutExpDate.getPrimaryKeyExpirationDate());
        
        //userids
        
        System.out.println("initial key: " + PGPainless.inspectKeyRing(initialKey).getFingerprint().prettyPrint());
        
        var keyWithoutUserId = PGPainless.modifyKeyRing(initialKey).removeUserId("xmpp:juliet@capulet.lit", keyProtector).done();
        
        var keyInfoWoUserId = PGPainless.inspectKeyRing(keyWithoutUserId);
        
        System.out.println(keyInfoWoUserId.isUserIdValid("xmpp:juliet@capulet.lit"));
        System.out.println(keyInfoWoUserId.isUserIdValid("Juliet <juliet@montague.lit>")); //TODO: Check this in app
        
        var keyWRevokedUser = PGPainless.modifyKeyRing(initialKey).revokeUserId("xmpp:juliet@capulet.lit", keyProtector).done();
        
        var infoRevokedUser = PGPainless.inspectKeyRing(keyWRevokedUser);
        
        var keyWaddUser = PGPainless.modifyKeyRing(initialKey).addUserId("adi 123", keyProtector).done();

        
        System.out.println("modified key: " + PGPainless.inspectKeyRing(keyWaddUser).getFingerprint().prettyPrint());

        
        System.out.println(infoRevokedUser.isUserIdValid("xmpp:juliet@capulet.lit"));
        System.out.println(infoRevokedUser.getUserIds());
        
        
        
        //passphrase
        
        var keyWithDiffPass = PGPainless.modifyKeyRing(initialKey).changePassphraseFromOldPassphrase(Passphrase.fromPassword("romeo_oh_Romeo<3")).withSecureDefaultSettings().toNewPassphrase(Passphrase.fromPassword("1234")).done();
        var newKeyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase.fromPassword("1234"), keyWithDiffPass);
        
        //test new protector
        try{
            PGPainless.modifyKeyRing(keyWithDiffPass).setExpirationDate(date, keyProtector).done();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        PGPainless.modifyKeyRing(keyWithDiffPass).setExpirationDate(date, newKeyProtector).done();
        
        
        //remove subkey
        
        var keyWSubkeyRevoked = PGPainless.modifyKeyRing(initialKey).revokeSubKey(fgp1, keyProtector).done();
        
        var keyInfoWSubkeyRevoked = PGPainless.inspectKeyRing(keyWSubkeyRevoked);
        
        var keysIterator = keyWSubkeyRevoked.getSecretKeys();
        keysIterator.next();
        var subkeyRevoked = keysIterator.next();
        
        assertTrue(subkeyRevoked.getPublicKey().hasRevocation());
        
        //add subkey
        var signingSubKeySpec = KeySpec.getBuilder(
                KeyType.RSA(RsaLength._4096),
                KeyFlag.SIGN_DATA).build();
        
        
        var keyWSubkeyAdded = PGPainless.modifyKeyRing(keyWSubkeyRevoked).addSubKey(signingSubKeySpec,
                Passphrase.fromPassword("romeo_oh_Romeo<3"),
            SecretKeyRingProtector.unlockSingleKeyWith(Passphrase.fromPassword("romeo_oh_Romeo<3"), keyWSubkeyRevoked.getSecretKey())).done();
        
        
        var keysIterator2 = keyWSubkeyAdded.getSecretKeys();
        
        
        keysIterator2.next();
        var subkey1 = keysIterator2.next();
        var subkey2 = keysIterator2.next();
        var subkey3 = keysIterator2.next();
        
        assertFalse(subkey2.getPublicKey().hasRevocation()); 
        
        System.out.println("Initial:");
        List<OpenPgpV4Fingerprint> keyFgpsInitial = printFingerprintsOfKey(initialKey);
        
        
        System.out.println("After revoke:");
        List<OpenPgpV4Fingerprint> keyFgpsAfterRevoke = printFingerprintsOfKey(keyWSubkeyRevoked);
        
        System.out.println("After add:");
        List<OpenPgpV4Fingerprint> keyFgpsAfterAdd = printFingerprintsOfKey(keyWSubkeyAdded);
        
        
    }
    
    public List<OpenPgpV4Fingerprint> printFingerprintsOfKey(PGPSecretKeyRing keyRing)
    {
        List<OpenPgpV4Fingerprint> keyFgps = new ArrayList<>();
        for (Iterator<PGPPublicKey> it = keyRing.getPublicKeys(); it.hasNext(); ) {
            var fgp = new OpenPgpV4Fingerprint(it.next());
            System.out.println(fgp);
        }
        return keyFgps;
    }
    
    @Test
    @Order(3)
    public void signAndEncrypt() throws IOException, PGPException
    {
        System.out.println("Sign and ecnrypt:");
        String strKeyRead = "";
        try 
        {
            strKeyRead = Files.readString(Path.of("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietaREAD.pgp"));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
            
        }
        
        String strKeyRead2 = "";
        try 
        {
            strKeyRead2 = Files.readString(Path.of("C:\\PGPCryptCom\\Keys\\PrivKeys\\julietatest.pgp"));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
            
        }
                
        var privKey = PGPainless.readKeyRing().secretKeyRing(strKeyRead);
        var privKey2 = PGPainless.readKeyRing().secretKeyRing(strKeyRead2);

        var privKeyInfo = PGPainless.inspectKeyRing(privKey);
        
        String plaintext = new String();
        
        try 
        {
            Path fileName = Path.of("C:\\PGPCryptCom\\Messages\\message.txt");
            plaintext = Files.readString(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        var keyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase.fromPassword("romeo_oh_Romeo<3"), privKey);
        
        
        //sign
        
        InputStream messageIn = new ByteArrayInputStream(plaintext.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream signedOut = new ByteArrayOutputStream();
        EncryptionStream signingStream = PGPainless.encryptAndOrSign()
                .onOutputStream(signedOut)
                .withOptions(ProducerOptions.sign(SigningOptions.get()
                                .addInlineSignature(keyProtector, privKey, privKeyInfo.getPrimaryUserId(), DocumentSignatureType.BINARY_DOCUMENT))
                .setAsciiArmor(true)
                );
        
        Streams.pipeAll(messageIn, signingStream);
        signingStream.close();
        
        String signedMessage = signedOut.toString();
         
        System.out.println("Signed message:");

        System.out.println(signedMessage);
         
         
         
        // Encrypt
        
        var pubKeyRing = PGPainless.extractCertificate(privKey);
        
        ByteArrayOutputStream ciphertext = new ByteArrayOutputStream();
        EncryptionStream encryptor = PGPainless.encryptAndOrSign()
                .onOutputStream(ciphertext)
                .withOptions(ProducerOptions.encrypt(
                                // we want to encrypt communication (affects key selection based on key flags)
                          EncryptionOptions.encryptCommunications()
                                        .addRecipient(pubKeyRing)
                        ).setAsciiArmor(true)
                );

        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plaintext.getBytes(StandardCharsets.UTF_8)), encryptor);
        encryptor.close();
        String encryptedMessage = ciphertext.toString();
        
        System.out.println("encrypted:");
        System.out.println(encryptedMessage);
        
        
        // sign and encrypt:
        
        ByteArrayOutputStream cipherSignedext = new ByteArrayOutputStream();
        EncryptionStream encryptorNSigner = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherSignedext)
                .withOptions(ProducerOptions.signAndEncrypt(
                                // we want to encrypt communication (affects key selection based on key flags)
                        EncryptionOptions.encryptCommunications()
                                        .addRecipient(pubKeyRing),
                                      new SigningOptions().addInlineSignature(keyProtector, privKey, DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)
                        ).setAsciiArmor(true)
                );

        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plaintext.getBytes(StandardCharsets.UTF_8)), encryptorNSigner);
        encryptorNSigner.close();
        String encryptedNSignedMessage = cipherSignedext.toString();
        
        System.out.println("encrypted and signed:");
        System.out.println(encryptedNSignedMessage);
        
        
        
        

        
        //verify
        
        DecryptionStream verifier = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(signedMessage.getBytes(StandardCharsets.UTF_8)))
                .withOptions(new ConsumerOptions()
                        .addVerificationCert(PGPainless.extractCertificate(privKey))
                );

        ByteArrayOutputStream verifiedText = new ByteArrayOutputStream();

        Streams.pipeAll(verifier, verifiedText);
        verifier.close();

        String verifiedMessage = verifiedText.toString();
        
        System.out.println("Verified message: ");
        System.out.println(verifiedMessage);

        assertEquals(plaintext, verifiedMessage);
       
        //decrypt
        
        DecryptionStream decryptor = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(encryptedMessage.getBytes(StandardCharsets.UTF_8)))
                .withOptions(new ConsumerOptions()
                        .addDecryptionKey(privKey, keyProtector)
                );

        ByteArrayOutputStream decryptedText = new ByteArrayOutputStream();

        Streams.pipeAll(decryptor, decryptedText);
        decryptor.close();

        String decryptedMessage = decryptedText.toString();
        
        System.out.println("decrypted message: ");
        System.out.println(decryptedMessage);

        assertEquals(plaintext, decryptedMessage);
        
        //decrypt and verify
        
        DecryptionStream decryptorAndVerifier = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(encryptedNSignedMessage.getBytes(StandardCharsets.UTF_8)))
                .withOptions(new ConsumerOptions()
                        .addDecryptionKey(privKey, keyProtector)
                        .addVerificationCert(pubKeyRing)
                );

        ByteArrayOutputStream decryptedAndVerifiedText = new ByteArrayOutputStream();

        Streams.pipeAll(decryptorAndVerifier, decryptedAndVerifiedText);
        decryptor.close();

        String decryptedAndVerifiedMessage = decryptedAndVerifiedText.toString();
        
        System.out.println("decrypted and verified message: ");
        System.out.println(decryptedAndVerifiedMessage);

        assertEquals(plaintext, decryptedAndVerifiedMessage);
        
        //test with non-text files
        
        System.out.println("Read image:");
        
        byte[] plainImgBytes = null;
                        
        try 
        {
            Path fileName = Path.of("C:\\PGPCryptCom\\Messages\\test video.mp4");
            plainImgBytes = Files.readAllBytes(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
                        
        //sign img
        
        InputStream imgIn = new ByteArrayInputStream(plainImgBytes);
        
        ByteArrayOutputStream signedImgOut = new ByteArrayOutputStream();
        
        EncryptionStream signingImgStream = PGPainless.encryptAndOrSign()
                .onOutputStream(signedImgOut)
                .withOptions(ProducerOptions.sign(SigningOptions.get()
                                .addInlineSignature(keyProtector, privKey, DocumentSignatureType.BINARY_DOCUMENT))
                        .setAsciiArmor(false)
                );
        
        Streams.pipeAll(imgIn, signingImgStream);
        signingImgStream.close();
        
        var signedImgBytes = signedImgOut.toByteArray();
                
        //ver img
        
        DecryptionStream verifierImg = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(signedImgBytes))
                .withOptions(new ConsumerOptions()
                        .addVerificationCert(pubKeyRing)
                        
                );

        ByteArrayOutputStream verifiedImg = new ByteArrayOutputStream();

        Streams.pipeAll(verifierImg, verifiedImg);
        verifierImg.close();

        var verifiedImgBytes = verifiedImg.toByteArray();
        

        //assertEquals(plainImgBytes, verifiedImgBytes);
        
        //*easiest way for file writing
        try {
            FileUtils.writeByteArrayToFile(new File("C:\\PGPCryptCom\\Messages\\test videoVerified.mp4"), verifiedImgBytes);
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        // encrypt img
                
        ByteArrayOutputStream cipherImg = new ByteArrayOutputStream();
        EncryptionStream encryptorImg = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherImg)
                .withOptions(ProducerOptions.encrypt(
                                // we want to encrypt communication (affects key selection based on key flags)
                          EncryptionOptions.encryptCommunications()
                                        .addRecipient(pubKeyRing)
                        ).setAsciiArmor(true)
                );
        
        
        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plainImgBytes), encryptorImg);
        encryptorImg.close();
        byte[] ecnryptedImgBytes = cipherImg.toByteArray();
        
        System.out.println("encrypted img:");
        
        //decrypt img
        
        DecryptionStream decryptorImg = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(ecnryptedImgBytes))
                .withOptions(new ConsumerOptions()
                        .addDecryptionKey(privKey, keyProtector)
                );

        ByteArrayOutputStream decryptedImg = new ByteArrayOutputStream();

        Streams.pipeAll(decryptorImg, decryptedImg);
        decryptorImg.close();
        
        byte[] decryptedImgBytes = decryptedImg.toByteArray();
        
        //assertEquals(plainImgBytes, decryptedImgBytes);
        
        try {
            FileUtils.writeByteArrayToFile(new File("C:\\PGPCryptCom\\Messages\\test videoDecrypted.mp4"), decryptedImgBytes);
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        //encrypt and sign image
        
        ByteArrayOutputStream cipherSignedImg = new ByteArrayOutputStream();
        EncryptionStream encryptorNSignerImg = PGPainless.encryptAndOrSign()
                .onOutputStream(cipherSignedImg)
                .withOptions(ProducerOptions.signAndEncrypt(
                                // we want to encrypt communication (affects key selection based on key flags)
                        EncryptionOptions.encryptCommunications()
                                        .addRecipient(pubKeyRing),
                                      new SigningOptions().addInlineSignature(keyProtector, privKey, DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)
                        ).setAsciiArmor(true)
                );

        // Pipe data trough and CLOSE the stream (important)
        Streams.pipeAll(new ByteArrayInputStream(plainImgBytes), encryptorNSignerImg);
        encryptorNSignerImg.close();
        
        byte[] cipherSignedImgBytes = cipherSignedImg.toByteArray();
        
        //decrypt and verify image
        
        DecryptionStream decryptorAndVerifierImg = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(cipherSignedImgBytes))
                .withOptions(new ConsumerOptions()
                        .addDecryptionKey(privKey, keyProtector)
                        .addVerificationCert(pubKeyRing)
                );

        ByteArrayOutputStream decryptedAndVerifiedImg = new ByteArrayOutputStream();

        Streams.pipeAll(decryptorAndVerifierImg, decryptedAndVerifiedImg);
        decryptorAndVerifierImg.close();

        byte[] decryptedAndVerifiedImgBytes = decryptedAndVerifiedImg.toByteArray();
        
        try {
            FileUtils.writeByteArrayToFile(new File("C:\\PGPCryptCom\\Messages\\test videoDecryptedVerified.mp4"), decryptedAndVerifiedImgBytes);
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        // NEW TESTS
        System.out.println("New tests: ");
        DecryptionStream decryptorImg2 = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(cipherSignedImgBytes))
                .withOptions(new ConsumerOptions()
                        .addDecryptionKey(privKey, keyProtector)
                        .addVerificationCert(pubKeyRing)
                );

        ByteArrayOutputStream decryptedImg2 = new ByteArrayOutputStream();

        Streams.pipeAll(decryptorImg2, decryptedImg2);
        
        decryptorImg2.close();
        
        
       


        
        
        byte[] decryptedImgBytes2 = decryptedImg2.toByteArray();
        
        var metadata = decryptorImg2.getMetadata();
        System.out.println(metadata.isVerifiedInlineSignedBy(pubKeyRing));
        System.out.println(metadata.isVerifiedInlineSignedBy(privKey));
        System.out.println(metadata.isVerifiedInlineSignedBy(privKey2));
        System.out.println(metadata.isEncrypted());
        System.out.println(metadata.isVerifiedSigned());
        
        ByteArrayInputStream inputAfterDec = new ByteArrayInputStream(decryptedImgBytes2);
        
        var info = MessageInspector.determineEncryptionInfoForMessage(new ByteArrayInputStream(cipherSignedImgBytes));
        
        System.out.println("----");
        
        System.out.println(info.getKeyIds().size());
        System.out.println(info.getKeyIds().get(0));
        System.out.println(info.isEncrypted());
        System.out.println(info.isPassphraseEncrypted());
        System.out.println("is signed?" + info.isSignedOnly());
        
        var pubkeys = privKeyInfo.getPublicKeys();
        for (var pubK : pubkeys)
        {
            System.out.println(pubK.getKeyID());
        }
        
        try {
            FileUtils.writeByteArrayToFile(new File("C:\\PGPCryptCom\\Messages\\test videoDecryptedwrong.mp4"), decryptedImgBytes2);
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
}
