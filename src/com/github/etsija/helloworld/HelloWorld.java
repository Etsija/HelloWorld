package com.github.etsija.helloworld;

import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

// This plugin works as a personal playground and template for new plugins
public class HelloWorld extends JavaPlugin {

	private Logger _log = Logger.getLogger("Minecraft"); // Write debug info to console
	
	// This method is called when the plugin is enabled
	public void OnEnable() {
		
		// Create the player listener
		new PlayerListener(this);
		
		_log.info("[HelloWorld] enabled!");
	}
	
	// This method is called when the plugin is disabled
	public void OnDisable() {
		_log.info("[HelloWorld] disabled!");
	}

	// Entity listener
	public class eListener implements Listener {
		
	}
}
