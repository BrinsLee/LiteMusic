package com.brins.base.baseblock

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.greenrobot.eventbus.EventBus


open abstract class BaseBlock(val mLifeCycleOwner: LifecycleOwner) : LifecycleObserver {

    init {
        mLifeCycleOwner.lifecycle.removeObserver(this)
        mLifeCycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        Log.d("OnLifecycleEven %s", "$this onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(source: LifecycleOwner) {
        Log.d("OnLifecycleEvent %s", "$this onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        Log.d("OnLifecycleEvent %s", "$this onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {

    }

    // 如果需要提前释放，则一定要自己手动移除！！！
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        Log.d("OnLifecycleEvent %s", "$this onDestroy")
        mLifeCycleOwner.lifecycle.removeObserver(this)
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    open fun initBlock() {}

    //onResume后的
    fun isAtLeastOnResume() : Boolean{
        return mLifeCycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
    }

    fun isOnResume(): Boolean {
        return mLifeCycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED
    }

    fun isDestroyed():Boolean{
        return mLifeCycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED
    }
}