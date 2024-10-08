package dev.kaa4mil.fazewallet;

import dev.kaa4mil.FazeBukkit;
import dev.kaa4mil.FazeSerdes;
import dev.kaa4mil.fazewallet.command.WalletBalanceCommand;
import dev.kaa4mil.fazewallet.command.WalletManageCommand;
import dev.kaa4mil.fazewallet.command.WalletOpenCommand;
import dev.kaa4mil.fazewallet.config.CategoryConfig;
import dev.kaa4mil.fazewallet.config.MessageConfig;
import dev.kaa4mil.fazewallet.config.WalletConfig;
import dev.kaa4mil.fazewallet.listener.WalletListener;
import dev.kaa4mil.fazewallet.menu.WalletMenu;
import dev.kaa4mil.fazewallet.persistence.WalletPersistenceImpl;
import dev.kaa4mil.fazewallet.placeholder.WalletPlaceholder;
import dev.kaa4mil.fazewallet.serializer.WalletCategorySerializer;
import dev.kaa4mil.fazewallet.serializer.WalletProductSerializer;
import dev.kaa4mil.fazewallet.user.UserManagerImpl;
import eu.okaeri.configs.yaml.bukkit.serdes.serializer.ItemStackSerializer;
import lombok.Getter;

public class WalletPlugin extends FazeBukkit {

    private WalletPersistenceImpl walletPersistence;

    @Getter private WalletConfig walletConfig;
    @Getter private MessageConfig messageConfig;
    @Getter private CategoryConfig categoryConfig;

    @Override
    public void enable() {

        this.registerComponent(this);

        this.walletConfig = this.getConfigLoader().addConfiguration(WalletConfig.class, "config", this, registry -> {
            registry.register(new FazeSerdes());
            registry.register(new WalletProductSerializer());
        });

        this.setDebug(walletConfig.isDebug());

        this.messageConfig = this.getConfigLoader().addConfiguration(MessageConfig.class, "message", this);

        this.categoryConfig = this.getConfigLoader().addConfiguration(CategoryConfig.class, "category", this, registry -> {
            registry.register(new ItemStackSerializer());
            registry.register(new WalletCategorySerializer());
            registry.register(new WalletProductSerializer());
        });

        this.registerComponent(walletConfig);
        this.registerComponent(messageConfig);
        this.registerComponent(categoryConfig);

        this.walletPersistence = this.createInstance(WalletPersistenceImpl.class);
        this.walletPersistence.register();

        final UserManagerImpl userManager = this.createInstance(UserManagerImpl.class);
        this.registerComponent(userManager);

        final WalletMenu walletMenu = this.createInstance(WalletMenu.class);
        this.registerComponent(walletMenu);

        if(this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            final WalletPlaceholder walletPlaceholder = this.createInstance(WalletPlaceholder.class);
            walletPlaceholder.register();
        }

        this.getCommandLoader().loadCommands(
                this.createInstance(WalletOpenCommand.class),
                this.createInstance(WalletBalanceCommand.class),
                this.createInstance(WalletManageCommand.class)
        );

        this.getServer().getPluginManager().registerEvents(this.createInstance(WalletListener.class), this);
    }

    @Override
    public void shutdown() {
        if(this.walletPersistence != null) {
            this.walletPersistence.onClose();
        }
    }

}
