package vn.whatsenglish.orchestrator.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.whatsenglish.orchestrator.retrofit.ServiceProduct;
import vn.whatsenglish.orchestrator.retrofit.ServiceUser;

@Configuration
public class RetrofitConfig {

    private final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    @Bean
    public ServiceUser buildServiceUserEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8090/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServiceUser.class);
    }

    @Bean
    public ServiceProduct buildServiceProductEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8083/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServiceProduct.class);
    }
}
