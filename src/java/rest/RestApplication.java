package rest;

import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("resources")
public class RestApplication extends Application{
    private final Set<Object> singletons = new HashSet<>();
    
    public RestApplication() {
        singletons.add(new ClientesResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
    
}
