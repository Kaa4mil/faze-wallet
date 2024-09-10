package dev.kaa4mil.fazewallet.config;

import dev.kaa4mil.fazewallet.model.Product;
import dev.kaa4mil.fazewallet.util.ItemBuilder;
import dev.kaa4mil.persistence.field.PersistenceField;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Header("## FazeDev-Wallet-Synchronized ##")
@Header("## Author: Kaa4mil ##")
@SuppressWarnings("all")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class WalletConfig extends OkaeriConfig {

    public PersistenceField persistenceField = new PersistenceField();

    @Comment("Wysylac dodatkowe wiadomosci do konsoli?")
    public boolean debug = true;

    @Comment({"", "Informacje o inventory sklepu"})
    public String menuTitle = "&6&lSklep za walute";

    public int menuSize = 47;

    @Comment({"", "Czy kategorie w sklepie maja byc wlaczone?"})
    public boolean enableCategories = true;

    @Comment({"", "Lista produktow, dziala jesli kategorie sa wylaczone"})
    public List<Product> products = List.of(
            new Product(
                    new ItemBuilder(Material.GOLDEN_HELMET)
                            .setName("&7Ranga: &e&lVIP")
                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                            .getItem(),
                    "Ranga VIP",
                    10,
                    21,
                    List.of("say {PLAYER} dzieki za kupno vipa!")
            ),
            new Product(
                    new ItemBuilder(Material.DIAMOND_HELMET)
                            .setName("&7Ranga: &6&lSVIP")
                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                            .getItem(),
                    "Ranga SVIP",
                    20,
                    22,
                    List.of("say {PLAYER} dzieki za kupno svipa!")
            ),
            new Product(
                    new ItemBuilder(Material.NETHERITE_HELMET)
                            .setName("&7Ranga: &5&lELITA")
                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                            .getItem(),
                    "Ranga Elita",
                    30,
                    23,
                    List.of("say {PLAYER} dzieki za kupno elity!")
            )
    );

    Map<Integer, ItemStack> contents = Stream.of(
            new AbstractMap.SimpleEntry<>(0, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(1, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(2, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(3, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(4, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("&7Nick: &6{PLAYER}")
                    .setLore(List.of("&7Stan konta: &a{BALANCE}zl"))
                    .getItem()),
            new AbstractMap.SimpleEntry<>(5, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(6, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(7, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(8, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(17, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(35, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(26, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(18, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(27, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(36, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(37, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(38, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(39, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(40, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(41, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(42, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(43, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem()),
            new AbstractMap.SimpleEntry<>(44, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).getItem())
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

}
