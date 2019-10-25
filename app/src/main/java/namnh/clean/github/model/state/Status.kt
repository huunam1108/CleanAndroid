package namnh.clean.github.model.state

object Loading : Status()
object Finish : Status()
object Success : Status()
object Error : Status()
sealed class Status
