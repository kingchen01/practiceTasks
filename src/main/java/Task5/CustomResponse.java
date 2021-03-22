package Task5;

import java.util.List;

public class CustomResponse {

    private final int responseCode;
    private List<Integer> values;

    public CustomResponse(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
