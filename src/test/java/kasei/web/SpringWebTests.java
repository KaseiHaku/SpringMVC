package kasei.web;


import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

public class SpringWebTests {

    @Test
    public void test(){
        System.out.println("213");

        OkHttpClient instance = OkHttpSingleton.SINGLETON.getInstance();
        OkHttpClient instance2 = OkHttpSingleton.SINGLETON.getInstance();
        System.out.println(instance==instance2);
    }

}
