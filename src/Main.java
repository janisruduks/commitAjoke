import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            String getQuote = getData();
            ProcessBuilder processBuilder = new ProcessBuilder("git", "commit", "-m", getQuote);
            processBuilder.start();
            System.out.println("Done...\nRandom quote you got is: " + getQuote);
        } catch (IOException error){
            System.out.println("Error occurred while building terminal command: " + error.getMessage());
        }
    }

    public static String getData() {
        String quote = "";

        try{
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
            String[] getQuote = content.toString().split("\"");

            quote = getQuote[3];

        } catch(IOException error){
            System.out.println("Error occurred while pulling data: " + error.getMessage());
        }
        return quote;
    }
}