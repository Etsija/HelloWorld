package com.github.etsija.HelloWorld;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

// This plugin works as a personal playground and template for new plugins
public class HelloWorld extends JavaPlugin {

	private Logger _log = Logger.getLogger("Minecraft"); // Write debug info to console
	
	// This method is called when the plugin is enabled
	public void OnEnable() {
		// Register all listeners
		getServer().getPluginManager().registerEvents(new pListener(), this);
		getServer().getPluginManager().registerEvents(new eListener(), this);
		
		_log.info("[HelloWorld] enabled!");
	}
	
	// This method is called when the plugin is disabled
	public void OnDisable() {
		_log.info("[HelloWorld] disabled!");
	}
	
	// ------------------------------------------------------------------------
	// Listeners
	// ------------------------------------------------------------------------
	
	// Player listener
	public class pListener implements Listener {
		
		@EventHandler
		public void onPlayerInteract(PlayerInteractEvent event) {
			// Here you can handle different stuff relating to the player interact event
			final Action action = event.getAction(); // Actions
			Player player = event.getPlayer(); // Player which fired the event
			int itemInHand = player.getItemInHand().getTypeId(); // ID of item in hand
			Block block = event.getClickedBlock();
			player.sendMessage("       player = " + player);
			player.sendMessage("       action = " + action);
			player.sendMessage(" item in hand = " + itemInHand);
			player.sendMessage("block clicked = " + block);
		}
		
		@EventHandler
		public void onPlayerLogin(PlayerLoginEvent event) {
			// Here you can print welcome messages for example
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.GREEN + "Why Hello, " + player);
		}
	}
	
	// Entity listener
	public class eListener implements Listener {
		
	}
	
}
