package pl.mbui.okeapp.ui.util.reactive

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class ReactiveViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}