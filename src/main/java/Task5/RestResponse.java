package Task5;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
Let’s have an interface CustomRest with 1 method getAll(). This method returns an instance of a class CustomResponse that has the following fields:
1)	int responseCode - possible values 200, 301, 404
2)	List<Integer> values (list is only populated when response code is 200)


Design and test a class that has one method: getRestResponse() which uses method getAll() and based on the response from that method, has the following logic:
1)	if response code is 404 returns null
2)	if the response code is 301 returns empty list
3)	if response code is 200 and:
    a)	and number of elements is greater than 5 it uses a consumer functional interface to return a list of one value that is the sum of all elements returned by getAll() method
    b)	and number of elements is less or equal to 5 it uses a functional interface to apply operation to multiple every element by 100 and return the new values as a list
    c)	use a consumer interface to log out each list element separately in the following format: Received value {} at position {}
 */
public class RestResponse {

    private final CustomRest customRest;
    private static final Logger LOGGER = Logger.getLogger(RestResponse.class.getName());

    public RestResponse(CustomRest customRest) {
        this.customRest = customRest;
    }

    public List<Integer> getRestResponse() {
        CustomResponse customResponse = customRest.getAll();

        if (customResponse.getResponseCode() == 404) {
            return null;
        }

        else if (customResponse.getResponseCode() == 301) {
            return Collections.emptyList();
        }

        else {
            List<Integer> values = customResponse.getValues();
            values.forEach(log(values));

            if (values.size() > 5) {
                Integer sum = sumValues().apply(values);
                return Collections.singletonList(sum);
            }

            else {
                return multipleElements(100).apply(values);
            }
        }
    }

    private Consumer<Integer> log(List<Integer> values) {
        return v -> LOGGER.info(String.format("Received value %s at " +
                "position %s", v, values.indexOf(v)));
    }

    private Function<List<Integer>, Integer> sumValues() {
        return list -> list.stream()
                            .mapToInt(Integer::intValue)
                            .sum();
    }

    private Function<List<Integer>, List<Integer>> multipleElements (int multiplier) {
        return list -> list.stream()
                            .map(e -> e * multiplier)
                            .collect(Collectors.toList());
    }
}
