package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel(
    private val calculatorRepository: CalculatorRepository,
) : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _text = MutableLiveData("")
    val text: LiveData<String>
        get() = _text

    private val _memories = MutableLiveData(emptyList<Memory>())
    val memories: LiveData<List<Memory>>
        get() = _memories

    private val _showMemoryMode = MutableLiveData(false)
    val showMemoryMode: LiveData<Boolean>
        get() = _showMemoryMode

    // TODO: SingleLiveEvent
    private val _onCalculationErrorEvent = MutableLiveData<Unit>()
    val onCalculationErrorEvent: LiveData<Unit>
        get() = _onCalculationErrorEvent

    fun addToExpression(operand: Int) {
        expression += operand
        _text.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _text.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _text.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result != null) {
            _text.value = result.toString()
            saveMemory(expression, result)
        } else {
            _onCalculationErrorEvent.value = Unit
        }
    }

    fun toggleMode() {
        val showMemoryMode = _showMemoryMode.value?.not() ?: return
        if (showMemoryMode) {
            _memories.value = calculatorRepository.findMemories()
        }
        _showMemoryMode.value = showMemoryMode
    }

    private fun saveMemory(expression: Expression, result: Int) {
        val memory = Memory(
            expression = expression.toString(),
            result = result,
        )
        calculatorRepository.saveMemory(memory)
    }
}
