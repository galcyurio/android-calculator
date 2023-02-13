package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.di.DataInjector
import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorRepositoryImplTest {
    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val calculatorDatabase = Room
            .inMemoryDatabaseBuilder(context, CalculatorDatabase::class.java)
            .build()
        calculatorRepository = DataInjector.provideCalculatorRepository(
            context = context,
            calculatorDatabase = calculatorDatabase,
        )
    }

    @Test
    fun 계산_결과_저장하고_가져오기() {
        // given
        val memory = Memory("1 + 2", 3)

        // when
        calculatorRepository.saveMemory(memory).get()
        val actual = calculatorRepository.findMemories()

        // then
        assertThat(actual).containsExactly(memory)
    }
}
