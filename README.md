# TestTask_TicketsSearch

я позволил себе отойти от некоторых дизайнерских решений в верстке, потому как это не производственный проект.
тем не менее технологический стек соответствует требуемому.

- ## Модульность

приложение разделено на пять гредл модулей:
- приложение
- домен
- дата
- презентер
- интерфейс (функционал, использование которого выходит за рамки одного модуля: например логгер)

домен и интерфейс не знает ни о ком, дата знает о домене и интерфейсе, презентер знает о домене и интерфейсе, 
приложение знает о всех.
добавлен модификатор "internal" к вьюмоделям и их стейтам, к списку (адаптеру и итему), 
к источникам данных и реализациям репозиториев, к основному аппликейшену.

- ## Чистая архитектура

приложение разделено на три основных слоя: 
- презентер, 
- домен 
- дата. 

дата отвечает за хранение данных и приход данных по сети, 
презентер отвечает за взаимодействие с пользователем, 
домен отвечает за передачу и обработку данных между другими слоями.

- ## Внедрение зависимостей

реализовано с помощью Dagger2,
многомодульность реализована с помощью сабмодулей (тем не менее есть решение лучше)

- ## Многопоточность

реализована с помощью Coroutines Flow, 
добавлена базовая обработка ошибок (исключения ловятся, ошибки пробрасываются далее), 
все выбросы логируются

- ## Архитектурные паттерны

MVVM, вьюмодель общается с фрагментом с помощью лайвдаты

- ## Верстка

с помощью XML, произведена оптимизация лэйаутов по читаемости и производительности с помощью "include",
все иконки созданы с помощью векторной графики

- ## Сеть

реализована с помощью ретрофит (+ окшттп - для перехватов)
