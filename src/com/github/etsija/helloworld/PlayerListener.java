package com.github.etsija.helloworld;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

// This is an example of a player listener
public class PlayerListener implements Listener {
    
	private final HelloWorld plugin;
	private Logger _log = Logger.getLogger("Minecraft"); // Write debug info to console
	
	// This listener needs to know about the plugin which it came from
    public PlayerListener(HelloWorld plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
        this.plugin = plugin;
    }

    // Some possible events to listen
    
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
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Here you can print welcome messages for example
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN + "Why hello, " + player.getName());

	}
}
