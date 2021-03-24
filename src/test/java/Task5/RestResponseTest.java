package Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestResponseTest {

    @Mock
    CustomRest customRest;

    @InjectMocks
    RestResponse restResponse;

    private static Stream<Arguments> provideParametersForResponseCode200() {
        return Stream.of(
                Arguments.of(200, List.of(1, 2, 3, 4, 5, 15, -5), 1, List.of(25)),
                Arguments.of(200, List.of(1, 2, 3, -20, 0), 5, List.of(100, 200, 300, -2000, 0)),
                Arguments.of(200, List.of(-12, 9, 0), 3, List.of(-1200, 900, 0)),
                Arguments.of(200, Collections.emptyList(), 0, Collections.emptyList())
        );
    }

    @Test
    @DisplayName("should return null when response code is 404")
    void shouldReturnNull_whenGettingRestResponse_withResponseCode404() {
        //given
        when(customRest.getAll()).thenReturn(new CustomResponse(404, Collections.emptyList()));

        //when
        List<Integer> values = restResponse.getRestResponse();

        //then
        assertThat(values).isNull();
    }

    @Test
    @DisplayName("should return an empty list when response code is 301")
    void shouldReturnEmptyList_whenGettingRestResponse_withResponseCode301() {
        //given
        when(customRest.getAll()).thenReturn(new CustomResponse(301, Collections.emptyList()));

        //when
        List<Integer> values = restResponse.getRestResponse();

        //then
        assertThat(values).isNotNull()
                          .isEmpty();
    }

    @ParameterizedTest
    @DisplayName("should return a sum of values when response code is 200 and input list has 5 elements OR return " +
            "list of values multiplied by 100 when input list has 5 or fewer elements")
    @MethodSource("Task5.RestResponseTest#provideParametersForResponseCode200")
    void shouldHandleGettingRestResponse_whenResponseCode200(int responseCode, List<Integer> values, int expectedSize,
                                                          List<Integer> expectedValues) {
        //given
        when(customRest.getAll()).thenReturn(new CustomResponse(responseCode, values));

        //when
        List<Integer> result = restResponse.getRestResponse();

        //then
        assertThat(result).isNotNull()
                          .hasSize(expectedSize)
                          .isEqualTo(expectedValues);
    }
}
