package network.frostless.dabridge.kits;

import java.util.HashMap;
import java.util.Map;

public class KitManager {

    public Map<Class<? extends BridgeKit>, BridgeKit> bridgeKits = new HashMap<>();


    public KitManager() {

    }

    public void registerKit(BridgeKit kit) {
        bridgeKits.put(kit.getClass(), kit);
    }

    public void unregisterKit(BridgeKit kit) {
        bridgeKits.remove(kit.getClass());
    }

    public void loadAllKits() {

    }
}
