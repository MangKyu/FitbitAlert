package com.fitbitalert.Network.Rx;

import io.reactivex.Scheduler;

public interface RxScheduler {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
