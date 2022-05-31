package network.frostless.dabridge.map;

import com.google.gson.JsonElement;
import network.frostless.glacier.map.MapManager;

public class BridgeMapManager extends MapManager<BridgeMapMeta> {

    public BridgeMapManager() {
        super("thebridge");
    }

    @Override
    protected BridgeMapMeta deserializeMapMeta(JsonElement parentObject) {
        return gson.fromJson(parentObject.getAsJsonObject(), BridgeMapMeta.class);
    }
}
