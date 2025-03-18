package me.aidanmikhail.gryphcraft.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Information implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Inventory inventory = Bukkit.createInventory(player, 9 * 2, ChatColor.GOLD + "Our Plugins");

        // ENCHANTMENTS
        ItemStack enchantmentsButton = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta enchantmentsMeta = enchantmentsButton.getItemMeta();
        if (enchantmentsMeta != null) {
            enchantmentsMeta.setDisplayName(ChatColor.GREEN + "SuperEnchants - CLICK TO SEE ENCHANTS");
            enchantmentsButton.setItemMeta(enchantmentsMeta);
        }
        inventory.setItem(5, enchantmentsButton);

        // DRAGON
        ItemStack dragonButton = new ItemStack(Material.DRAGON_HEAD);
        ItemMeta dragonMeta = dragonButton.getItemMeta();
        if (dragonMeta != null) {
            dragonMeta.setDisplayName(ChatColor.GREEN + "THE END - CLICK HERE TO LEARN MORE");
            dragonButton.setItemMeta(dragonMeta);
        }
        inventory.setItem(3, dragonButton);

        // AURA SKILLS
        ItemStack magicSkillsButton = new ItemStack(Material.IRON_SWORD);
        ItemMeta magicSkillsMeta = magicSkillsButton.getItemMeta();
        if (magicSkillsMeta != null) {
            List<String> magicSkillsLore = new ArrayList<>();

            magicSkillsLore.add(ChatColor.GRAY + "The AuraSkills plugin enhances gameplay with a skill system.");
            magicSkillsLore.add(ChatColor.GRAY + "Level up abilities like mining, farming, and combat.");
            magicSkillsLore.add(ChatColor.GRAY + "Use /skills to learn more! And fight for the top on /skilltop");

            magicSkillsMeta.setDisplayName(ChatColor.GREEN + "AuraSkills/MagicItems");
            magicSkillsMeta.setLore(magicSkillsLore);
            magicSkillsButton.setItemMeta(magicSkillsMeta);
        }
        inventory.setItem(1, magicSkillsButton);

        // CLAIMS
        ItemStack claimsButton = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta claimsMeta = claimsButton.getItemMeta();
        if (claimsMeta != null) {
            List<String> claimsLore = new ArrayList<>();

            claimsLore.add(ChatColor.GRAY + "Claim land to prevent griefing.");
            claimsLore.add(ChatColor.GRAY + "Right-click two corners with a golden shovel. To claim everything in between");
            claimsLore.add(ChatColor.GRAY + "Use /trust <PlayerName> to allow friends to work on your claim");

            claimsMeta.setDisplayName(ChatColor.GREEN + "Claiming");
            claimsMeta.setLore(claimsLore);
            claimsButton.setItemMeta(claimsMeta);
        }
        inventory.setItem(7, claimsButton);

        // SOCIALS
        ItemStack socialsButton = new ItemStack(Material.OAK_SIGN);
        ItemMeta socialsMeta = socialsButton.getItemMeta();
        if (socialsMeta != null) {
            List<String> socialsLore = new ArrayList<>();
            socialsLore.add(ChatColor.GRAY + "Discord: https://discord.gg/HQamfAfnrt");
            socialsLore.add(ChatColor.GRAY + "Instagram: @gryphongaming.uog");
            socialsLore.add(ChatColor.GRAY + "Twitter/X: @uogesports");
            socialsMeta.setDisplayName(ChatColor.GREEN + "OUR SOCIALS");
            socialsMeta.setLore(socialsLore);
            socialsButton.setItemMeta(socialsMeta);
        }
        inventory.setItem(13, socialsButton);

        player.openInventory(inventory);
        return true;
    }
}
