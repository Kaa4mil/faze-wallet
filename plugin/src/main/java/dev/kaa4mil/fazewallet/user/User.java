package dev.kaa4mil.fazewallet.user;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Document {

    private String name;
    private double balance;

}
