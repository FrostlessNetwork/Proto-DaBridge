package network.frostless.dabridge.user;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.LazyForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import network.frostless.dabridge.BridgeGame;
import network.frostless.dabridge.kits.BridgeKit;
import network.frostless.dabridge.team.BridgeTeam;
import network.frostless.glacier.user.impl.GameUserImpl;
import network.frostless.glacierapi.user.GameUser;

import java.sql.SQLException;
import java.util.UUID;


public class BridgeUser extends GameUserImpl<BridgeGame> {

    @ForeignCollectionField(eager = true)
    private ForeignCollection<BridgeKit> unlockedKits;


    public BridgeUser() {
    }

    public BridgeUser(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onStartGame() {
        // leveling system for teams but for now lets just randomize
        BridgeTeam team = ((BridgeTeam) getGame().getRandomTeam());

        if (team != null) {
            team.addPlayer(this);

            getPlayer().teleport(team.getSpawnPoint());
            // give kits
        }
    }


    public void giveKit(Dao<BridgeUser, Long> userDao, BridgeKit kit) throws SQLException {
        userDao.assignEmptyForeignCollection(this, "unlockedKits");
        unlockedKits.add(kit);
    }

    @Override
    public <U extends GameUser> boolean areTeamMates(U user) {
        return getGame().getTeamForPlayer(this).isPartOfTeam(user.getPlayer());
    }
}
