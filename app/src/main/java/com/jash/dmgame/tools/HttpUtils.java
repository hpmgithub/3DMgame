package com.jash.dmgame.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jash
 * Date: 15-11-20
 * Time: 下午1:29
 */
public class HttpUtils {
    private static GameService service;

    static {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new CustomDateTypeAdapter()).create();
        service = new Retrofit.Builder()
                .baseUrl("http://www.3dmgame.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(GameService.class);
    }
    public interface GameService{
        @GET("/sitemap/api.php?paging=1")
        Call<ChapterResult> getChapterList(@Query("row") int row, @Query("page") int page, @Query("typeid") int typeId);
    }
    private static class CustomDateTypeAdapter extends TypeAdapter<Date>{

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.getTime() / 1000);
            }
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                return null;
            } else {
                return new Date(in.nextLong());
            }
        }
    }
}
