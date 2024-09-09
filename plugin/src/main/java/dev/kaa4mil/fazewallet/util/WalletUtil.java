package dev.kaa4mil.fazewallet.util;

import dev.kaa4mil.util.ColorTransformer;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WalletUtil {

    public static void sendMessage(@NotNull final CommandSender sender, @NotNull final String message) {
        sender.sendMessage(ColorTransformer.fix(message));
    }

    public static void sendMessage(@NotNull final CommandSender sender, @NotNull String message, @NotNull final Map<String, Object> replacer) {
        for(Map.Entry<String, Object> entry : replacer.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        sendMessage(sender, message);
    }

}
