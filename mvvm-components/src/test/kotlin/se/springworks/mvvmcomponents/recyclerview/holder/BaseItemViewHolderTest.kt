package se.springworks.mvvmcomponents.recyclerview.holder

import androidx.databinding.ViewDataBinding
import android.view.View
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class BaseItemViewHolderTest {

  private lateinit var viewHolder: BaseItemViewHolder

  @Before
  fun setUp() {
    val viewDataBinding = mock<ViewDataBinding> {
      on { root } doReturn mock<View>()
    }
    viewHolder = BaseItemViewHolder(viewDataBinding)
  }

  @Test
  fun testInitialization() {
    verify(viewHolder.binding).root
  }
}
