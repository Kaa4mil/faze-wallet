package dev.kaa4mil.fazewallet.model;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

@Data
public class Category {

    private final ItemStack item;
    private final int size;
    private final int slot;
    private final String title;
    private final List<Product> products;
    private final Map<Integer, ItemStack> contents;

}
