package dev.kaa4mil.fazewallet.serializer;

import dev.kaa4mil.fazewallet.model.Category;
import dev.kaa4mil.fazewallet.model.Product;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class WalletCategorySerializer implements ObjectSerializer<Category> {


    @Override
    public boolean supports(@NonNull Class<? super Category> type) {
        return Category.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Category object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("item", object.getItem());
        data.add("size", object.getSize());
        data.add("slot", object.getSlot());
        data.add("title", object.getTitle());
        data.add("products", object.getProducts());
        data.add("contents", object.getContents());
    }

    @Override
    public Category deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new Category(
                data.get("item", ItemStack.class),
                data.get("size", Integer.class),
                data.get("slot", Integer.class),
                data.get("title", String.class),
                data.getAsList("products", Product.class),
                data.get("contents", Map.class)
        );
    }
}
