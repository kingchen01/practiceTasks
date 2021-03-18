package Task4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HealthyString implements HealthyCheck<String> {

    private final List<String> objects;

    public HealthyString(List<String> objects) {
        this.objects = objects;
    }

    @Override
    public List<String> getOnlyHealthy(String threshold) {
        if (threshold != null) {
            Predicate<String> predicate = s -> s.length() < threshold.length();
            return this.objects.stream().filter(predicate).collect(Collectors.toList());
        }
        else {
            return  objects;
        }
    }

    public List<String> getObjects() {
        return objects;
    }
}
