package com.github.etsija.helloworld;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

// This plugin works as a personal playground and template for new plugins
public class HelloWorld extends JavaPlugin {

	private Logger _log = Logger.getLogger("Minecraft"); // Write debug info to console
	File configFile;				// config.yml
	File messagesFile;				// messages.yml
	FileConfiguration config;		// configuration object for config.yml
	FileConfiguration messages;		// configuration object for messages.yml
	private boolean debug;			// 
	private String welcomeMessage;	// plugin config parameters
	private String testMessage;		//
	
	// This method is called when the plugin is enabled
	public void onEnable() {
		
		// Initialize the configuration files
		// Note that so far, they're only virtual, not real files yet
		configFile = new File(getDataFolder(), "config.yml");
		messagesFile = new File(getDataFolder(), "messages.yml");
		
		// If the plugin is run the first time, create the actual config files
		try {
            firstRun(configFile, "config.yml");
            firstRun(messagesFile, "messages.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// Import configurations from the (physical) files
		config = new YamlConfiguration();
        messages = new YamlConfiguration();
		loadYaml(configFile, config);
		loadYaml(messagesFile, messages);
        
		// Set the default parameter values
		final Map<String, Object> configParams = new HashMap<String, Object>();
		configParams.put("general.debug", "true");
		
		final Map<String, Object> messageParams = new HashMap<String, Object>();
		messageParams.put("welcome", "Why hello, ");
		messageParams.put("testmessage", "This is a test message!");
		
		setDefaultValues(config, configParams);
		setDefaultValues(messages, messageParams);
		
		// And save them to the files, if they don't already contain such parameters
		// This is also a great way to correct mistyping of the config params (by the users)
		saveYaml(configFile, config);
		saveYaml(messagesFile, messages);
		
		// Finally, import all needed config params from the corresponding config files
		debug = config.getBoolean("general.debug");
		welcomeMessage = messages.getString("welcome");
		testMessage = messages.getString("testmessage");
		
		if (debug) {
			_log.info(welcomeMessage);
			_log.info(testMessage);
		}
		
		// Create listeners for this plugin
		new PlayerListener(this);
		new EntityListener(this);
		
		_log.info("[HelloWorld] enabled!");
	}
	
	// This method is called when the plugin is disabled
	public void onDisable() {
		saveYaml(configFile, config);
		saveYaml(messagesFile, messages);
		_log.info("[HelloWorld] disabled!");
	}
	
	//////////////////////////////////////
	// Plugin's file configuration methods
	//////////////////////////////////////
	
	// Set default values for parameters if they don't already exist
	public void setDefaultValues(FileConfiguration config, Map<String, Object> configParams) {
		if (config == null) return;
		for (final Entry<String, Object> e : configParams.entrySet())
			if (!config.contains(e.getKey()))
				config.set(e.getKey(), e.getValue());
	}
	
	// Load a file from disk into its respective FileConfiguration
	public void loadYaml(File file, FileConfiguration configuration) {
        try {
            configuration.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save a FileConfiguration into its respective file on disk
    public void saveYaml(File file, FileConfiguration configuration) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	// This private method is called on first time the plugin is executed
	// and it handles the creation of the datafiles
	private void firstRun(File file, String filename) throws Exception {
	    if (!file.exists()) {
	        file.getParentFile().mkdirs();
	        copy(getResource(filename), file);
	    }
	}
	
	// This is a private method to copy contents of the YAML file found in
	// the JAR to a datafile in ./pluginname/*.yml
	private void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0)
	            out.write(buf,0,len);
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
