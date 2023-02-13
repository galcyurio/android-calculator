package camp.nextstep.edu.calculator.data.di

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.data.CalculatorDatabase
import camp.nextstep.edu.calculator.data.CalculatorRepositoryImpl
import camp.nextstep.edu.calculator.domain.CalculatorRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object DataInjector {
    private fun provideCalculatorDatabase(context: Context): CalculatorDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CalculatorDatabase::class.java,
            "calculator.db"
        ).build()
    }

    private fun provideExecutorService(): ExecutorService {
        val threadCount = Runtime.getRuntime().availableProcessors() * 2
        return Executors.newFixedThreadPool(threadCount)
    }

    fun provideCalculatorRepository(
        context: Context,
        calculatorDatabase: CalculatorDatabase = provideCalculatorDatabase(context),
        executorService: ExecutorService = provideExecutorService(),
    ): CalculatorRepository {
        return CalculatorRepositoryImpl(
            calculatorDao = calculatorDatabase.calculatorDao(),
            executor = executorService,
        )
    }
}
