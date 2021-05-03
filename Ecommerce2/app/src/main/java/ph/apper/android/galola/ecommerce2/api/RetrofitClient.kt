package ph.apper.android.galola.ecommerce2.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH = "Basic "+ Base64.encodeToString("root:123456".toByteArray(), Base64.NO_WRAP)

    // GET ALL USERS: http://18.141.239.189/myapi/public/allusers
    // CREATE USER: http://18.141.239.189/myapi/public/createuser
    private const val BASE_URL = "http://18.141.239.189/myapi/public/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}