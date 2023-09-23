/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InputOutput;

import Configuration.Parameters;
import static Configuration.Parameters.Constants.RootPathForKeys;
import Encryptor.EncryptionFactory;
import Model.ReceiverEntity;
import Model.SenderEntity;
import java.util.List;


/**
 *
 * @author adipu
 */
public class FileManager implements IFileManager{

    @Override
    public void Save() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public boolean CheckApplicationRoot(String ApplicationRoot) {
        FileHandler fileHandler = new FileHandler();
        boolean checkOK = fileHandler.checkFolder(ApplicationRoot);
        return checkOK;
    }

    public boolean CheckApplicationKeys(String RootPathForKeys) {
        FileHandler fileHandler = new FileHandler();
        boolean checkOK = fileHandler.checkFolder(RootPathForKeys);
        return checkOK;
    }

    public boolean CheckApplicationMessages(String RootPathForMessages) {
        FileHandler fileHandler = new FileHandler();
        boolean checkOK = fileHandler.checkFolder(RootPathForMessages);
        return checkOK;
    }

    public boolean CheckLogFile(String PathForLogFile) {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.checkFile(PathForLogFile);
    }

    public void createRootDirectory(String ApplicationRoot) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createDirectory(ApplicationRoot);
    }

    public void createPubKeyDirectory(String RootPathForPubKeys) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createDirectory(RootPathForPubKeys);
    }

    public void createPrivKeyDirectory(String RootPathForPrivKeys) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createDirectory(RootPathForPrivKeys);
    }

    public void createMessagesDirectory(String RootPathForMessages) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createDirectory(RootPathForMessages);
    }

    public void createKeyDirectory(String RootPathForKeys) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createDirectory(RootPathForKeys);
    }

    public void createSenderKeyFiles(SenderEntity senderEntity) {
//        EncryptionFactory ef = new EncryptionFactory(senderEntity);
//        String pubKey = ef.GetEncryptionStrategy(senderEntity.getEncryptionAlgorithm()).GetPubKey();
//        String privKey = ef.GetEncryptionStrategy(senderEntity.getEncryptionAlgorithm()).GetPrivKey();
        FileHandler fileHandler = new FileHandler();
        fileHandler.createFile(Parameters.Constants.RootPathForPrivKeys, senderEntity.getName() + ".pgp");
        fileHandler.createFile(Parameters.Constants.RootPathForPubKeys, senderEntity.getName() + ".pub");
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForPrivKeys, senderEntity.getName() + ".pgp", senderEntity.getPrivateKey());
        fileHandler.writeToFile(Parameters.Constants.RootPathForPubKeys, senderEntity.getName() + ".pub", senderEntity.getPublicKey());

    }
     public void createReceiverKeyFiles(ReceiverEntity receiverEntity) {
//        EncryptionFactory ef = new EncryptionFactory(senderEntity);
//        String pubKey = ef.GetEncryptionStrategy(senderEntity.getEncryptionAlgorithm()).GetPubKey();
//        String privKey = ef.GetEncryptionStrategy(senderEntity.getEncryptionAlgorithm()).GetPrivKey();
        FileHandler fileHandler = new FileHandler();
        fileHandler.createFile(Parameters.Constants.RootPathForPrivKeys, receiverEntity.getName() + ".pgp");
        fileHandler.createFile(Parameters.Constants.RootPathForPubKeys, receiverEntity.getName() + ".pub");
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForPrivKeys, receiverEntity.getName() + ".pgp", receiverEntity.getPrivateKey());
        fileHandler.writeToFile(Parameters.Constants.RootPathForPubKeys, receiverEntity.getName() + ".pub", receiverEntity.getPublicKey());

    }   

    public void createEncryptedMessage(String message, String fileName) {
        FileHandler fileHandler = new FileHandler();
        
        fileHandler.createFile(Parameters.Constants.RootPathForMessages, fileName + ".pgp");
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForMessages, fileName + ".pgp", message);
    }

    public void createPlaintextMessage(String plaintextMessage, String fileName) {
        FileHandler fileHandler = new FileHandler();
        
        String plaintextFileName = fileName.replace(".pgp", "");
        fileHandler.createFile(Parameters.Constants.RootPathForMessages, "dec_" + plaintextFileName);
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForMessages, "dec_" + plaintextFileName, plaintextMessage);
    }

    public void createEncryptedAndSignedMessage(String encryptedMsg, String fileName) {
        FileHandler fileHandler = new FileHandler();
        
        fileHandler.createFile(Parameters.Constants.RootPathForMessages, "signed_" + fileName + ".pgp");
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForMessages, "signed_" + fileName + ".pgp", encryptedMsg);
    }

    public void createPlaintextVerifiedMessage(String plaintextMessage, String name) {
        FileHandler fileHandler = new FileHandler();
        
        String plaintextFileName = name.replace(".pgp", "");
        plaintextFileName = name.replace("signed_", "");
        fileHandler.createFile(Parameters.Constants.RootPathForMessages, "dec_ver_" + plaintextFileName);
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForMessages, "dec_ver_" + plaintextFileName, plaintextMessage);
    }

    public void createFileForTesting(String RootPathForMessages) {
        FileHandler fileHandler = new FileHandler();
        
        fileHandler.createFile(Parameters.Constants.RootPathForMessages, "spe.txt");
        
        fileHandler.writeToFile(Parameters.Constants.RootPathForMessages, "spe.txt", "adi\n123\n456\789");
    }

    public List<String> GetPrivKeyList() {
        FileHandler fileHandler = new FileHandler();
        List<String> list = fileHandler.getFilesInDirectory(Parameters.Constants.RootPathForPrivKeys);
        return list;
    }

    public List<String> GetPubKeyList() {
        FileHandler fileHandler = new FileHandler();
        List<String> list = fileHandler.getFilesInDirectory(Parameters.Constants.RootPathForPubKeys);
        return list;
    }

}
