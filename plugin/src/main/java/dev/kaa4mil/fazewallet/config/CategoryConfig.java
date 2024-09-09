package dev.kaa4mil.fazewallet.config;

import dev.kaa4mil.fazewallet.model.Category;
import dev.kaa4mil.fazewallet.model.Product;
import dev.kaa4mil.fazewallet.util.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Collections;
import java.util.List;

@Getter
public class CategoryConfig extends OkaeriConfig {

    public List<Category> categories = List.of(
            new Category(
                    "klucze",
                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                            .setName("&7Kategoria: &6Klucze")
                            .setLore(List.of("&eNacisnij, aby przejsc do kategorii!"))
                            .getItem(),
                    47,
                    21,
                    Collections.singletonList(
                            new Product(
                                    new ItemBuilder(Material.TRIPWIRE_HOOK)
                                            .setName("&7Klucz: &6&lLEGENDARNY")
                                            .setLore(List.of("", "&7Koszt uslugi: &e{PRICE}zl", "&7Co otrzymasz?", "&8- Wstaw twoje informacje...", "", "&aKliknij, aby zakupic usluge!"))
                                            .getItem(),
                                    "Klucz Legendarny",
                                    5,
                                    21,
                                    Collections.singletonList("say {PLAYER} dzieki za kupno klucza!")
                            )
                    ),
                    "&7Kategoria: &6Klucze"
            )
    );

}
