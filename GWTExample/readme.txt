GWT приложение состоит из 4 частей:
1. Module descriptors (обязательно)
2. Public resources (обязательно)
3. Client-side code (обязательно)
4. Server-side code (необязательно)
Расположение:
Project root - HelloWorld/
Описывается конфигурация приложения
Module descriptor - src/com/tutorialspoint/HelloWorld.gwt.xml  (описывает конфигурацию прилжения)
Public resources - src/com/tutorialspoint/war/ (здесь содержаться все файлы, упоминаемые в приложении, например html, css, картинки. Место этих файлах определяется в дескрипторе конфигурации в <public path = "path" />, по умолчанию это каталог,
в коором храниться конфигуационный файл !!web.xml!!)
Client-side code - src/com/tutorialspoint/client/
Server-side code - src/com/tutorialspoint/server/