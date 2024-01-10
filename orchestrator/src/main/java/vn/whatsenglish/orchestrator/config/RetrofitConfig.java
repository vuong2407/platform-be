package vn.whatsenglish.orchestrator.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.whatsenglish.orchestrator.retrofit.ServiceCustomer;

@Configuration
public class RetrofitConfig {

    private final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    @Bean
    public ServiceCustomer buildServiceBooksEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServiceCustomer.class);
    }
}
