/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.request.service;

import fynisys.constants.Constants;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

//import snap.gp.service.RegistrationResponse;
/**
 *
 * @author daffodil-11
 */
public class PostRequestService {
       public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    
    public void postRequestService(String json,String url, final RequestResponseInterface responseCallback){
        System.out.println("json - "+json);
        System.out.println("URL- "+Constants.URL+url);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        RequestBody body;
        body = RequestBody.create(JSON, json);
        
        Request request = new Request.Builder()
                .url(Constants.URL+url)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException ioe) {
                //System.out.println("Hi in the onFailure block");
                ioe.printStackTrace();
                responseCallback.failureResponse(ioe.toString());
            }
            
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                
//                System.out.println("Response:-"+response.body().string());
                //getCurrentResponse(response.body().string());
                ResponseBody rb = response.body();
//                RegistrationController.Result(rb.string());
                String wsg = rb.string();
                
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        //Update UI here
                        responseCallback.successResponse(wsg);
                    }
                });
                
                //RegistrationResponse.getResponse(response.body().string());
            }
        });
        
    }
    
}
