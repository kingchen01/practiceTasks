package Task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class HealthyCheckImplUnitTest {

    @Nested
    @DisplayName("test HealthyString class implementation")
    class HealthyStringCases {

        private final String THRESHOLD_LONGER = "THRESHOLD123456789123456789";
        private final String THRESHOLD_LONG = "THRESHOLD";
        private final String THRESHOLD_SHORT = "a";
        private final String INPUT_1 = "away";
        private final String INPUT_2 = "dictionary";
        private final String INPUT_3 = "OTHER";
        private final String INPUT_4 = "ooo123ccc4444b";
        private final String INPUT_5 = "123";

        private List<String> objects;
        private HealthyString healthyString;

        @BeforeEach
        void prepare() {
            objects = List.of(INPUT_1, INPUT_2, INPUT_3, INPUT_4, INPUT_5);
            healthyString = new HealthyString(THRESHOLD_LONG, objects);
        }

        @Test
        @DisplayName("should correctly handle returning only healthy objects from a given list")
        void shouldReturnHealthyObjects() {
            //when
            List<String> onlyHealthy = healthyString.getOnlyHealthy();

            //then
            assertThat(onlyHealthy).hasSize(3)
                                    .contains(INPUT_5)
                                    .contains(INPUT_3)
                                    .contains(INPUT_1);
        }
    }

    @Nested
    @DisplayName("test HealthyInteger class implementation")
    class HealthyInteger {

    }
}
