package com.nesml.commons.network;

import retrofit2.Call;
import retrofit2.Response;

/**
 * This is a template class used for response of an HTTP request.
 *
 * @author n.diazgranados
 */
public abstract class ResponseEvent<T> {
    private Call<T> call;
    private Response<T> response;
    private Throwable throwable;
    private boolean isSuccessful;

    public void setResponse(Call<T> call, Response<T> response) {
        this.call = call;
        this.response = response;
        this.isSuccessful = true;
    }

    public void setBadResponse(Call<T> call, Throwable t) {
        this.call = call;
        this.throwable = throwable;
        isSuccessful = false;
    }

    public Call<T> getCall() {
        return call;
    }

    public Response<T> getResponse() {
        return response;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
