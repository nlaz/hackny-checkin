package hackny.checkin;

import java.util.List;

import hackny.checkin.models.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by nlazaris on 7/31/15.
 */
public interface CheckInService {
    @GET("/api/v1/users")
    void listUsers(@Query("client_id") String client_id, @Query("secret") String secret, Callback<List<User>> cb);
}
