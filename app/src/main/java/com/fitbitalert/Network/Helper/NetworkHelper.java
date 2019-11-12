package com.fitbitalert.Network.Helper;

import com.fitbitalert.Network.Retrofit.RetrofitService;
import com.fitbitalert.Utils.Rx.AppRxScheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NetworkHelper {

    private RetrofitService retrofitService;
    private AppRxScheduler scheduler;

}
