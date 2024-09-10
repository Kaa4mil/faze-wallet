package dev.kaa4mil.fazewallet.config;

import dev.kaa4mil.fazewallet.model.Category;
import dev.kaa4mil.fazewallet.model.Product;
import dev.kaa4mil.fazewallet.util.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@SuppressWarnings("all")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class CategoryConfig extends OkaeriConfig {

    public Map<String, Category> categories = new HashMap<>(Map.of(
            "klucze", new Category(
                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                            .setName("&7Kategoria: &6&lKlucze")
                            .setLore(List.of("&eNacisnij, aby przejsc do kategorii!"))
                            .getItem(),
                    54,
                    21,
                    "&6&lKlucze",
                    List.of(
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &5&lEPICKI")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Epicki",
                                    5,
                                    21,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
                            ),
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &6&lLEGENDARNY")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Legendarny",
                                    10,
                                    22,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
                            ),
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &c&lMITYCZNY")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Mityczny",
                                    15,
                                    23,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
                            )
                    ),
                    Map.of()
            ),
            "rangi", new Category(
                    new ItemBuilder(Material.TNT)
                            .setName("&7Kategoria: &c&lRangi")
                            .setLore(List.of("&eNacisnij, aby przejsc do kategorii!"))
                            .getItem(),
                    54,
                    22,
                    "&c&lRangi",
                    List.of(
                            new Product(
                                    new ItemBuilder(Material.IRON_HELMET)
                                            .setName("&7Ranga: &e&LVIP &8(&7Na tryb&8)")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Ranga VIP",
                                    10,
                                    21,
                                    List.of("say {PLAYER} dzieki za kupno rangi!")
                            ),
                            new Product(
                                    new ItemBuilder(Material.GOLDEN_HELMET)
                                            .setName("&7Ranga: &6&LSVIP &8(&7Na tryb&8)")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Ranga SVIP",
                                    20,
                                    22,
                                    List.of("say {PLAYER} dzieki za kupno rangi!")
                            ),
                            new Product(
                                    new ItemBuilder(Material.NETHERITE_HELMET)
                                            .setName("&7Ranga: &5&lElita &8(&7Na tryb&8)")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Ranga Elita",
                                    30,
                                    23,
                                    List.of("say {PLAYER} dzieki za kupno rangi!")
                            )
                    ),
                    Map.of()
            ),
            "waluty", new Category(
                    new ItemBuilder(Material.PAPER)
                            .setName("&7Kategoria: &a&lPrzedmioty serwerowe")
                            .setLore(List.of("&eNacisnij, aby przejsc do kategorii!"))
                            .getItem(),
                    54,
                    23,
                    "&c&lRangi",
                    List.of(
                            new Product(
                                    new ItemBuilder(Material.DIAMOND_SWORD)
                                            .setName("&b&lDiamentowy miecz")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij lewym, aby zakupic usluge!"))
                                            .getItem(),
                                    "Diamentowy Miecz",
                                    2,
                                    22,
                                    List.of("say {PLAYER} dzieki za kupno miecza!", "give {PLAYER} diamond_sword 1")
                            )
                    ),
                    Map.of()
            )
    ));

    @Comment({ "","Wyglad przycisku powrotu do menu glownego" })
    public ItemStack returnButton = new ItemBuilder(Material.RED_BED)
            .setName("&c&lPowrot")
            .setLore(List.of("&eNacisnij, aby wrocic do menu glownego!"))
            .getItem();

    public int returnButtonSlot = 40;

}
