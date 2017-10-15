package se.springworks.mvvmcomponents.recyclerview.holder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import se.springworks.mvvmcomponents.recyclerview.viewmodel.ItemViewModel

typealias BindingInflateFunction<DataBinding> = (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> DataBinding

open class BaseItemViewHolder<out DataBinding : ViewDataBinding>
(val binding: DataBinding) : RecyclerView.ViewHolder(binding.root)

open class ItemViewHolder<in Model : Any, out ViewModel : ItemViewModel<Model>, out DataBinding : ViewDataBinding>(
    parent: ViewGroup,
    layoutInflater: LayoutInflater,
    bindingInflateFunction: BindingInflateFunction<DataBinding>,
    private val viewModel: ViewModel)
  : BaseItemViewHolder<DataBinding>(bindingInflateFunction.invoke(layoutInflater, parent, false)) {

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
