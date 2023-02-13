package camp.nextstep.edu.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorDao {
    @Insert
    fun saveMemory(memory: MemoryEntity)

    @Query("SELECT * from memory")
    fun findMemories(): List<MemoryEntity>
}
