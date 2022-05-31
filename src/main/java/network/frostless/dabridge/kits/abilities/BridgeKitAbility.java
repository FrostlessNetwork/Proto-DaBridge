package network.frostless.dabridge.kits.abilities;

import network.frostless.dabridge.kits.actions.ActionType;
import org.bukkit.event.Listener;

public interface BridgeKitAbility extends Listener {
    void run();

    String getDescription();
    String getName();

}
