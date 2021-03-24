package Task5;

public interface CustomRest {

    default CustomResponse getAll()
    {
        return null;
    }
}
