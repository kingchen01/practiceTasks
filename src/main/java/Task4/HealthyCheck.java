package Task4;

import java.util.List;

public interface HealthyCheck<T> {

    List<T> getOnlyHealthy();

    default int getNumberOfHealthyObjects() {
        return getOnlyHealthy().size();
    }
}
