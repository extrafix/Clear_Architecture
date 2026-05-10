# Обработка ошибок при задании нового состояния
В первом решении в стиле ООП выделил [OutMessage](https://github.com/extrafix/Clear_Architecture/blob/catch_bounds/src/main/java/com/summer/cleaner/out/OutMessage.java) в отдельный класс. Ранее там было только только поле String text, но сейчас смог не меняя никакой код использующий уже этот интерфейс работы с OutMessage, добавить в него новое поле, которое хранит Optional<ValidationMessage>.
Из вызывающего кода менял только создание OutMessage в реализации метода move() и set() (установка моющего режиме) в [CleanerFunctionalStaticImpl](https://github.com/extrafix/Clear_Architecture/blob/catch_bounds/src/main/java/com/summer/cleaner/robot/CleanerFunctionalStaticImpl.java)
Одну из проверок реализовал в этом же классе, а для еще одной создал новый метод в [Field](https://github.com/extrafix/Clear_Architecture/blob/catch_bounds/src/main/java/com/summer/cleaner/field/Field.java)


### Плюсы
* Не происходит остановки интерпретации в случае нестандартной ситуации.
* Пользоваться такой программой исправляющей ошибки комманд - приятней.

### Минусы
* Чем больше внешних неожиданных ситуаций будем предугадывать, тем сложнее может стать программа и наша обработка может сделать только хуже, а этого мы могли не предусмотреть.
