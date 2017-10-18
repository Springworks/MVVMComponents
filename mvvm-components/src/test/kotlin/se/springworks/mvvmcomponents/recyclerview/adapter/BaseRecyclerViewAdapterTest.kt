package se.springworks.mvvmcomponents.recyclerview.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import se.springworks.mvvmcomponents.recyclerview.holder.BindingInflateFunction
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder
import se.springworks.mvvmcomponents.recyclerview.viewmodel.EmptyItemViewModel
import se.springworks.mvvmcomponents.recyclerview.viewmodel.StringItemViewModel

class StubRecyclerViewAdapter(items: List<String>) : BaseRecyclerViewAdapter<String>(items.toMutableList()) {
  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder<String> {
    return mock { }
  }
}

class BaseRecyclerViewAdapterTest {

  private lateinit var adapter: StubRecyclerViewAdapter
  private lateinit var items: List<String>

  class TestDataObserver : RecyclerView.AdapterDataObserver()

  @Before
  fun setUp() {
    items = listOf("a", "b", "c", "d")
    adapter = StubRecyclerViewAdapter(items)
  }

  @Test
  fun testOnBindViewHolderAlsoBindItemIntoViewModel() {
    val holder = mock<ItemViewHolder<String>>()
    adapter.onBindViewHolder(holder, 0)
    verify(holder).bindTo("a", 0)
  }

  @Test
  fun testAdapterItemCountEqualsItemsSize() {
    assertThat(adapter.itemCount, equalTo(items.size))
  }

  @Test
  fun testOnAttachedToRecyclerViewAttachStateChangeListener() {
    val recycler = mock<RecyclerView>()
    adapter.onAttachedToRecyclerView(recycler)
    verify(recycler).addOnAttachStateChangeListener(any())
  }

  @Test
  fun testOnViewAttachedToWindowInitHolder() {
    val parentView = mock<ViewGroup>()
    val itemView = mock<View>()
    val binding = mock<ViewDataBinding> {
      on { root } doReturn itemView
    }
    val layoutInflater = mock<LayoutInflater>()
    val bindingInflateFunction = mock<BindingInflateFunction<ViewDataBinding>> {
      on { invoke(layoutInflater, parentView, false) } doReturn binding
    }
    val viewModel = mock<StringItemViewModel>()

    val holder = ItemViewHolder<String>(parentView, layoutInflater, bindingInflateFunction, viewModel)
    val holderSpy = spy(holder)

    adapter.onViewAttachedToWindow(holderSpy)
    verify(holderSpy).init()
    verify(holderSpy.itemView).setOnClickListener(any())
  }

  @Test
  fun testOnViewDetachedFromWindowReleaseHolder() {
    val parentView = mock<ViewGroup>()
    val itemView = mock<View>()
    val binding = mock<ViewDataBinding> {
      on { root } doReturn itemView
    }
    val layoutInflater = mock<LayoutInflater>()
    val bindingInflateFunction = mock<BindingInflateFunction<ViewDataBinding>> {
      on { invoke(layoutInflater, parentView, false) } doReturn binding
    }
    val viewModel = mock<StringItemViewModel>()

    val holder = ItemViewHolder(parentView, layoutInflater, bindingInflateFunction, viewModel)
    val holderSpy = spy(holder)

    adapter.onViewDetachedFromWindow(holderSpy)
    verify(holderSpy).release()
    val clickListener: View.OnClickListener? = null
    verify(holderSpy.itemView).setOnClickListener(clickListener)
  }

  @Test(expected = UninitializedPropertyAccessException::class)
  fun testOnDetachedFromRecyclerViewWithoutAttachingThrowError() {
    val recyclerView = mock<RecyclerView>()
    adapter.onDetachedFromRecyclerView(recyclerView)
    verify(recyclerView).removeOnAttachStateChangeListener(any())
  }

  @Test
  fun testOnAttachAndDetachForRecyclerView() {
    val recyclerView = mock<RecyclerView>()
    adapter.onAttachedToRecyclerView(recyclerView)
    val listenerCaptor = argumentCaptor<View.OnAttachStateChangeListener>()
    verify(recyclerView).addOnAttachStateChangeListener(listenerCaptor.capture())
    val listener = listenerCaptor.firstValue
    adapter.onDetachedFromRecyclerView(recyclerView)
    verify(recyclerView).removeOnAttachStateChangeListener(listener)
  }
}
