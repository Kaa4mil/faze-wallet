package dev.kaa4mil.fazewallet.command;

import dev.kaa4mil.fazewallet.WalletPlugin;
import dev.kaa4mil.fazewallet.config.MessageConfig;
import dev.kaa4mil.fazewallet.user.UserManagerImpl;
import dev.kaa4mil.fazewallet.util.WalletUtil;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class WalletManageCommand extends Command {

    private static final String PERMISSION = "fazedev.wallet.admin";

    private final WalletPlugin plugin;
    private final UserManagerImpl userManager;
    private final MessageConfig messageConfig;

    @Inject
    public WalletManageCommand(@NotNull final WalletPlugin plugin, @NotNull final UserManagerImpl userManager, @NotNull final MessageConfig messageConfig) {
        super("adminwallet");
        this.setAliases(List.of("awallet", "walletadmin"));

        this.plugin = plugin;
        this.messageConfig = messageConfig;
        this.userManager = userManager;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(!sender.hasPermission(PERMISSION)) {
            WalletUtil.sendMessage(sender, "&cNie posiadasz uprawnienia: &4" + PERMISSION);
            return false;
        }

        if(args.length == 1 && args[0].equals("reload")) {
            this.plugin.getWalletConfig().load();
            this.plugin.getMessageConfig().load();
            this.plugin.getCategoryConfig().load();

            WalletUtil.sendMessage(sender, "&aPrzeladowano pomyslnie konfiguracje!");
            return true;
        }

        if(args.length != 3) {
            WalletUtil.sendMessage(sender, "&cPoprawne uzycie: &4/awallet <add/remove/set> <gracz> <wartosc>");
            return false;
        }

        final Player player = Bukkit.getPlayerExact(args[1]);

        if(player == null) {
            WalletUtil.sendMessage(sender, this.messageConfig.getInvalidPlayerMessage(), Map.of("PLAYER", args[1]));
            return false;
        }

        final double amount = Double.parseDouble(args[2]);

        switch (args[0]) {
            case "add":
                this.userManager.addBalance(player, amount);
                WalletUtil.sendMessage(sender, this.messageConfig.getAddedBalanceMessage(), Map.of("PLAYER", args[1], "AMOUNT", amount));
                break;
            case "remove":
                this.userManager.removeBalance(player, amount);
                WalletUtil.sendMessage(sender, this.messageConfig.getRemoveBalanceMessage(), Map.of("PLAYER", args[1], "AMOUNT", amount));
                break;
            case "set":
                this.userManager.setBalance(player, amount);
                WalletUtil.sendMessage(sender, this.messageConfig.getSetBalanceMessage(), Map.of("PLAYER", args[1], "AMOUNT", amount));
                break;
            default:
                WalletUtil.sendMessage(sender, "&cPoprawne uzycie: &4/awallet <add/remove/set> <gracz> <wartosc>");
                break;
        }

        return true;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if(args.length == 1) {
            return List.of("add", "remove", "set", "reload");
        }
        return List.of();
    }
}
