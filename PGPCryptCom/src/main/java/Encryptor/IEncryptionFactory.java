/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Encryptor;

import Model.EncryptionAlgorithm;

/**
 *
 * @author adipu
 */
public interface IEncryptionFactory {
    IEncryptionStrategy GetEncryptionStrategy(EncryptionAlgorithm encryptionAlgorithm);
}
