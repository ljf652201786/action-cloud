package com.action.auth.service;

public interface IAuthService {

    void redirect(String appid, String url, String response_type, String scope);
}
