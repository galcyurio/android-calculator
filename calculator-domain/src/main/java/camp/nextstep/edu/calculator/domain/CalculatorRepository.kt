package camp.nextstep.edu.calculator.domain

import java.util.concurrent.Future

interface CalculatorRepository {
    fun saveMemory(memory: Memory): Future<*>

    fun findMemories(): List<Memory>
}
