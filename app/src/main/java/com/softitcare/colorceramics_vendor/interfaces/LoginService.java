package com.softitcare.colorceramics_vendor.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginService {

    @GET("login/{username}/{password}")
    Call login(@Path("username") String username,
               @Path("password") String password);
}
