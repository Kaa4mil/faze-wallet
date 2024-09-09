package dev.kaa4mil.fazewallet.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import lombok.Getter;

import java.util.List;

@Getter
@Header("## FazeDev-Wallet-Synchronized ##")
@Header("## Author: Kaa4mil ##")
@Names(strategy = NameStrategy.IDENTITY, modifier = NameModifier.TO_LOWER_CASE)
public class MessageConfig extends OkaeriConfig {

    public String balanceMessage = "&7Twoj stan konta wynosi: &a{BALANCE}zl";
    public String onlyPlayerMessage = "&cKomenda dostepna tylko z poziomu gracza!";
    public String invalidPlayerMessage = "&cGracz o nazwie &4{PLAYER}&c jest aktualnie offline!";

    public String addedBalanceMessage = "&aDodano &2{AMOUNT} &ado balansu gracza &2{PLAYER}";
    public String removeBalanceMessage = "&cZabrano &4{AMOUNT} &cz balansu gracza &4{PLAYER}";
    public String setBalanceMessage = "&aUstawiono balans gracza &2{PLAYER} &ana &2{AMOUNT}";

    public String priceIsHigher = "&cNie stac cie na ten przedmiot, Brakuje ci: &4{PRICE} &c:(";

    public List<String> buyMessages = List.of("&7Gracz: &6{PLAYER} &7zakupil usluge: &e{SERVICE}", "&7Chcesz tez? &7Zrob zakupy pod &6/uslugi&7!");

}
