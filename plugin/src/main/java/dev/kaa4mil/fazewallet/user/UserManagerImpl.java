package dev.kaa4mil.fazewallet.user;

import dev.kaa4mil.fazewallet.WalletPlugin;
import dev.kaa4mil.fazewallet.config.MessageConfig;
import dev.kaa4mil.fazewallet.config.WalletConfig;
import dev.kaa4mil.fazewallet.model.Product;
import dev.kaa4mil.fazewallet.util.WalletUtil;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserManagerImpl implements UserManager {

    private final WalletPlugin plugin;
    private final UserRepository userRepository;

    private final WalletConfig walletConfig;
    private final MessageConfig messageConfig;

    @Override
    public void buyProduct(@NotNull Player player, @NotNull Product product) {
        this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(user -> {

            if(user.getBalance() < product.getCost()) {
                WalletUtil.sendMessage(player, this.messageConfig.getPriceIsHigher(), Map.of("PRICE", product.getCost() - user.getBalance()));
                return;
            }

            Bukkit.getOnlinePlayers().forEach(p -> {
               this.messageConfig.getBuyMessages().forEach(message ->
                       WalletUtil.sendMessage(p, message, Map.of(
                               "SERVICE", product.getServiceName(),
                               "PLAYER", player.getName()
                       )));
            });

        });
    }

    @Override
    public void addBalance(@NotNull Player player, double amount) {
        this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(user -> {
            user.setBalance(user.getBalance() + amount);
            user.save();
        });
    }

    @Override
    public void removeBalance(@NotNull Player player, double amount) {
        this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(user -> {
            user.setBalance(user.getBalance() - amount);
            user.save();
        });
    }

    @Override
    public void setBalance(@NotNull Player player, double amount) {
        this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(user -> {
            user.setBalance(amount);
            user.save();
        });
    }


}
