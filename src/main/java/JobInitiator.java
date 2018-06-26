import java.net.HttpURLConnection;
import java.net.URL;

public class JobInitiator {

    /**
     * A simple class designed to ping a URL, and disconnect from it without waiting for a response.
     * It's useful for initiating a potentially long-running process that runs within an HTTP servlet
     * container, with precise control over timeout behavior.
     *
     * A typical use case is to invoke this program via cron, to ensure that a process executes on a
     * specified schedule.
     *
     * Two arguments are required:
     * 1. URL - the full URL of Job Initiation endpoint, i.e. http://my.domain.com:8080/DoWork
     * 2. read timeout - the timeout of the URL read in milliseconds...set this very short, like 2 seconds
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            URL url = new URL(args[0]);
            Integer timeout = Integer.parseInt(args[1]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(timeout);
            con.getContent();
        } catch(java.net.SocketTimeoutException e) {
            // benign...we expect this to timeout
        }
    }
}
