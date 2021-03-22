package Task5;

import java.util.ArrayList;

/*
Let’s have an interface CustomRest with 1 method getAll(). This method returns an instance of a class CustomResponse that has the following fields:
1)	int responseCode - possible values 200, 301, 404
2)	List<Integer> values (list is only populated when response code is 200)


Design and test a class that has one method: getRestResponse() which uses method getAll() and based on the response from that method, has the following logic:
1)	if response code is 404 returns null
2)	if the response code is 301 returns empty list
3)	if response code is 200 and:
a)	number of elements is greater than 5 it uses a consumer to return a list of one value that is the sum of all elements returned by getAll() method
b)	number of elements is less or equal to 5 it uses a functional interface to apply operation to multiple every element by 100 and return the new values as a list
 */
public interface CustomRest {

    default CustomResponse getAll()
    {
        return null;
    }
}
