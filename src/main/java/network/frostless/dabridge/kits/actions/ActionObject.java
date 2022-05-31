package network.frostless.dabridge.kits.actions;


import lombok.Data;

@Data
public class ActionObject {

    private int cooldown;
    private ActionType triggerBy;
    private int charge;
}
