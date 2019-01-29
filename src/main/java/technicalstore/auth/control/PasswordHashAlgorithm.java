package technicalstore.auth.control;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.PasswordHash;

@ApplicationScoped
public class PasswordHashAlgorithm {
    @Inject
    private PasswordHash passwordHash;

    public PasswordHash get() {
        return passwordHash;
    }
}
