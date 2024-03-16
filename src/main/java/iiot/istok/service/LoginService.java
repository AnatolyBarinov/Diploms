package iiot.istok.service;


import iiot.istok.request.LoginRequest;
import iiot.istok.responce.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);

    void logout(String login, String token);

}
