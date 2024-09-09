package dev.kaa4mil.fazewallet.serializer;

import dev.kaa4mil.fazewallet.model.Product;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WalletProductSerializer implements ObjectSerializer<Product> {

    @Override
    public boolean supports(@NonNull Class<? super Product> type) {
        return Product.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Product object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("item", object.getItem());
        data.add("name", object.getServiceName());
        data.add("cost", object.getCost());
        data.add("slot", object.getSlot());
        data.add("commands", object.getCommands());
    }

    @Override
    public Product deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new Product(
                data.get("item", ItemStack.class),
                data.get("name", String.class),
                data.get("cost", Double.class),
                data.get("slot", Integer.class),
                data.get("commands", ArrayList.class)
        );
    }
}
