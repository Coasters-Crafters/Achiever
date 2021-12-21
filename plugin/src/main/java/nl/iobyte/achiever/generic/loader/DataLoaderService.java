package nl.iobyte.achiever.generic.loader;

import nl.iobyte.achiever.generic.loader.interfaces.ILoader;
import nl.iobyte.achiever.generic.loader.objects.DatabaseLoader;
import nl.iobyte.achiever.generic.service.IService;
import java.util.ArrayList;
import java.util.Collection;

public class DataLoaderService implements IService {

    private final Collection<ILoader> loaders = new ArrayList<>();

    public DataLoaderService() {
        loaders.add(new DatabaseLoader());
        //TODO Register loaders
    }

    /**
     * {@inheritDoc}
     */
    public void start() {
        loaders.forEach(ILoader::load);
    }

}
