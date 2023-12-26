package vn.whatsenglish.backend.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.whatsenglish.backend.retrofit.ServiceProduct;

@Configuration
public class RetrofitConfig {

    private final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    @Bean
    public ServiceProduct buildServiceBooksEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServiceProduct.class);
    }
}
