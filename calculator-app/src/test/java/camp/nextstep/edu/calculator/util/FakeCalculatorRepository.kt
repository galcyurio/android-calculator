package camp.nextstep.edu.calculator.util

import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

object FakeCalculatorRepository : CalculatorRepository {
    private val memories = mutableListOf<Memory>()

    override fun saveMemory(memory: Memory): Future<*> {
        memories.add(memory)
        return CompletableFuture.completedFuture(Unit)
    }

    override fun findMemories(): List<Memory> {
        return emptyList()
    }
}
