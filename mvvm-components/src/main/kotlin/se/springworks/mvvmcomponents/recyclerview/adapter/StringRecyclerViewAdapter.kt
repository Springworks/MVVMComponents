package se.springworks.mvvmcomponents.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vhudnitsky.mvvmcomponents.databinding.StringItemBinding
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder
import se.springworks.mvvmcomponents.recyclerview.viewmodel.StringItemViewModel

class StringRecyclerViewAdapter(items: List<String>) : BaseRecyclerViewAdapter<String, StringItemViewModel>(items.toMutableList()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<String, StringItemViewModel, *> =
      ItemViewHolder<String, StringItemViewModel, StringItemBinding>(parent,
                                                                     LayoutInflater.from(parent.context),
                                                                     StringItemBinding::inflate,
                                                                     StringItemViewModel())
}
