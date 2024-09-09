package dev.kaa4mil.fazewallet.persistence;

import dev.kaa4mil.fazewallet.WalletPlugin;
import dev.kaa4mil.fazewallet.config.WalletConfig;
import dev.kaa4mil.fazewallet.user.User;
import dev.kaa4mil.fazewallet.user.UserRepository;
import dev.kaa4mil.persistence.PersistenceConnector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.PersistenceCollection;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.repository.RepositoryDeclaration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WalletPersistenceImpl implements WalletPersistence {

    private final WalletPlugin plugin;
    private final PersistenceConnector connector;
    private final WalletConfig walletConfig;

    @Getter private DocumentPersistence persistence;

    @Getter private UserRepository userRepository;
    @Getter private PersistenceCollection userCollection;

    @Override
    public void register() {
        this.persistence = this.connector.openConnection(walletConfig.getPersistenceField());
        this.userCollection = PersistenceCollection.of("users");

        this.userRepository = RepositoryDeclaration.of(UserRepository.class)
                .newProxy(this.persistence, this.userCollection, this.plugin.getClass().getClassLoader());

        this.persistence.registerCollection(this.userCollection);

        this.plugin.registerComponent(this.userRepository);
    }

    @Override
    public void addCollections(@NotNull PersistenceCollection... collections) {
        for(PersistenceCollection collection : collections) {
            this.persistence.registerCollection(collection);
        }
    }

    @Override
    public void onClose() {
        Bukkit.getOnlinePlayers().forEach(player -> this.userRepository.findOrCreate(player.getUniqueId(), player.getName()).thenAcceptAsync(User::save));
    }

}
