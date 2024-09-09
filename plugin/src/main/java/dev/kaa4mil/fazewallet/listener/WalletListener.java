package dev.kaa4mil.fazewallet.listener;

import dev.kaa4mil.fazewallet.user.User;
import dev.kaa4mil.fazewallet.user.UserRepository;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WalletListener implements Listener {

    private final UserRepository userRepository;

    @EventHandler
    void onJoin(@NotNull PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(user -> {
            if(user.getName() == null) {
                user.setName(player.getName());
            }

            user.save();
        });
    }

}
