package extension;

public class CheckApiKey {
    public static void checkApiKey(String apiKey){
        if(apiKey == null || !apiKey.equals("123456")){
            throw new RuntimeException("Missing or invalid API Key");
        }
    }
}
