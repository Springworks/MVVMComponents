package se.springworks.mvvmcomponents.recyclerview.adapter

import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder
import se.springworks.mvvmcomponents.recyclerview.viewmodel.StringItemViewModel

class BaseRecyclerViewAdapterTest {

  private lateinit var adapter: StringRecyclerViewAdapter
  private lateinit var items: List<String>

  @Before
  fun setUp() {
    items = listOf("a", "b", "c", "d")
    adapter = StringRecyclerViewAdapter(items)
  }

  @Test
  fun onBindViewHolder() {
    val holder = mock<ItemViewHolder<String, StringItemViewModel, *>>()
    adapter.onBindViewHolder(holder, 0)
    verify(holder).bindTo("a", 0)
  }

  @Test
  fun getItemCount() {
    assertThat(adapter.itemCount, equalTo(items.size))
  }

  @Test
  fun onAttachedToRecyclerView() {
    val recycler = mock<RecyclerView>()
    adapter.onAttachedToRecyclerView(recycler)
    verify(recycler).addOnAttachStateChangeListener(any())
  }

  @Test
  fun onViewAttachedToWindow() {

  }

  @Test
  fun onViewDetachedFromWindow() {

  }

  @Test
  fun onDetachedFromRecyclerView() {

  }

  @Test
  fun removeItem() {

  }

  @Test
  fun moveItem() {

  }

  @Test
  fun setItems() {

  }

  @Test
  fun insertItems() {

  }

  @Test
  fun clearItems() {

  }

  @Test
  fun addItem() {

  }

  @Test
  fun observeClickedItem() {

  }

}
