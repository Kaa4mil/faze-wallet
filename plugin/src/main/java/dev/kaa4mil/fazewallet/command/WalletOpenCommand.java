package dev.kaa4mil.fazewallet.command;

import dev.kaa4mil.fazewallet.config.MessageConfig;
import dev.kaa4mil.fazewallet.menu.WalletMenu;
import dev.kaa4mil.fazewallet.util.WalletUtil;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WalletOpenCommand extends Command {

    private final WalletMenu walletMenu;
    private final MessageConfig messageConfig;

    @Inject
    public WalletOpenCommand(@NotNull final WalletMenu walletMenu, @NotNull final MessageConfig messageConfig) {
        super("uslugi");

        this.walletMenu = walletMenu;
        this.messageConfig = messageConfig;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(!(sender instanceof final Player player)) {
            WalletUtil.sendMessage(sender, this.messageConfig.getOnlyPlayerMessage());
            return false;
        }

        this.walletMenu.open(player);
        return true;
    }

}
