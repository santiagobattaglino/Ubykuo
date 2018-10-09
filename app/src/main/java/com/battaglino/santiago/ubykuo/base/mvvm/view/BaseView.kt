package com.battaglino.santiago.ubykuo.base.mvvm.view

import android.arch.lifecycle.AndroidViewModel
import android.support.v7.app.AppCompatActivity

import java.lang.ref.WeakReference

/**
 * Created by Santiago Battaglino.
 *
 * @typeparam A This represents your Activity related to this view
 * @typeparam V This represents your own implementation of BaseViewModel
 */
abstract class BaseView<A : AppCompatActivity, V : AndroidViewModel>(baseActivity: A, baseViewModel: V) {

    protected var baseActivity: WeakReference<A> = WeakReference(baseActivity)
    protected var baseViewModel: V = baseViewModel

    init {
        this.subscribeUiToLiveData()
    }

    /* In this method you must define your observer in order to receive the livedata changes managed
     * by the BaseViewModel.
     *
     *  e.g:
     *  We have a ViewModel that contains LiveData data that must be observed by our view,
     *  in order to react to changes in such data and show it calling showDataInUi method
     *
     *  baseViewModel.getObjects().observer(baseActivity.get(),new Observer<List<Object>>() {
     *       @Override
     *       public void onChanged(@Nullable List<Object> objects) {
     *           showDataInUi();
     *       }
     *   });
     *
     *
     * */
    protected abstract fun subscribeUiToLiveData()

    /* Show the observed live data in your view  */
    protected abstract fun showDataInUi()
}
