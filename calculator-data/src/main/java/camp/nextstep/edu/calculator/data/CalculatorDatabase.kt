package camp.nextstep.edu.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        MemoryEntity::class
    ],
    version = 1,
)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorDao(): CalculatorDao
}
