package Task4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HealthyInteger implements HealthyCheck<Integer> {

    public static String ILLEGAL_ARGUMENT = "Threshold cannot be null!";

    private final List<Integer> objects;

    public HealthyInteger(List<Integer> objects) {
        this.objects = objects;
    }

    @Override
    public List<Integer> getOnlyHealthy(Integer threshold) {
        if (threshold == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        Predicate<Integer> predicate = i -> i > threshold * threshold;
        return objects.stream().filter(predicate).collect(Collectors.toList());
    }
}
