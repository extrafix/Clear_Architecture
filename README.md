# Stream Processing
Самое сложное для понимания задание оказалось.  
[CommandInterpreter](https://github.com/extrafix/Clear_Architecture/blob/event_sourcing/src/main/java/com/summer/cleaner/interpreter/CommandInterpreter.java), в нем задаю [EventStore](https://github.com/extrafix/Clear_Architecture/blob/event_sourcing/src/main/java/com/summer/cleaner/store/event/EventStore.java), добавляю слушателей (processors) для событий и вызываю по событию нужный слушатель.  
Так как логика у меня осталась инкапсулирована в Command, то по их типа и создавал Event в [CommandToRequestEventTransformer](https://github.com/extrafix/Clear_Architecture/blob/event_sourcing/src/main/java/com/summer/cleaner/input/transformer/CommandToRequestEventTransformer.java).   


### Плюсы
* Теперь проще смотреть на kafka не как на что-то очень сложное само в себе, а просто как на EventStore с функцией вызова нужных Consumer(Listener/Processor) по событию (сообщение) нужного типа и коммитить что event обработан только если действительно удалось его выполнить.
* Так же в kafka после обработки сообщение продолжает храниться и можем попытаться востановить состояние проиграв повторно всю цепочку


### Минусы
* Для понимания было трудно с непривычки.  Да и сейчас еще надо будет внимательно смотреть пример.
  Вызывать слушателей вроде надо было из EventStore, но тогда это циклическая зависимость, т.к. они тоже имеют поле EventStore.
  В идеале, это решается разделением на клиентское и серверное API, как в kafka.   
* Каждое увеличение Store будет сказываться на скорости получения результата следющего, что можно ускорить если возвращать крайнее состояние из DoneEvent, а всю цепочку хранить если потребуется отладка.   
