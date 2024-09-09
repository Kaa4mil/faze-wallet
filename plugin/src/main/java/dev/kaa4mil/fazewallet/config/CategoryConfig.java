package dev.kaa4mil.fazewallet.config;

import dev.kaa4mil.fazewallet.model.Category;
import dev.kaa4mil.fazewallet.model.Product;
import dev.kaa4mil.fazewallet.util.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
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
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Epicki",
                                    5,
                                    21,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
                            ),
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &6&lLEGENDARNY")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Legendarny",
                                    5,
                                    22,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
                            ),
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &c&lMITYCZNY")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Mityczny",
                                    5,
                                    23,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
                            )
                    ),
                    Map.of()
            ),
            "rangi", new Category(
                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                            .setName("&7Kategoria: &cRangi")
                            .setLore(List.of("&eNacisnij, aby przejsc do kategorii!"))
                            .getItem(),
                    54,
                    21,
                    "&c&lRangi",
                    Collections.singletonList(
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &6&lLEGENDARNY")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Legendarny",
                                    5,
                                    21,
                                    List.of("say {PLAYER} dzieki za kupno klucza!")
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

    public int returnButtonSlot = 49;

}
