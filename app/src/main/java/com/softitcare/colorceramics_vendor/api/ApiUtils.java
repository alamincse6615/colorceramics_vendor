package com.softitcare.colorceramics_vendor.api;

import com.softitcare.colorceramics_vendor.interfaces.LoginService;

public class ApiUtils {
    public static final String BASE_URL = "https://colorceramics.com/api/v1/auth/signupseller/";
    public static LoginService getLoginService(){
        return RetrofitClient.getClient(BASE_URL).create(LoginService.class);
    }
}
