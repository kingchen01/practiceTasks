package Task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class HealthyCheckImplUnitTest {

    @Nested
    @DisplayName("test HealthyString class implementation")
    class HealthyStringCases {
        private List<String> objects;
        private HealthyString healthyString;

        private final String THRESHOLD_LONG = "THRESHOLD123456789123456789";
        private final String THRESHOLD = "THRESHOLD";
        private final String THRESHOLD_SHORT = "a";

        private final String INPUT_1 = "away";
        private final String INPUT_2 = "dictionary";
        private final String INPUT_3 = "OTHER";
        private final String INPUT_4 = "ooo123ccc4444b";
        private final String INPUT_5 = "123";
        private final String INPUT_6 = "b";

        @BeforeEach
        void prepare() {
            objects = List.of(INPUT_1, INPUT_2, INPUT_3, INPUT_4, INPUT_5, INPUT_6);
            healthyString = new HealthyString(objects);
        }

        @ParameterizedTest
        @DisplayName("should correctly handle returning only healthy objects from a given list")
        @CsvSource({", 6", "'',0", THRESHOLD_LONG + ",6", THRESHOLD + ",4", THRESHOLD_SHORT + ",0"})
        void shouldReturnHealthyObjects(String threshold, String size) {
            //when
            List<String> onlyHealthy = healthyString.getOnlyHealthy(threshold);

            //then
            assertThat(onlyHealthy).hasSize(Integer.parseInt(size));
        }

        @Test
        @DisplayName("should return 4 healthy objects from a list")
        void shouldReturnHealthyObjects() {
            //when
            List<String> onlyHealthy = healthyString.getOnlyHealthy(THRESHOLD);

            //then
            assertThat(onlyHealthy).hasSize(4)
                    .doesNotContain(INPUT_4)
                    .doesNotContain(INPUT_2);
        }
    }

    @Nested
    @DisplayName("test HealthyInteger class implementation")
    class HealthyInteger {

    }
}
