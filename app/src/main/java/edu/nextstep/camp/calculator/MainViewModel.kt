package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _text = MutableLiveData("")
    val text: LiveData<String>
        get() = _text

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
        } else {
            _onCalculationErrorEvent.value = Unit
        }
    }
}
