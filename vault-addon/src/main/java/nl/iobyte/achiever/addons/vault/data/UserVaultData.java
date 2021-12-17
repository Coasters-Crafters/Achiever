package nl.iobyte.achiever.addons.vault.data;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.database.IDatabaseService;
import nl.iobyte.achiever.generic.service.IService;
import nl.iobyte.dataapi.data.DataService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserVaultData extends DataService<UUID, UserEntry> implements IService {

    /**
     * Get entry, or add new
     * @param uuid UUID
     * @return UserEntry
     */
    public UserEntry getOrAdd(UUID uuid) {
        if(has(uuid))
            return get(uuid);

        //Execute query
        Collection<Map<String,Object>> rows = Achiever.service(IDatabaseService.class).executeQuery(
                "local",
                "SELECT deposit,withdraw FROM vault WHERE uuid=? LIMIT 1",//TODO Create table and save data into table
                new HashMap<>(){{
                    put(1, uuid.toString());
                }}
        );

        //Get entry from query result
        UserEntry entry = null;
        if(rows != null)
            for(Map<String,Object> row : rows)
                entry = new UserEntry(
                        uuid,
                        Double.parseDouble((String) row.get("deposit")),
                        Double.parseDouble((String) row.get("withdraw"))
                );

        //Empty if non found
        if(entry == null)
            entry = new UserEntry(
                    uuid,
                    0,
                    0
            );

        add(entry);
        return entry;
    }

}
