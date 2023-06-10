package dev.gether.getfireworknolimit;

import dev.gether.getfireworknolimit.cmd.FireworkNoLimitCmd;
import dev.gether.getfireworknolimit.listeners.InteractionListener;
import dev.gether.getfireworknolimit.utils.ColorFixer;
import me.gethertv.szaman.api.ISzamanApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class GetFireworkNoLimit extends JavaPlugin {

    private static GetFireworkNoLimit instance;

    private ItemStack fireworkNoLimit;

    private ISzamanApi szamanApi;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();

        if(Bukkit.getPluginManager().getPlugin("getSzaman")!=null)
        {
            szamanApi = (ISzamanApi) Bukkit.getPluginManager().getPlugin("getSzaman");
        }

        loadFirework();

        getServer().getPluginManager().registerEvents(new InteractionListener(this), this);

        new FireworkNoLimitCmd(this, "getfirework");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void loadFirework()
    {
        fireworkNoLimit = new ItemStack(Material.FIREWORK_ROCKET);

        if(getConfig().getBoolean("firework.glow"))
        {
            fireworkNoLimit.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            ItemMeta itemMeta = fireworkNoLimit.getItemMeta();
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            fireworkNoLimit.setItemMeta(itemMeta);
        }

        ItemMeta itemMeta = fireworkNoLimit.getItemMeta();
        itemMeta.setDisplayName(ColorFixer.addColor(getConfig().getString("firework.displayname")));
        List<String> lore = new ArrayList<>();
        lore.addAll(getConfig().getStringList("firework.lore"));
        itemMeta.setLore(ColorFixer.addColor(lore));
        fireworkNoLimit.setItemMeta(itemMeta);
    }

    public ISzamanApi getSzamanApi() {
        return szamanApi;
    }

    public static GetFireworkNoLimit getInstance() {
        return instance;
    }

    public ItemStack getFireworkNoLimit() {
        return fireworkNoLimit;
    }
}
