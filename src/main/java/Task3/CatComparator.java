package Task3;

import java.util.Comparator;

public class CatComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        int livesCompared = (-1) * Integer.compare(o1.getLives(), o2.getLives());
        if (livesCompared == 0) {
            return Integer.compare(o1.getName().length(), o2.getName().length());
        }
        return livesCompared;
    }
}
