# Generator Bumblebee
## Version : 0.0.1 (prototype)
## Описание :
>`Сервис позволяет сгенерировать тестовые данные по заданным шаблонам и выгрузить в формате xlsx и csv`
## Используемые технологии :
* `Java 8`
* `Hibernate 5.2.16.Final`
* `Postgresql 42.2.2`
* `Spring  5.0.6`
* `Aspectjtools 1.9.1`
* `Apache POI 3.17`
* `Junit 4.12`
## Запуск : 
* `Для запуска проекта необходима БД  Postgresql :` [Generator BD](https://github.com/TimurBaldin/Bumblebee/tree/develop/src/database/migration)
* `Файл настроек для Hibernate доступен по ссылке :`  [Configuration](https://github.com/TimurBaldin/Bumblebee/blob/develop/src/main/resources/hibernate.cfg.xml)
* `Обратить внимание на следующие параметры hibernate.cfg.xml :`
```xml
<property name="hibernate.connection.username">UserName for your database</property>
<property name="hibernate.connection.password">Password for your database </property>
<property name="hibernate.connection.url">URL for your database</property>
```
* `Собрать проект "gradle BootJar" ---> перейти в папку  \build\libs ---> запустить Bumblebee-0.0.1 `
* `Перейти по ссылке : http://localhost:8080/`
* `После перехода по ссылке , открывается стартовая страница `
![Start_page](https://i.paste.pics/90a5a9ab065e502021b9dca2118f4e2b.png)
* `Страница создания тестовых данных : `
![Generator](https://i.paste.pics/913d408831164665111e9e6ee4ca6a44.png)
## Алгоритм создания тестовых данных  : 
* `Нажать на пункт меню "Создать тестовые данные" `
* `Ввести название колонки ---> нажать на кнопку "Добавить колонку" `
* `Задать параметры для необходимых проверок ----> нажать на кнопку "Сохранить проверку" `
* `Нажать на кнопку "Сохранить колонку". При необходимости добавить в отчет колонку ,повторить действия с шага 2 `
* `Нажать на кнопку "Сохранить модель" `
* `Заполнить параметры отчета (Report Excel/Report CSV) ----> нажать на кнопку "Загрузить" `

