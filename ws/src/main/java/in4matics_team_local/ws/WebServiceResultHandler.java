package in4matics_team_local.ws;

/**
 * Created by Matija Popijač on 7.11.2015..
 */
public interface WebServiceResultHandler {
    public void handleResult(
            String result,
            boolean ok,
            long timestamp
    );
}
