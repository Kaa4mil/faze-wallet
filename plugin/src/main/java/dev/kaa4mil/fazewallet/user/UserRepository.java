package dev.kaa4mil.fazewallet.user;

import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@DocumentCollection(path = "users", keyLength = 36)
public interface UserRepository extends DocumentRepository<UUID, User> {

    default CompletableFuture<User> findOrCreate(@NotNull UUID uuid, String name) {
        return CompletableFuture.supplyAsync(() -> {
            User user = this.findOrCreateByPath(uuid);

            if(name != null) {
                user.setName(name);
            }

            return user;
        });
    }

}
