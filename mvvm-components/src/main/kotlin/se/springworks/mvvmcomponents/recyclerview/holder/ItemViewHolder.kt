package se.springworks.mvvmcomponents.recyclerview.holder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import se.springworks.mvvmcomponents.recyclerview.viewmodel.ItemViewModel

typealias BindingInflateFunction<DataBinding> = (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> DataBinding

open class BaseItemViewHolder
(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

open class ItemViewHolder<in Model : Any>(
    parent: ViewGroup,
    layoutInflater: LayoutInflater,
    bindingInflateFunction: BindingInflateFunction<ViewDataBinding>,
    private val viewModel: ItemViewModel<Model>)
  : BaseItemViewHolder(bindingInflateFunction.invoke(layoutInflater, parent, false)) {

  open fun init() {
    viewModel.initialize()
  }

  open fun bindTo(item: Model, position: Int) {
    viewModel.setItem(item, position)
    binding.executePendingBindings()
  }

  open fun release() {
    viewModel.release()
  }
}
