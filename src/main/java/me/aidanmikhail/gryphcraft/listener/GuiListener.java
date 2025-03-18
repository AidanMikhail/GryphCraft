package me.aidanmikhail.gryphcraft.listener;

import com.bekvon.bukkit.residence.commands.attack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;

public class GuiListener implements Listener {

    private final Map<Player, Integer> playerPages = new HashMap<>();
    private final List<String> enchantments = Arrays.asList(
            "Blindness - Blind your target",
            "Healthsteal - Steal a specified amount of health from a player when you hit them",
            "Bleed - Make the target slowly bleed out",
            "Lightning - DISABLED ON THE SERVER",
            "Teleport - DISABLED ON THE SERVER",
            "Jetpack - Hold sneak and move around, with your own jetpack",
            "Explosive - Press sneak and let an explosive go off under your feet",
            "Excavator - Mine multiple blocks at once",
            "Autosmelt - Autosmelt ores when mined",
            "Freeze - Temporarily freeze a player when you hit them",
            "Confusion - Make your target have confusion/nausea",
            "Multishoot - Shoot 3 arrows instead of just one",
            "Dash - Quickly dash away from your opponent",
            "Bloodlust - Gain strength, regeneration, and speed on killing a player",
            "Shockwave - Send a shockwave to players around you, pushing them back",
            "Veinminer - DISABLED ON THE SERVER",
            "XPBoost - Gain extra XP from killing mobs and players",
            "Enlightened - Gain extra XP from breaking ores",
            "EnderAura - Increases pearls dropped by killing enderman",
            "Frostbite - Slows players down when hit",
            "Magnet - Grabs items around the player",
            "Speed - Increases player speed",
            "Leap - Increases player jump height",
            "Growth - Gives the player extra health",
            "Detonate - Makes your arrows explode on impact",
            "Backstabber - Deal more damage on the first hit when out of combat",
            "Piercing - Arrows shoot through multiple entities",
            "Deflecting - Chance of arrows bouncing off armor and hitting the shooter",
            "Bait - Chance to get multiple drops when fishing",
            "Angler - More XP when fishing",
            "Abiding - Makes your tool/armor unbreakable",
            "Disarm - DISABLED ON THE SERVER",
            "Archer - Deals more damage with your arrows",
            "Farmer - Auto replants seeds",
            "Decapitation - Small chance for players to drop heads on death",
            "Dismantle - DISABLED ON THE SERVER",
            "Famine - Give your opponent hunger",
            "Spotlight - Allows you to see in the dark",
            "Feast - Chance to regain hunger and take it away from your opponent",
            "Updraft - Chance to send your attacker into the air",
            "Lavawalker - Allows you to walk on lava, like frostwalker",
            "Doublestrike - Has a chance to do double damage",
            "Timber - Instantly break all logs of a tree",
            "Missile - Turn your arrows into fireball projectiles",
            "Critical - Turn all of your hits into critical hits",
            "Inflame - Set mobs and players around you on fire",
            "NightOwl - Do more damage at night time",
            "Headshot - Do more damage when you get a headshot with a bow",
            "ArrowStorm - Rain arrows on your opponent",
            "Hook - When reeling in with a fishing rod, make an entity come flying at you",
            "Gravity - Pull players towards you with a right click",
            "Adrenaline - Do more damage when you're at or below 2 hearts",
            "Resilience - Chance of not taking damage when you're at or below 2 hearts",
            "Coward - Chance of gaining speed upon taking damage from an entity",
            "Weaken - Chance of giving your opponent weakness when you hit them",
            "Withering - Chance of giving your opponent the withering effect",
            "Heavy - Gain more health but in turn move slower",
            "Lightweight - Move faster but have less health",
            "Blastoff - On low health chance to blastoff in the sky when hit",
            "Flippers - Swim faster in the water",
            "Harvester - Chance to gain more drops from crops",
            "Dragon Hunter - Do more damage to enderdragons",
            "Ascend - Ascend to the sky when you right-click",
            "Hell Hunter - Do more damage to mobs in the nether",
            "Cubism - Do more damage to cubed mobs",
            "Vanish - Chance to become invisible when hit",
            "Dodge - Chance to dodge an attack",
            "Predator - Make entities you hit glow for a few seconds",
            "Slaughter - Do more damage to passive mobs",
            "SkullCrusher - Do more damage to skeletons",
            "Ninja - Inflict more damage while sneaking",
            "Thanos - Chance to half your opponent's health",
            "Consume - Every few seconds gain half a hunger bar",
            "Antidote - Negates all damage from poison and deals it to the chestplate",
            "Curse of conductivity  - Gives a chance to get struck by lightning during a thunderstorm",
            "Ghasted - Shoot a fire arrow which explodes upon landing, destroying the arrow in the process",
            "Gravity - Shoot an arrow which pulls all mobs around it upon landing, destroying the arrow in the process",
            "Illagers bane - Deal extra damage to Illagers, Vexes, Witches and Ravagers",
            "Outreach - Raises the block interaction range",
            "Photosynthesis - Repairs the item when is in direct view of the sun",
            "Traveler - Faster sprinting speed and step up full blocks",
            "Wax wings - Negates durability damage from slow gliding but raises by 50% the durability damage from gliding fast",
            "Wither coated - Deals wither upon hitting the target and doubles the durability damage",
            "Might - Power for crossbow",
            "Blowthrough - Piercing for bow",
            "Tri-shot - Multishot for bows",
            "Spitefull - Bellow 2.5 hearts players are given +2 (+2 per level) attack damage",
            "Curse of Deterioration - Prevents the item from having Mending applied to it",
            "Wingspan - Hitting a mob creates a more powerful Sweeping Edge-like effect",
            "Dragonbane - Enchanted weapon deals additional damage against Dragons",
            "Exhalation - Shot arrows deal 15 damage, and are more effective against Dragons",
            "Pillaring - When a block is mined, the next 3 blocks in a row also get mined.",
            "Dragonhearted - Grants additional max hearts equal to the level",
            "Dragonyield - Thorns-like enchantment that only affects Dragons",
            "Voidwalk - You can hover over the void as long as there are no blocks beneath you.",
            "Clear Skies - While blocking, causes Dragon breath clouds to instantly dissipate",
            "Kickback - When a mob attack is blocked, knocks the mob back and deals them damage",
            "Altitude - While flying, looking upwards gives a strong levitation effect",
            "Dragonsight - While wearing, hidden ores will be highlighted in the direction you're looking",
            "Dragon Lungs - Applies the same effects as Aqua Affinity and Respiration"
    );

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        // Get inventory name to distinguish menus
        String title = event.getView().getTitle();

