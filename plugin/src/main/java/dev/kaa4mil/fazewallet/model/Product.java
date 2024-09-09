package dev.kaa4mil.fazewallet.model;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public class Product {

    private final ItemStack item;
    private final String serviceName;
    private final double cost;
    private final int slot;
    private final List<String> commands;

}
