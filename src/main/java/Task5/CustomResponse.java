package Task5;

import java.util.List;

public class CustomResponse {

    private final int responseCode;
    private List<Integer> values;

    public CustomResponse(int responseCode, List<Integer> values) {
        this.responseCode = responseCode;
        this.values = values;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<Integer> getValues() {
        return values;
    }

}
