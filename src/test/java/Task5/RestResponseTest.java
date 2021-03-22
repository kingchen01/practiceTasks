package Task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RestResponseTest {

    @Mock
    CustomRest customRest;

    RestResponse restResponse;

    @BeforeEach
    void prepare() {
        restResponse = new RestResponse();
    }

    @Test
    @DisplayName("should return null when response code is 404")
    void whenResponseCodeIs404_thenReturnNull() {
        //given
        when(customRest.getAll()).thenReturn(new CustomResponse(404));

        //when
        List<Integer> values = restResponse.getRestResponse();

        //then
        assertThat(restResponse.getRestResponse()).isNull();
    }
}
