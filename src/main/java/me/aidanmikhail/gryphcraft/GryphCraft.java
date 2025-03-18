package me.aidanmikhail.gryphcraft;

import me.aidanmikhail.gryphcraft.command.Information;
import me.aidanmikhail.gryphcraft.listener.GuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GryphCraft extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("GryphCraft plugin has been enabled!");
		getServer().getPluginManager().registerEvents(new GuiListener(), this);
		getCommand("information").setExecutor(new Information());
	}

	@Override
	public void onDisable() {
		getLogger().info("GryphCraft plugin has been disabled!");
	}
}
