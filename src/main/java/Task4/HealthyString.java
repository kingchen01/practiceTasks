package Task4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HealthyString implements HealthyCheck<String> {

    private final String threshold;
    private final List<String> objects;

    public HealthyString(String threshold, List<String> objects) {
        this.threshold = threshold;
        this.objects = objects;
    }

    @Override
    public List<String> getOnlyHealthy() {
        Predicate<String> predicate = s -> s.length() < this.threshold.length();
        return this.objects.stream().filter(predicate).collect(Collectors.toList());
    }

    public String getThreshold() {
        return threshold;
    }

    public List<String> getObjects() {
        return objects;
    }
}
