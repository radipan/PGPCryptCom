/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.EncryptionPurpose;
import org.pgpainless.decryption_verification.ConsumerOptions;
import org.pgpainless.decryption_verification.DecryptionStream;
import org.pgpainless.decryption_verification.MessageInspector;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.pgpainless.util.Passphrase;

/**
 *
 * @author adipu
 */
public class Decryptor {
    
    private byte[] data;
    private List<PublicKeyLocalizer> pubKeys;
    private List<PrivateKeyLocalizer> privKeys;
    private MessageInspector.EncryptionInfo info;
    private boolean isVerified;
    private boolean isDataValid;
    
    private PrivateKeyLocalizer keyUsedForVerifyingPriv = null;
    private PublicKeyLocalizer keyUsedForVerifyingPub = null;

    public PrivateKeyLocalizer getKeyUsedForVerifyingPriv() {
        return keyUsedForVerifyingPriv;
    }

    public PublicKeyLocalizer getKeyUsedForVerifyingPub() {
        return keyUsedForVerifyingPub;
    }

    public PrivateKeyLocalizer getKeyUsedForDecrypting() {
        return keyUsedForDecrypting;
    }
    
    private PrivateKeyLocalizer keyUsedForDecrypting = null;

    public Decryptor(byte[] data, List<PublicKeyLocalizer> pubKeys, List<PrivateKeyLocalizer> privKeys) throws IOException
    {
        this.data = data;
        this.privKeys = privKeys;
        this.pubKeys = pubKeys;
        
        try {
            info = MessageInspector.determineEncryptionInfoForMessage(new ByteArrayInputStream(data));
        } catch (PGPException ex) {
            Logger.getLogger(Decryptor.class.getName()).log(Level.SEVERE, null, ex);
            isDataValid = false;
            return;
        }
        
        isDataValid = true;        
        
    }
    
    public boolean isDataSignedOnly()
    {
        return info.isSignedOnly();
    }
    
    public boolean isEncrypted()
    {
        return info.isEncrypted();
    }
    
    public List<Long> getKeyIds()
    {
        return info.getKeyIds();
    }
    
    
    public byte[] tryVerify() throws PGPException, IOException
    {
        for (PublicKeyLocalizer pubKeyInfo : pubKeys)
        {
            DecryptionStream decryptor = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(data))
                .withOptions(new ConsumerOptions()
                        .addVerificationCert(pubKeyInfo.getKeyRing())
                );
            
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Streams.pipeAll(decryptor, output);
            decryptor.close();
            var metadata = decryptor.getMetadata();
            
            
            if (metadata.isVerifiedSigned())
            {
                this.keyUsedForVerifyingPub = pubKeyInfo;
                byte[] outputBytes = output.toByteArray();
                return outputBytes;
            }
        }
        for (PrivateKeyLocalizer privKeyInfo : privKeys)
        {
            DecryptionStream decryptor = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(data))
                .withOptions(new ConsumerOptions()
                        .addVerificationCert(PGPainless.extractCertificate(privKeyInfo.getKeyRing()))
                );
            
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Streams.pipeAll(decryptor, output);
            decryptor.close();
            var metadata = decryptor.getMetadata();
            
            
            if (metadata.isVerifiedSigned())
            {
                this.keyUsedForVerifyingPriv = privKeyInfo;
                byte[] outputBytes = output.toByteArray();
                return outputBytes;
            }
        }
        
        return null;
    }
    
    public boolean findDecryptKey()
    {
        for (PrivateKeyLocalizer privKeyInfo : privKeys)
        {
            for (Long keyId : info.getKeyIds())
            {
                var keyListEnc = PGPainless.inspectKeyRing(privKeyInfo.getKeyRing()).getEncryptionSubkeys(EncryptionPurpose.ANY);
            
                for (var pubKey : keyListEnc)
                {
                    if (keyId == pubKey.getKeyID())
                    {
                        this.keyUsedForDecrypting = privKeyInfo;
                        break;
                    }
                }
                
//                if (keyId == PGPainless.inspectKeyRing(privKeyInfo.getKeyRing()))
//                {
//                    this.keyUsedForDecrypting = privKeyInfo;
//                    break;
//                }
            }
            if (keyUsedForDecrypting != null)
                break;
        }
        return keyUsedForDecrypting != null;
    }
    
    public byte[] TryDecryptAndVerify(char[] passphrase) throws PGPException, IOException
    {
        var key = keyUsedForDecrypting.getKeyRing();
        var keyProtector = SecretKeyRingProtector.unlockEachKeyWith(Passphrase
                .fromPassword(String.copyValueOf(passphrase)), key);
        byte[] outputBytes = null;
        
        for (PublicKeyLocalizer pubKeyInfo : pubKeys)
        {
            DecryptionStream decryptor = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(data))
                .withOptions(new ConsumerOptions()
                        .addVerificationCert(pubKeyInfo.getKeyRing())
                        .addDecryptionKey(key, keyProtector)
                );
            
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Streams.pipeAll(decryptor, output);
            decryptor.close();
            var metadata = decryptor.getMetadata();
            
            
            outputBytes = output.toByteArray();
            if (metadata.isVerifiedSigned())
            {
                this.keyUsedForVerifyingPub = pubKeyInfo;
                return outputBytes;
            }
        }
        for (PrivateKeyLocalizer privKeyInfo : privKeys)
        {
            DecryptionStream decryptor = PGPainless.decryptAndOrVerify()
                .onInputStream(new ByteArrayInputStream(data))
                .withOptions(new ConsumerOptions()
                        .addVerificationCert(PGPainless.extractCertificate(privKeyInfo.getKeyRing()))
                        .addDecryptionKey(key, keyProtector)
                );
            
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Streams.pipeAll(decryptor, output);
            decryptor.close();
            var metadata = decryptor.getMetadata();
            
            outputBytes = output.toByteArray();
            if (metadata.isVerifiedSigned())
            {
                this.keyUsedForVerifyingPriv = privKeyInfo;
                return outputBytes;
            }
        }
        
        return outputBytes;
    }
}

