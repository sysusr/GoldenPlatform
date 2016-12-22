package com.tikt.goldenplatform.retrofitInterface;

import com.tikt.goldenplatform.Api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TikT on 2016/12/22.
 */

public interface BusStationList {
    @POST(Api.getBusStationlistUrl)
    Call<String> listRepos(@Body String str);
}
