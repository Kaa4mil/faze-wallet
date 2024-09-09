package dev.kaa4mil.fazewallet.persistence;

import eu.okaeri.persistence.PersistenceCollection;
import org.jetbrains.annotations.NotNull;

public interface WalletPersistence {

    void register();
    void addCollections(@NotNull PersistenceCollection... collections);

    void onClose();

}
