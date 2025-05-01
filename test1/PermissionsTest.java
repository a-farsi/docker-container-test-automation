import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PermissionsTest {
    public static void main(String[] args) {
        try {
            // Récupérer les variables d'environnement pour l'adresse et le port de l'API
            String apiAddress = System.getenv("API_ADDRESS");
            String apiPort = System.getenv("API_PORT");

            // Construire l'URL pour faire la requête HTTP
            String apiUrl = "http://" + apiAddress + ":" + apiPort + "/permissions?username=alice&password=wonderland";
            System.out.println("The URL: " + apiUrl);

            // Exécution de la requête GET
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Récupération du code de réponse HTTP
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Lecture de la réponse de l'API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Formatage de la sortie similaire au script Python
            String output = "\n============================\n" +
                            "    Authentication Test\n" +
                            "============================\n" +
                            "Request done at \"/permissions\"\n" +
                            "| username=\"alice\"\n" +
                            "| password=\"wonderland\"\n\n" +
                            "Expected result = 200\n" +
                            "Actual result = " + responseCode + "\n" +
                            "Response body = " + response.toString() + "\n\n" +
                            "==> " + (responseCode == 200 ? "SUCCESS" : "FAILURE") + "\n";

            // Afficher la sortie dans la console
            System.out.println(output);

            // Vérification de la variable d'environnement 'LOG'
            String logEnv = System.getenv("LOG");
            if ("1".equals(logEnv)) {
                writeLog(output);  // Écrire dans le fichier si LOG=1
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour écrire les logs dans un fichier api_test.log
    private static void writeLog(String output) {
        try {
            FileWriter writer = new FileWriter("/app/logs/api_test.log", true);  // 'true' pour ajouter au fichier existant
            writer.write(output);
            writer.close();
            System.out.println("Log saved to /app/logs/api_test.log");
        } catch (IOException e) {
            System.err.println("Error writing log file: " + e.getMessage());
        }
    }
}

