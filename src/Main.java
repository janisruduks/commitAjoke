import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("git", "commit", "-m", getData());
    }

    public static String getData() throws IOException {
        URL url = new URL("https://api.gameofthronesquotes.xyz/v1/random");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputline;
        StringBuilder content = new StringBuilder();
        while((inputline = in.readLine()) != null) {
            content.append(inputline);
        }

        in.close();
        connection.disconnect();

        String[] contentful = content.toString().split("\"");

        return contentful[3];
    }
}