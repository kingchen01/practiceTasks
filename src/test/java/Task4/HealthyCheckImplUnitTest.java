package Task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class HealthyCheckImplUnitTest {

    private static Stream<Arguments> provideParametersHealthyInteger() {
        return Stream.of(
                Arguments.of(-4, 2, List.of(23, 1000)),
                Arguments.of(2, 4, List.of(23, 5, 16, 1000)),
                Arguments.of(0, 5, List.of(23, 4, 5, 16, 1000))
        );
    }

    @Nested
    @DisplayName("HealthyString class implementation")
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
        void shouldReturnHealthyObjects(String threshold, int size) {
            //when
            List<String> onlyHealthy = healthyString.getOnlyHealthy(threshold);

            //then
            assertThat(onlyHealthy).hasSize(size);
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
    @DisplayName("HealthyInteger class implementation")
    class HealthyIntegerCases {

        private List<Integer> objects;
        private HealthyInteger healthyInteger;

        @BeforeEach
        void prepare() {
            objects = List.of(23, -15, 0, 4, 5, 16, 1000);
            healthyInteger = new HealthyInteger(objects);
        }

        @ParameterizedTest
        @DisplayName("should correctly handle returning only healthy objects from a given list")
        @MethodSource("Task4.HealthyCheckImplUnitTest#provideParametersHealthyInteger")
        void shouldReturnHealthyObjects(Integer threshold, int size, List<Integer> expectedHealthy) {
            //when
            List<Integer> actualHealthy = healthyInteger.getOnlyHealthy(threshold);

            //then
            assertThat(actualHealthy).hasSize(size)
                                     .isEqualTo(expectedHealthy);
        }

        @Test
        @DisplayName("should throw IllegalArgumentException when given threshold was null")
        void shouldThrowException_whenNullThreshold() {
            //when then
            assertThatIllegalArgumentException().isThrownBy(() -> healthyInteger.getOnlyHealthy(null))
                                                .withMessage(HealthyInteger.ILLEGAL_ARGUMENT)
                                                .withNoCause();
        }
    }
}
