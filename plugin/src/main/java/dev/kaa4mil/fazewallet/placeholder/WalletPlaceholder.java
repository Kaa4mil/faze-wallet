package dev.kaa4mil.fazewallet.placeholder;

import dev.kaa4mil.fazewallet.WalletPlugin;
import dev.kaa4mil.fazewallet.user.User;
import dev.kaa4mil.fazewallet.user.UserRepository;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WalletPlaceholder extends PlaceholderExpansion {

    private final WalletPlugin plugin;
    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if(player == null || !params.equals("balance"))
            return null;

        final User user = this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).get();
        return String.valueOf(user.getBalance());
    }

    @Override
    public @NotNull String getIdentifier() {
        return this.plugin.getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

}
