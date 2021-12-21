package nl.iobyte.achiever.generic.loader.objects;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.database.DatabaseService;
import nl.iobyte.achiever.generic.database.IDatabaseService;
import nl.iobyte.achiever.generic.database.objects.types.SQLiteDatabase;
import nl.iobyte.achiever.generic.loader.interfaces.ILoader;
import java.io.File;

public class DatabaseLoader implements ILoader {

    /**
     * {@inheritDoc}
     */
    public void load() {
        DatabaseService service = (DatabaseService) Achiever.service(IDatabaseService.class);
        service.add(new SQLiteDatabase(
                "local",
                new File(
                        Achiever.getInstance().getDataFolder(),
                        "data.db"
                )
        ));

        //TODO MySQL
    }

}
