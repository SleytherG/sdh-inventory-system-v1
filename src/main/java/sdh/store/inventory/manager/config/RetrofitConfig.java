package sdh.store.inventory.manager.config;

import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sdh.store.inventory.manager.proxy.client.OpenFoodFactsApi;

@Configuration
public class RetrofitConfig {

    @Bean
    public OpenFoodFactsApi openFoodFactsApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://world.openfoodfacts.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(OpenFoodFactsApi.class);
    }
}
