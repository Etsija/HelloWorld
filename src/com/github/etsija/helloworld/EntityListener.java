package com.github.etsija.helloworld;

import org.bukkit.event.Listener;

// This is an example of an entity listener
public class EntityListener implements Listener {

	// This listener needs to know about the plugin which it came from
    public EntityListener(HelloWorld plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // Some possible events to listen
	
    
    
}
