package dev.kaa4mil.fazewallet.menu;

import dev.kaa4mil.fazewallet.config.CategoryConfig;
import dev.kaa4mil.fazewallet.config.WalletConfig;
import dev.kaa4mil.fazewallet.model.Product;
import dev.kaa4mil.fazewallet.user.User;
import dev.kaa4mil.fazewallet.user.UserManagerImpl;
import dev.kaa4mil.fazewallet.user.UserRepository;
import dev.kaa4mil.fazewallet.util.ItemBuilder;
import dev.kaa4mil.util.ColorTransformer;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WalletMenu implements Listener {

    private final WalletConfig walletConfig;
    private final CategoryConfig categoryConfig;

    private final UserManagerImpl userManager;
    private final UserRepository userRepository;

    private Gui menu;

    public void open(@NotNull Player player) {

        this.menu = Gui.gui()
                .type(GuiType.CHEST)
                .rows(this.walletConfig.getMenuSize() / 9)
                .title(Component.text(ColorTransformer.fix(this.walletConfig.getMenuTitle())))
                .create();

        final User user = this.userRepository.findOrCreateByPath(player.getUniqueId());

        this.walletConfig.getContents().forEach((slot, item) -> {

            if(item.getItemMeta().getLore() != null) {
                item = ItemBuilder.formatItem(item, (int) user.getBalance(), player.getName());
            }

            this.menu.setItem(slot, new GuiItem(item, event -> event.setCancelled(true)));
        });

        this.categoryConfig.getCategories().forEach(category -> {

            final Gui categoryMenu = Gui.gui(GuiType.CHEST)
                    .title(Component.text(ColorTransformer.fix(category.getTitle())))
                    .rows(category.getSize() / 9)
                    .create();

            menu.setItem(category.getSlot(), new GuiItem(category.getItem(), event -> {

                for(Product product : category.getProducts()) {
                    categoryMenu.setItem(product.getSlot(), new GuiItem(product.getItem(), e -> e.setCancelled(true)));
                }

                categoryMenu.open(player);
                event.setCancelled(true);
            }));

        });


        this.menu.open(player);
    }

}
