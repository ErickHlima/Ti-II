import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class AzureCognitiveServiceDemo {
    private static final String ENDPOINT = "https://1522942puc.cognitiveservices.azure.com/";
    private static final String API_PATH = "text/analytics/v3.0/sentiment";

    public static void main(String[] args) {
        String texto = "Estou muito feliz com o progresso do projeto!";
        try {
            String response = analyzeSentiment(texto);
            System.out.println("Resposta do serviço: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String analyzeSentiment(String texto) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(ENDPOINT + API_PATH);

        JSONObject document = new JSONObject();
        document.put("id", "1");
        document.put("language", "pt");
        document.put("text", texto);

        JSONObject payload = new JSONObject();
        payload.put("documents", new org.json.JSONArray().put(document));

        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Content-Type", "application/json")
            .header("Ocp-Apim-Subscription-Key", API_KEY)
            .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
