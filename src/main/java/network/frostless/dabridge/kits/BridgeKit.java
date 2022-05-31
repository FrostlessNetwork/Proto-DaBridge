package network.frostless.dabridge.kits;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import network.frostless.dabridge.kits.abilities.BridgeKitAbility;
import network.frostless.dabridge.user.BridgeUser;
import network.frostless.glacier.persisters.SerializableCollectionsType;


import java.util.List;


@DatabaseTable(tableName = "thebridge_kits")
public class BridgeKit {

    @DatabaseField(generatedId = true)
    public long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public BridgeUser user;

    @DatabaseField
    public String name;

    @DatabaseField
    public int price;

    @DatabaseField
    public int maxCap;

    @DatabaseField
    public BridgeKitClass kitClass;

    @DatabaseField(persisterClass = SerializableCollectionsType.class)
    public List<BridgeKitAbility> abilities;

    enum BridgeKitClass {
        OFFENSIVE,
        DEFENSIVE,
        SUPPORT,
        RANGED,
        SPECIALIST,
        CUSTOM
    }
}