package Task5;

import java.util.ArrayList;
import java.util.List;

public class RestResponse implements CustomRest {

    public List<Integer> getRestResponse() {
        CustomResponse customResponse = getAll();

        if (customResponse.getResponseCode() == 404) {
            return null;
        }
        else if (customResponse.getResponseCode() == 301) {
            return new ArrayList<>();
        }
        else if (customResponse.getResponseCode() == 200) {
            return new ArrayList<>();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
