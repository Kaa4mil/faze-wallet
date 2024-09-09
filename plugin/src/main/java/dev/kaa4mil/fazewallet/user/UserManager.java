package dev.kaa4mil.fazewallet.user;

import dev.kaa4mil.fazewallet.model.Product;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface UserManager {

    void buyProduct(@NotNull Player player, @NotNull Product product);
    void addBalance(@NotNull Player player, double amount);
    void removeBalance(@NotNull Player player, double amount);
    void setBalance(@NotNull Player player, double amount);

}