        // Cancel movement in both menus
        if (title.equals(ChatColor.GOLD + "Our Plugins") || title.contains(ChatColor.DARK_PURPLE + "Enchantments - Page ") || title.contains("END - SELECT") || title.contains("END - DRAGONS") || title.contains("END - NEW ITEMS")) {
            event.setCancelled(true);
        }

        // Check if clicked inside "Our Plugins"
        if (title.equals(ChatColor.GOLD + "Our Plugins")) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            if (clickedItem.getType() == Material.ENCHANTED_BOOK) {
                player.closeInventory();
                openEnchantmentsGUI(player, 1);
            }

            if (clickedItem.getType() == Material.DRAGON_HEAD) {
                player.closeInventory();
                openDragonSelectionGUI(player);
            }
        }

        if (title.contains(ChatColor.DARK_PURPLE + "Enchantments - Page ")) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem.getType() == Material.ARROW) {
                if (clickedItem.getItemMeta().getDisplayName().contains("Next")) {
                    nextPage(player);
                } else if (clickedItem.getItemMeta().getDisplayName().contains("Previous")) {
                    previousPage(player);
                }
            }
        }

        // Dragon Selection Menu
        if (title.equals(ChatColor.DARK_PURPLE + "END - SELECT")) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            if (clickedItem.getType() == Material.ENDER_EYE) {
                player.closeInventory();
                openDragonsGUI(player);
            } else if (clickedItem.getType() == Material.NETHERITE_SWORD) {
                player.closeInventory();
                openNewItemsGUI(player);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(ChatColor.GOLD + "Our Plugins")) {
            Player player = (Player) event.getPlayer();
        }
    }

    private void openEnchantmentsGUI(Player player, int page) {
        Inventory enchantmentsGUI = Bukkit.createInventory(player, 36, ChatColor.DARK_PURPLE + "Enchantments - Page " + page);
        int startIndex = (page - 1) * 27;
        int endIndex = Math.min(startIndex + 27, enchantments.size());

        for (int i = startIndex; i < endIndex; i++) {
            enchantmentsGUI.setItem(i - startIndex, createEnchantmentItem(enchantments.get(i)));
        }

        // Navigation Buttons
        if (page > 1) {
            enchantmentsGUI.setItem(27, createNavButton("Previous Page", Material.ARROW));
        }
        if (endIndex < enchantments.size()) {
            enchantmentsGUI.setItem(35, createNavButton("Next Page", Material.ARROW));
        }

        player.openInventory(enchantmentsGUI);
        playerPages.put(player, page);
    }

    // Opens the GUI when clicking the Dragon Head
    private void openDragonSelectionGUI(Player player) {
        Inventory dragonSelectionGUI = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "END - SELECT");

        // "Dragons" Button
        ItemStack dragonsButton = new ItemStack(Material.ENDER_EYE);
        ItemMeta dragonsMeta = dragonsButton.getItemMeta();
        if (dragonsMeta != null) {
            dragonsMeta.setDisplayName(ChatColor.GREEN + "Dragons");
            dragonsMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Click to view different Dragon types."));
            dragonsButton.setItemMeta(dragonsMeta);
        }
        dragonSelectionGUI.setItem(2, dragonsButton);

        // "New Items" Button
        ItemStack newItemsButton = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta newItemsMeta = newItemsButton.getItemMeta();
        if (newItemsMeta != null) {
            newItemsMeta.setDisplayName(ChatColor.GREEN + "New Items");
            newItemsMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Click to view new items."));
            newItemsButton.setItemMeta(newItemsMeta);
        }
        dragonSelectionGUI.setItem(6, newItemsButton);

        // Explination Sign
        ItemStack explainButton = new ItemStack(Material.OAK_SIGN);
        ItemMeta explainMeta = newItemsButton.getItemMeta();
        if (newItemsMeta != null) {
            newItemsMeta.setDisplayName(ChatColor.DARK_PURPLE + "THE END");

            List<String> explainLore = new ArrayList<>();
            explainLore.add(ChatColor.GRAY + "To improve the end we added a new plugin");
            explainLore.add(ChatColor.GRAY + "DragonKind Evolved adds new dragon types to improve replayability");
            explainLore.add(ChatColor.GRAY + "Run through the end to get fun new items, but watch out! It gets harder as you go.");

            explainMeta.setLore(explainLore);
            explainButton.setItemMeta(explainMeta);
        }
        dragonSelectionGUI.setItem(4, explainButton);

        player.openInventory(dragonSelectionGUI);
    }

    private ItemStack createEnchantmentItem(String name) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = book.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.AQUA + name.split(" - ")[0]);
            meta.setLore(Collections.singletonList(ChatColor.GRAY + name.split(" - ")[1]));
            book.setItemMeta(meta);
        }
        return book;
    }

    private ItemStack createNavButton(String name, Material material) {
        ItemStack button = new ItemStack(material);
        ItemMeta meta = button.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.YELLOW + name);
            button.setItemMeta(meta);
        }
        return button;
    }

    private void nextPage(Player player) {
        openEnchantmentsGUI(player, playerPages.getOrDefault(player, 1) + 1);
    }

    private void previousPage(Player player) {
        openEnchantmentsGUI(player, Math.max(1, playerPages.getOrDefault(player, 1) - 1));
    }

    // Opens the Dragon Types GUI
    private void openDragonsGUI(Player player) {
        Inventory dragonsGUI = Bukkit.createInventory(player, 36, ChatColor.DARK_PURPLE + "END - DRAGONS");

        // Dragon Types
        String[] dragons = {
                "Zombified Dragon - Breath Clouds spread more and give Hunger + Nausea",
                "Warped Dragon - Periodically teleports and perches more often",
                "Sculk Dragon - Breath Clouds give Darkness + Instant Damage",
                "Frost Dragon - Converts the ground to snow and ice as it flies",
                "Dragon Tyrant - Summons Wither Skeleton Servants",
                "Skeletal Dragon - Perches often and summons Skeleton Servants",
                "Blazing Dragon - Sets fires and summons Blaze Servants",
                "Elder Dragon - Periodically casts random spells on players",
                "Dragon Wight - Moves much faster and never perches",
                "The Last Dragon - Frequently shoots Amethyst Shard projectiles",
                "Beacon Dragon - Much slower flying speed near the ground",
                "Desert Dragon - Converts the ground to sand with frequent cacti",
                "Thunder Dragon - Breath attack summons lightning",
                "10,000 Dragon - Always appears as the 30th Dragon"
        };

        // Add dragon items
        for (int i = 0; i < dragons.length && i < 27; i++) {
            dragonsGUI.setItem(i, createInfoItem(Material.DRAGON_EGG, dragons[i]));
        }

        player.openInventory(dragonsGUI);
    }

    // Opens the New Items GUI
    private void openNewItemsGUI(Player player) {
        Inventory newItemsGUI = Bukkit.createInventory(player, 36, ChatColor.DARK_PURPLE + "END - NEW ITEMS");

        // New Items
        String[][] newItems = {
                {"Dragonslayer Sword", "Deals 15 damage, has increased attack speed"},
                {"Dragon-Sinew Crossbow", "Enchanted with Exhalation and Dragonbane IV"},
                {"Dragontooth Pickaxe", "More effective when mining Obsidian"},
                {"Dragonhide Armor", "Provides more Armor value than Netherite"},
                {"Dragonskull Shield", "Enchanted with Clear Skies and Kickback"},
                {"Dragonscale Wings", "Enchanted with Dragonhearted III and Altitude"},
                {"Draconic Scepter", "Launch a damaging laser up to 48 blocks away"},
                {"Dragon Eyes", "Enchanted with Dragonsight and Dragon Lungs III"},
                {"Dragonheart Anchor", "Sets a Recovery point for emergency teleport"}
        };

        // Add new items
        for (int i = 0; i < newItems.length && i < 27; i++) {
            newItemsGUI.setItem(i, createInfoItem(Material.ENCHANTED_BOOK, newItems[i][0] + " - " + newItems[i][1]));
        }

        player.openInventory(newItemsGUI);
    }

    // Creates an ItemStack with a name and description
    private ItemStack createInfoItem(Material material, String info) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            String[] parts = info.split(" - ");
            meta.setDisplayName(ChatColor.AQUA + parts[0]);
            meta.setLore(Collections.singletonList(ChatColor.GRAY + parts[1]));
            item.setItemMeta(meta);
        }
        return item;
    }
}
