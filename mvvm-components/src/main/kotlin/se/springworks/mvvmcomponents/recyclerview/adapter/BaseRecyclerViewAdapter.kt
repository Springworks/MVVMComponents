package se.springworks.mvvmcomponents.recyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder

abstract class BaseRecyclerViewAdapter<Model : Any>
(private val items: MutableList<Model> = mutableListOf()) : RecyclerView.Adapter<ItemViewHolder<Model>>() {

  private val itemClickSubject = PublishSubject.create<Model>()
  protected var observeClicks = true

  private lateinit var attachListener: View.OnAttachStateChangeListener

  override fun onBindViewHolder(holder: ItemViewHolder<Model>, position: Int) {
    val item = items[position]
    holder.bindTo(item, position)
  }

  override fun getItemCount() = items.size

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)

    attachListener = object : View.OnAttachStateChangeListener {
      override fun onViewAttachedToWindow(p0: View) {
        initializeViewHolders(recyclerView)
      }

      override fun onViewDetachedFromWindow(p0: View) {
        releaseViewHolders(recyclerView)
      }
    }

    recyclerView.addOnAttachStateChangeListener(attachListener)
  }

  private fun initializeViewHolders(recyclerView: RecyclerView) {
    (0..itemCount)
        .mapNotNull { recyclerView.findViewHolderForAdapterPosition(it) }
        .filterIsInstance<ItemViewHolder<Model>>()
        .forEach { it.init() }
  }

  private fun releaseViewHolders(recyclerView: RecyclerView) {
    (0..itemCount)
        .mapNotNull { recyclerView.findViewHolderForAdapterPosition(it) }
        .filterIsInstance<ItemViewHolder<Model>>()
        .forEach { it.release() }
  }

  override fun onViewAttachedToWindow(holder: ItemViewHolder<Model>) {
    super.onViewAttachedToWindow(holder)
    holder.init()
    if (observeClicks) {
      holder.itemView.setOnClickListener {
        val pos = holder.adapterPosition
        when (pos) {
          in 0 until items.size -> itemClickSubject.onNext(items[pos])
        }
      }
    }
  }

  override fun onViewDetachedFromWindow(holder: ItemViewHolder<Model>) {
    super.onViewDetachedFromWindow(holder)
    if (observeClicks) {
      holder.itemView.setOnClickListener(null)
    }
    holder.release()
  }

  override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
    super.onDetachedFromRecyclerView(recyclerView)
    recyclerView.removeOnAttachStateChangeListener(attachListener)
  }

  open fun removeItem(position: Int): Model {
    val item = items.removeAt(position)
    notifyItemRemoved(position)
    return item
  }

  open fun moveItem(fromPosition: Int, toPosition: Int): Model {
    val model = items[fromPosition]
    val prev = items.removeAt(fromPosition)
    val current = if (toPosition > fromPosition) toPosition - 1 else toPosition
    items.add(current, prev)
    notifyItemMoved(fromPosition, toPosition)
    return model
  }

  open fun setItems(newItems: List<Model>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  fun insertItems(newItems: List<Model>) {
    items.addAll(newItems)
    notifyItemRangeInserted(0, newItems.size)
  }

  fun clearItems() {
    val count = items.count()
    items.clear()
    notifyItemRangeRemoved(0, count)
  }

  fun addItem(newItem: Model) {
    items.add(newItem)
    notifyItemInserted(itemCount - 1)
  }

  fun observeClickedItem(): Observable<Model> = itemClickSubject

}
