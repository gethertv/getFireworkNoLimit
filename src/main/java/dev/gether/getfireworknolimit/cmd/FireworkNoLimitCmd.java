package dev.gether.getfireworknolimit.cmd;

import dev.gether.getfireworknolimit.GetFireworkNoLimit;
import dev.gether.getfireworknolimit.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FireworkNoLimitCmd extends Command {

    public FireworkNoLimitCmd(@NotNull String name) {
        super(name);
    }


    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(!sender.hasPermission("firework.admin"))
            return false;

        if(args.length==3)
        {
            if(args[0].equalsIgnoreCase("give")) {
                Player target = Bukkit.getPlayer(args[1]);
                if(target==null)
                {
                    sender.sendMessage(ColorFixer.addColor("&cPodany gracz nie jest online!"));
                    return false;
                }
                if(!isInt(args[2]))
                {
                    sender.sendMessage(ColorFixer.addColor("&cMusisz podac liczbe calkowita!"));
                    return false;
                }
                int amount = Integer.parseInt(args[2]);
                ItemStack itemStack = GetFireworkNoLimit.getInstance().getFireworkNoLimit().clone();
                itemStack.setAmount(amount);
                target.getInventory().addItem(itemStack);
                sender.sendMessage(ColorFixer.addColor("&aPomyslnie nadano przedmiot!"));
                return true;

            }
        }
        return false;
    }

    private boolean isInt(String input)
    {
        try{
            int a = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ignored) {}

        return false;
    }
}
