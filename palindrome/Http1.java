import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Http1 {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = get("https://jsonplaceholder.typicode.com/posts/1");

        HttpResponse<String> postResponse = post("https://jsonplaceholder.typicode.com/posts",
                "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}");

        System.out.println(response.statusCode());
        System.out.println(response.body());
        System.out.println("-----");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.body());


        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = postAsync("https://jsonplaceholder.typicode.com/posts",
                "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}");

        HttpResponse<String> responseFuture = httpResponseCompletableFuture.join();
        System.out.println("Async response status code: " + responseFuture.statusCode());
        System.out.println("Async response body: " + responseFuture.body());
    }

    public static HttpResponse<String> get(String url) throws IOException {
        HttpResponse<String> response;
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new IOException(e);
        }
        return response;
    }

    public static HttpResponse<String> post(String url, String body) throws IOException {
        HttpResponse<String> response;
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new IOException(e);
        }
        return response;
    }

    public static CompletableFuture<HttpResponse<String>> postAsync(String url, String body) throws IOException {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            response.thenApply(HttpResponse::body)
                    .thenAccept(System.out::println);
            return response;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
