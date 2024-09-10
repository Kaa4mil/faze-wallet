package dev.kaa4mil.fazewallet.menu;

import dev.kaa4mil.fazewallet.config.CategoryConfig;
import dev.kaa4mil.fazewallet.config.MessageConfig;
import dev.kaa4mil.fazewallet.config.WalletConfig;
import dev.kaa4mil.fazewallet.user.User;
import dev.kaa4mil.fazewallet.user.UserManagerImpl;
import dev.kaa4mil.fazewallet.user.UserRepository;
import dev.kaa4mil.fazewallet.util.ItemBuilder;
import dev.kaa4mil.fazewallet.util.WalletUtil;
import dev.kaa4mil.util.ColorTransformer;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WalletMenu implements Listener {

    private final WalletConfig walletConfig;
    private final CategoryConfig categoryConfig;
    private final MessageConfig messageConfig;

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

            if (Objects.requireNonNull(item.getItemMeta()).getLore() != null) {
                item = ItemBuilder.formatItem(item, (int) user.getBalance(), player.getName());
            }

            this.menu.setItem(slot, new GuiItem(item, event -> event.setCancelled(true)));
        });

        if (!this.walletConfig.isEnableCategories()) {

            this.walletConfig.getProducts().forEach(product -> {

                final ItemStack item = ItemBuilder.formatProduct(product.getItem(), product.getCost(), user.getBalance(), player.getName());

                this.menu.setItem(product.getSlot(), new GuiItem(item, event -> {

                    player.closeInventory();
                    event.setCancelled(true);

                    if(user.getBalance() < product.getCost()) {
                        WalletUtil.sendMessage(player, this.messageConfig.getPriceIsHigher(), Map.of("PRICE", product.getCost() - user.getBalance()));
                        return;
                    }

                    this.userManager.buyProduct(player, product);

                    product.getCommands().forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command
                            .replace("{PLAYER}", player.getName())
                    ));

                }));

                this.menu.open(player);
            });

            return;
        }

        this.categoryConfig.getCategories().forEach((name, category) -> {

            final Gui categoryMenu = Gui.gui(GuiType.CHEST)
                    .title(Component.text(ColorTransformer.fix(category.getTitle())))
                    .rows(category.getSize() / 9)
                    .create();

            menu.setItem(category.getSlot(), new GuiItem(category.getItem(), event -> {

                category.getContents().forEach((slot, item) -> {
                    final ItemStack formatted = ItemBuilder.formatItem(item, user.getBalance(), player.getName());

                    categoryMenu.setItem(slot, new GuiItem(formatted));
                });

                category.getProducts().forEach(product -> {
                    final ItemStack item = ItemBuilder.formatProduct(product.getItem(), product.getCost(), user.getBalance(), player.getName());

                    categoryMenu.setItem(product.getSlot(), new GuiItem(item, e -> {

                        player.closeInventory();
                        event.setCancelled(true);

                        if(user.getBalance() < product.getCost()) {
                            WalletUtil.sendMessage(player, this.messageConfig.getPriceIsHigher(), Map.of("PRICE", product.getCost() - user.getBalance()));
                            return;
                        }

                        this.userManager.buyProduct(player, product);

                        product.getCommands().forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command
                                .replace("{PLAYER}", player.getName())
                        ));

                    }));

                });

                categoryMenu.setItem(this.categoryConfig.getReturnButtonSlot(), new GuiItem(this.categoryConfig.getReturnButton(), e -> {
                    menu.open(player);
                    e.setCancelled(true);
                }));

                categoryMenu.open(player);
                event.setCancelled(true);
            }));


            this.menu.open(player);
        });
    }

}
