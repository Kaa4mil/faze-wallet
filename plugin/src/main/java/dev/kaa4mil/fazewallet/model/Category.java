package dev.kaa4mil.fazewallet.model;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public class Category extends OkaeriConfig {

    private final String name;
    private final ItemStack item;
    private final int size;
    private final int slot;
    private final List<Product> products;
    private final String title;

}
