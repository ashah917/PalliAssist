package io.palliassist.palliassistmobile.twilio.util;

/**
 * Created by User on 11/16/2016.
 */

public interface ILoginListener {

    public void onLoginStarted();

    public void onLoginFinished();

    public void onLoginError(String errorMessage);


    public void onLogoutFinished();

}
