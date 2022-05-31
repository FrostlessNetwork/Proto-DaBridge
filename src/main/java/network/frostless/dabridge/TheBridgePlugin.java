package network.frostless.dabridge;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import network.frostless.dabridge.kits.BridgeKit;
import network.frostless.dabridge.lobby.BridgeLobby;
import network.frostless.dabridge.map.BridgeMapManager;
import network.frostless.dabridge.team.BridgeTeam;
import network.frostless.dabridge.user.BridgeUser;
import network.frostless.glacier.Glacier;
import network.frostless.glacier.app.GlacierCoreGameLoader;
import network.frostless.glacierapi.game.GameType;

import java.sql.SQLException;
import java.util.UUID;

public class TheBridgePlugin extends GlacierCoreGameLoader<BridgeUser, BridgeTeam> {
    @Override
    public void init() {
        Glacier.setGameType(GameType.THEBRIDGE);


        glacierAPI.setLobby(new BridgeLobby());
        glacierAPI.setMapManager(new BridgeMapManager());
        glacierAPI.getGameManager().createGame(new BridgeGame());
        glacierAPI.getGameManager().createGame(new BridgeGame());
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void registerPreConnections() {
        ConnectionSource connectionSource = Glacier.get().getFrostbite().getUserManager().getConnectionSource();
        try {
            Dao<BridgeKit, Long> kitDao = DaoManager.createDao(connectionSource, BridgeKit.class);
            if (!kitDao.isTableExists()) TableUtils.createTable(kitDao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BridgeUser createUser(String username, UUID uuid) {
        return new BridgeUser(uuid);
    }

    @Override
    public Class<BridgeUser> getUserClass() {
        return BridgeUser.class;
    }
}
