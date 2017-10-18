package se.springworks.mvvmcomponents.mock

import android.databinding.DataBindingComponent
import android.databinding.ViewDataBinding
import android.view.View

open class MockViewBinding(private val parent: View)
    : ViewDataBinding(MockBindingComponent(), parent, 1) {
    override fun getRoot(): View = parent

    override fun setVariable(variableId: Int, value: Any?): Boolean = true

    override fun executeBindings() {

    }

    override fun onFieldChange(localFieldId: Int, `object`: Any?, fieldId: Int): Boolean = true

    override fun invalidateAll() {

    }

    override fun hasPendingBindings(): Boolean = true
}

open class MockBindingComponent: DataBindingComponent

object DataBinderMapper{
    val TARGET_MIN_SDK = 19
}