package avalith.quevedo.photo.domain;

/***
 * Class that defines a common response for the services of the application. The field isSuccessful defines whether the data access operation was completed or not. If it is completed there is an object with the information retrieved. If that is not the case there is an error message in the error attribute.
 * @param <T> Any class that is the result of a data access operation or its transformation.
 */
public class ServerResponse<T> {
    private boolean isSuccessful;
    private T object;
    private ServerError error;

    public ServerResponse(){
        this.isSuccessful = false;
    }

    public ServerResponse(boolean isSuccessful, T object, ServerError error) {
        this.isSuccessful = isSuccessful;
        this.object = object;
        this.error = error;
    }

    public ServerError getError() {
        return error;
    }

    public void setError(ServerError error) {
        this.error = error;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
