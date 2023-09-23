/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.pgpainless.PGPainless;

/**
 *
 * @author adipu
 */
public class PublicKeyLocalizer {
    
    private String path;
    private PGPPublicKeyRing keyRing;
    
    public PublicKeyLocalizer (String path)
    {
        this.path = path;
        
        String strPrivKeyRead = null;
        try 
        {
            strPrivKeyRead = Files.readString(Path.of(path));
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
        }
        
        try {
            this.keyRing = PGPainless.readKeyRing().publicKeyRing(strPrivKeyRead);
        } catch (IOException ex) {
            Logger.getLogger(PrivateKeyLocalizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PGPPublicKeyRing getKeyRing(){
        return keyRing;
    }
    
    public String getName(){
        String name = PGPainless.inspectKeyRing(keyRing).getUserIds().get(0);
        return name;
    }
    
    public Date getCreationDate(){
        Date creationDate = PGPainless.inspectKeyRing(keyRing).getCreationDate();
        return creationDate;
    }
    
    public Date getExpDate(){
        Date expDate = PGPainless.inspectKeyRing(keyRing).getPrimaryKeyExpirationDate();
        return expDate;
    }
    
    public String getKeyId(){
        String keyId = PGPainless.inspectKeyRing(keyRing).getFingerprint().prettyPrint();
        
        return keyId;
    }
    
    public String getPath(){        
        return path;
    }
    
}
