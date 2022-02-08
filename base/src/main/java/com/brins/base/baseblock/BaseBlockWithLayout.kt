package com.brins.base.baseblock

import android.view.View
import androidx.lifecycle.LifecycleOwner

/**
 * 实现KAE在kotlin代码中直接使用view id
 */
open abstract class BaseBlockWithLayout(
    val lifecycleOwner: LifecycleOwner,
    override val containerView: View?
) : BaseBlock(lifecycleOwner), LayoutContainer {

}