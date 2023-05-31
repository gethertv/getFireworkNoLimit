package dev.gether.getfireworknolimit.listeners;

import dev.gether.getfireworknolimit.GetFireworkNoLimit;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class InteractionListener implements Listener {

    private final GetFireworkNoLimit plugin;

    public InteractionListener(GetFireworkNoLimit plugin)
    {
        this.plugin =plugin;
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if(player.getItemInHand().isSimilar(GetFireworkNoLimit.getInstance().getFireworkNoLimit())) {
            ItemStack item = player.getItemInHand();
            ItemMeta itemMeta = item.getItemMeta();
            if (player.isGliding()) {
                if(plugin.getSzamanApi()!=null && plugin.getSzamanApi().hasCooldown(player))
                    return;

                player.setVelocity(player.getLocation().getDirection().multiply(2.0));
                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f, 1f);
            }
            event.setCancelled(true);
        }
    }

}
