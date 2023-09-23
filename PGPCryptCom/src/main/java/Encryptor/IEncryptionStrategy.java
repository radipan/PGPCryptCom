/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Encryptor;

/**
 *
 * @author adipu
 */
public interface IEncryptionStrategy {
    public String GetPubKey();
    public String GetPrivKey();
    public String Encrypt(String message);
}
