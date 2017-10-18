package se.springworks.mvvmcomponents.recyclerview.holder

import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import se.springworks.mvvmcomponents.recyclerview.viewmodel.ItemViewModel

class ItemViewHolderTest {

  private lateinit var viewHolder: ItemViewHolder<Unit>

  private lateinit var viewModel: ItemViewModel<Unit>
  private lateinit var viewBinding: ViewDataBinding

  @Before
  fun setUp() {
    viewBinding = mock {
      on { root } doReturn mock<View>()
    }
    val parent = mock<ViewGroup>()
    val layoutInflater = mock<LayoutInflater>()
    val bindingInflateFunction = mock<BindingInflateFunction<ViewDataBinding>> {
      on { invoke(layoutInflater, parent, false) } doReturn viewBinding
    }
    viewModel = mock()
    viewHolder = ItemViewHolder(parent, layoutInflater, bindingInflateFunction, viewModel)

    verify(bindingInflateFunction).invoke(layoutInflater, parent, false)
    verify(viewBinding).root
  }

  @Test
  fun testInitCallViewModelInit() {
    verify(viewModel).initialize()
  }

  @Test
  fun testBindToCallViewModelSetItem() {
    val item = Unit
    viewHolder.bindTo(item, 0)
    verify(viewModel).setItem(item, 0)
    verify(viewBinding).executePendingBindings()
  }

  @Test
  fun testReleaseClearViewModel() {
    viewHolder.release()
    verify(viewModel).release()
  }

  @After
  fun tearDown() {
    verify(viewModel, never()).resume()
    verify(viewModel, never()).pause()
  }
}
