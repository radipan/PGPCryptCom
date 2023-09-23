/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import org.pgpainless.key.generation.type.rsa.RsaLength;

/**
 *
 * @author adipu
 */
public class SenderEntity extends GenericEntity {
    private String Name;
    private String Email;
    private String Passphrase;
    private EncryptionAlgorithm EncryptionAlgorithm;
    private RsaLength KeySize;
    private String PrivateKey;
    private String PublicKey;

    public SenderEntity(String Name, String Email, String Passphrase, EncryptionAlgorithm EncryptionAlgorithm, RsaLength KeySize) {
        this.Name = Name;
        this.Email = Email;
        this.Passphrase = Passphrase;
        this.EncryptionAlgorithm = EncryptionAlgorithm;
        this.KeySize = KeySize;
    }

    public SenderEntity() {
    }

    @Override
    public String toString() {
        return Name + " " + Email + " " + Passphrase + " " + EncryptionAlgorithm.toString() + " " + KeySize.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the EncryptionAlgorithm
     */
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm;
    }

    /**
     * @param EncryptionAlgorithm the EncryptionAlgorithm to set
     */
    public void setEncryptionAlgorithm(EncryptionAlgorithm EncryptionAlgorithm) {
        this.EncryptionAlgorithm = EncryptionAlgorithm;
    }

    /**
     * @return the Passphrase
     */
    public String getPassphrase() {
        return Passphrase;
    }

    /**
     * @param Passphrase the Passphrase to set
     */
    public void setPassphrase(String Passphrase) {
        this.Passphrase = Passphrase;
    }

    /**
     * @return the KeySize
     */
    public RsaLength getKeySize() {
        return KeySize;
    }

    /**
     * @param KeySize the KeySize to set
     */
    public void setKeySize(RsaLength KeySize) {
        this.KeySize = KeySize;
    }

    /**
     * @return the PrivateKey
     */
    public String getPrivateKey() {
        return PrivateKey;
    }

    /**
     * @param PrivateKey the PrivateKey to set
     */
    public void setPrivateKey(String PrivateKey) {
        this.PrivateKey = PrivateKey;
    }

    /**
     * @return the PublicKey
     */
    public String getPublicKey() {
        return PublicKey;
    }

    /**
     * @param PublicKey the PublicKey to set
     */
    public void setPublicKey(String PublicKey) {
        this.PublicKey = PublicKey;
    }
}
