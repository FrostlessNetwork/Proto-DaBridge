package network.frostless.dabridge.events;

import network.frostless.dabridge.BridgeGame;
import network.frostless.glacier.utils.Pair;
import network.frostless.glacierapi.game.event.GameEvent;

import java.util.concurrent.TimeUnit;

public class GracePeriodEvent implements GameEvent<BridgeGame> {

    @Override
    public void execute(BridgeGame game) {

    }

    @Override
    public Pair<Integer, TimeUnit> getDelay() {
        return Pair.of(5, TimeUnit.MINUTES);
    }

    @Override
    public String getName() {
        return "Grace Period";
    }
}
