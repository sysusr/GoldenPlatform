package com.tikt.goldenplatform;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TikT on 2016/10/15.
 */

public interface BusLine {

    @POST("getLineList")
    Call<String> listRepos(@Body String str);
}
