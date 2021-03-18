package Task4;

import java.util.List;

/*
Design a generic interface with one method "getOnlyHealthy" that returns a List<T> object.
This method should filter objects from given List<T> based on a single object T that we call threshold (and which is passed as an argument).

Implement and test this interface in at least 2 and at most 4 classes, where T is one of the following: Integer, Boolean, String, Cat or any other non java defined class.
Each class should have one field: list of all its elements.
They way "getOnlyHealthy" is implemented depends on given class and should be different for each one (just for testing purposes).

Examples of logic of getOnlyHealthy:
1)	healthy are only objects that are greater than square root of threshold
2)	healthy are only objects that have length shorter than threshold
3)	healthy are only objects that are equal to threshold

Bonus: add 2nd method to the interface: getNumberOfHealthy that will return count of healthy objects.
Is it possible not to have this method implemented in every class and still be usable by them?
 */
public interface HealthyCheck<T> {

    List<T> getOnlyHealthy(T threshold);

    default int getNumberOfHealthyObjects(T threshold) {
        return getOnlyHealthy(threshold).size();
    }
}
