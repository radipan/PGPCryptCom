/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.aapps.pgpcryptcom;

import Configuration.ConfigurationManager;
import Configuration.Parameters;
import DependencyInjection.PgpComBootstrapper;
import java.util.function.Consumer;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import java.nio.file.FileSystemNotFoundException;
import javax.swing.JOptionPane;
//import serilogj.Log;
//import serilogj.ILogger;
//import serilogj.LoggerConfiguration;
//import serilogj.events.LogEventLevel;
//import static serilogj.sinks.coloredconsole.ColoredConsoleSinkConfigurator.*;
//import static serilogj.sinks.rollingfile.RollingFileSinkConfigurator.*;
//import static serilogj.sinks.seq.SeqSinkConfigurator.*;
/**
 *
 * @author adipu
 */
public class PGPCryptCom {

    public static void main(String[] args) {
//        
//        Log.setLogger(new LoggerConfiguration()
//	.writeTo(coloredConsole())
//	.writeTo(rollingFile("test-{Date}.log"), LogEventLevel.Information)
//	//.writeTo(seq("http://localhost:5341/"))
//	.setMinimumLevel(LogEventLevel.Verbose)
//	.createLogger());
//        
//        Log.information("Starting application...");
        
        //PgpComBootstrapper pico = new PgpComBootstrapper();
        //ConfigurationManager configurationManager = pico.getComponent(ConfigurationManager.class);
        //Injector injector = pico.createInjector();
        //ConfigurationManager CM = injector.getInstance(ConfigurationManager.class);
        ConfigurationManager configurationManager = new ConfigurationManager();
        boolean IOFoldersOK = configurationManager.CheckIOFolders();
        boolean LogFileOK = configurationManager.CheckLogFile();
        if (!LogFileOK)
        {
            //configurationManager.InitializeLogging();
        }
        if (!IOFoldersOK)
        {
            try {
//                Log.information("PGPCryptCom::main: Trying to create directory");
                configurationManager.CreateDirectories();

            } 
            catch (FileSystemNotFoundException e) {
//                Log.error("PGPCryptCom::main: {message}", e.getMessage());
            }
            
            IOFoldersOK = configurationManager.CheckIOFolders();
            
             
        }
        if (IOFoldersOK /*&& LogFileOK*/)
        {
            
            FormHome form = new FormHome();
            form.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Failed to initialize folder structure", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
