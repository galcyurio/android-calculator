package camp.nextstep.edu.calculator.data

import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.data.di.DataInjector
import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class CalculatorRepositoryImplTest {

    private val calculatorRepository: CalculatorRepository =
        DataInjector.provideCalculatorRepository(
            context = ApplicationProvider.getApplicationContext(),
        )

    @Test
    fun `계산 결과 저장하고 가져오기`() {
        // given
        val memory = Memory("1 + 2", 3)

        // when
        calculatorRepository.saveMemory(memory).get()
        val actual = calculatorRepository.findMemories()

        // then
        assertThat(actual).containsExactly(memory)
    }
}
