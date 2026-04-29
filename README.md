# EventSource

У меня по-прежнему осталась переменная в [CommandInterpreter](https://github.com/extrafix/Clear_Architecture/blob/event_sourcing/src/main/java/com/summer/cleaner/interpreter/CommandInterpreter.java), но она изменяется только внутри исполнения метода и в нем же и иницилизируется по занчению из [EventStorage](https://github.com/extrafix/Clear_Architecture/blob/event_sourcing/src/main/java/com/summer/cleaner/store/event/EventStore.java).  
То есть я могу выполнить несколько вызовов от одного и того же его экземпляра, но мутабельность будет только у его поля CommandInterpreter.EventStore, не касаясь CleanerImpl в CommandInterpreter.

### Плюсы
* Есть возможность откатываться назад/вперед по событиям.   
* Можно теоретически ломать не всю последовательность, а локализовывать ошибочное событие, точнее в текущей реализации, ошибочное событие даже не запишется в EventSource, т.к. исключение не позволит дойти до добавления.

### Минусы
* Внутри метода интерпретации все еще есть мутабельное currentCleaner.  
* В  EventStore дублируется почти вся логика интерпретации комманд из основного CommandInterpreter.
