package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.util.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun `피연산자를 수식에 추가`() {
        // when
        viewModel.addToExpression(1)

        // then
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `연산자를 수식에 추가`() {
        // when
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // then
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `'1 + 2' 수식에서 마지막을 제거하면 '1 +'`() {
        // when
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)
        viewModel.removeLast()

        // then
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `'1 + 2' 수식을 계산하면 3`() {
        // when
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)
        viewModel.calculate()

        // then
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("3")
    }

    @Test
    fun `'1 +' 수식을 계산하면 에러`() {
        // when
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)
        viewModel.calculate()

        // then
        val actual = viewModel.onCalculationErrorEvent.getOrAwaitValue()
        assertThat(actual).isNotNull()
    }
}
