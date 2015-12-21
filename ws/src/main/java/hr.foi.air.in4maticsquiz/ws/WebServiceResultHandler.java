package hr.foi.air.in4maticsquiz.ws;

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
