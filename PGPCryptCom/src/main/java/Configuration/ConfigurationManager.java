/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import InputOutput.FileManager;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 *
 * @author adipu
 */
public class ConfigurationManager /*implements IConfigurationManager*/{
   
    
    @Inject
    public ConfigurationManager()
    {
    }
    
    public void LoadParameters()
    {
        
    }

    public boolean CheckIOFolders() {
        FileManager fileManager = new FileManager();
        boolean checkPassed = false;
        checkPassed = fileManager.CheckApplicationRoot(Parameters.Constants.ApplicationRoot);
        if (checkPassed) {
            checkPassed = fileManager.CheckApplicationKeys(Parameters.Constants.RootPathForKeys); // check my keys
        }
        else {
            
        }
        if (checkPassed) {
            checkPassed = fileManager.CheckApplicationKeys(Parameters.Constants.RootPathForPubKeys); // check my keys
        }
        if (checkPassed) {
            checkPassed = fileManager.CheckApplicationKeys(Parameters.Constants.RootPathForPrivKeys); // check my keys
        }
        if (checkPassed) {
            checkPassed = fileManager.CheckApplicationMessages(Parameters.Constants.RootPathForMessages); // check my keys
        }
        return checkPassed;
    }

    public boolean CheckLogFile() {
        FileManager fileManager = new FileManager();
        boolean ok = fileManager.CheckLogFile(Parameters.Constants.PathForLogFile);
        return ok;
    }

    public void InitializeLogging() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void CreateDirectories() {
        FileManager fileManager = new FileManager();
        fileManager.createRootDirectory(Parameters.Constants.ApplicationRoot);
        fileManager.createKeyDirectory(Parameters.Constants.RootPathForKeys);
        fileManager.createPubKeyDirectory(Parameters.Constants.RootPathForPubKeys);
        fileManager.createPrivKeyDirectory(Parameters.Constants.RootPathForPrivKeys);
        fileManager.createMessagesDirectory(Parameters.Constants.RootPathForMessages);
        fileManager.createFileForTesting(Parameters.Constants.RootPathForMessages);

    }
}
