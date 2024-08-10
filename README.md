

# TestTask_TicketsSearch

- ## Описание навигации между сверстанными экранами

<img alt="Screenshot_airtickets_screen.png" height="350" src="screenshot/Screenshot_airtickets_screen.png" width="200"/>

на главном экране при нажатии на место прибытия (в верхней части экрана поле поиска с подсказкой "куда") 
открывается окно поиска.

<img alt="Screenshot_search_empty_screen.png" height="350" src="screenshot/Screenshot_search_empty_screen.png" width="200"/>

<img alt="Screenshot_search_full_screen.png" height="350" src="screenshot/Screenshot_search_full_screen.png" width="200"/>

в окне поиска при нажатии на рекомендуемое место прибытия (центральная часть экрана с тремя предложениями городов) 
окно частично меняется.
в измененном окне поиска при нажатии на просмотр билетов (синяя кнопка) открывается экран со списком билетов.

<img alt="Screenshot_ticket_list_screen.png" height="350" src="screenshot/Screenshot_ticket_list_screen.png" width="200"/>


я позволил себе отойти от некоторых дизайнерских решений в верстке и коде, потому как это не производственный проект.
тем не менее технологический стек соответствует требуемому.

- ## Модульность

приложение разделено на пять основных гредл модулей:
- приложение
- домен
- дата
- презентер
- интерфейс (функционал, использование которого выходит за рамки одного модуля: например логгер)
- фичи (в данный момент отсутствуют - для будущего функционала)

домен и интерфейс не знает ни о ком, дата знает о домене и интерфейсе, презентер знает о домене и интерфейсе, 
приложение знает о всех.
добавлен модификатор "internal" к вьюмоделям и их стейтам, к списку (адаптеру и итему), 
к источникам данных и реализациям репозиториев, к основному аппликейшену.
новый функционал можно добавлять в новые фичерные модули.

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
многомодульность реализована с помощью сабмодулей (тем не менее есть решение лучше),
добавлено централизованное управление зависимостями

- ## Многопоточность

реализована с помощью Coroutines Flow, 
добавлена базовая обработка ошибок (исключения ловятся, ошибки пробрасываются далее), 
все выбросы логируются во вьюмоделях, 
исключения сети логируются в репозиториях перед забором данных из заглушечного источника
(заглушечный источник добавлен по причине не стабильной работы апи)

- ## Архитектурные паттерны

MVVM, вьюмодель общается с фрагментом с помощью лайвдаты

- ## Верстка

начат переход приложения c Xml на Compose.
переведены все экраны. На данный момент написан грамотно только экран Search:
- аргументы передаются через состояние, 
- ивенты пользователя передаются через лямбду и обрабатываются во вьюмодели
- вьюмодель в компоуз функции не заходит
- компоненты находятся только там где используются (кроме LazyLists)
- компоненты которые используются несколькими источниками вынесены в пакет ближайшего к корню источника

- ## Сеть

реализована с помощью ретрофит (+ окшттп - для перехватов), добавлена (частично) null-обработка входящих данных,
если данные не пришли то берутся из заглушечного источника 
(заглушечный источник добавлен по причине не стабильной работы апи),
кеш не реализован.

- ## Навигация (Compose)

навигация отделена от activity в отдельный файл для последующего тестирования,
контроллер не передается в экраны - остается в навигации, сама навигация происходит через лямбды,

- ## Список (экран список билетов) (xml)

произведены почти все оптимизации (верстка без вложенности, установка прослушивателей для примера, 
предварительная корректировка данных) 
кроме дифф утил (установлена без поддержки асинхронности) и переопределения базовых анимаций
