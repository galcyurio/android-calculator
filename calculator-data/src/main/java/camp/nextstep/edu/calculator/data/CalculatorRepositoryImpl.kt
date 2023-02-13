package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

internal class CalculatorRepositoryImpl(
    private val calculatorDao: CalculatorDao,
    private val executor: ExecutorService,
) : CalculatorRepository {

    override fun saveMemory(memory: Memory): Future<*> {
        return executor.submit {
            calculatorDao.saveMemory(MemoryEntity.from(memory))
        }
    }

    override fun findMemories(): List<Memory> {
        return executor.submit<List<Memory>> {
            calculatorDao.findMemories().map(MemoryEntity::toDomain)
        }.get()
    }
}
