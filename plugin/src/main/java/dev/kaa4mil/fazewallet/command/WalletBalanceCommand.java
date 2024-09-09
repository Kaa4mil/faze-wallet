package dev.kaa4mil.fazewallet.command;

import dev.kaa4mil.fazewallet.config.MessageConfig;
import dev.kaa4mil.fazewallet.user.UserRepository;
import dev.kaa4mil.fazewallet.util.WalletUtil;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class WalletBalanceCommand extends Command {

    private final UserRepository userRepository;
    private final MessageConfig messageConfig;

    @Inject
    public WalletBalanceCommand(@NotNull final UserRepository userRepository, @NotNull MessageConfig messageConfig) {
        super("wallet");
        this.userRepository = userRepository;
        this.messageConfig = messageConfig;
        this.setAliases(List.of("portfel"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {

        if(!(sender instanceof final Player player)) {
            WalletUtil.sendMessage(sender, this.messageConfig.getOnlyPlayerMessage());
            return false;
        }

        this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(user ->
                WalletUtil.sendMessage(player, this.messageConfig.getBalanceMessage(), Map.of("BALANCE", user.getBalance())));
        return true;
    }

}
