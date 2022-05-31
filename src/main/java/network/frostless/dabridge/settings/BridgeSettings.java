package network.frostless.dabridge.settings;

import java.util.concurrent.TimeUnit;

public class BridgeSettings {

    public BridgeOreSettings oreSettings;
    public long gracePeriodDuration = TimeUnit.MINUTES.toMillis(5);

}
