package com.brins.base.baseblock

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import com.brins.base.baseblock.BaseBlock

abstract open class ViewModelStoreOwnerBlock(
    lifecycleOwner: LifecycleOwner,
    val viewModelStoreOwner: ViewModelStoreOwner
) : BaseBlock(lifecycleOwner) {
}