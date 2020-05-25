## Особенности запуска
1. Указать в файле application.properties в строке `spring.datasource.url=jdbc:mysql://192.168.99.101:3306/app` вместо 192.168.99.101:
* localhost в случае использования нативного Docker
* ip адрес, выдаваемый командой `docker-machine ip` в случае использования Docker Toolbox
2. Запустить контейнер с mysql командой `docker-compose up`
3. Запусктить SUT командой:
    ```
    java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://ip:3306/app -P:jdbc.user=user -P:jdbc.password=pass
    ```
    вместо ip подставить адрес из пукнка 1.
4. Запустить автотесты командой `gradlew clean test`.
5. По окончаниию тестов система автоматически очищает базу данных, при необходимости повторного запуска автотестов нужно остановить контейнер командой `docker-compose down` и повторить шаги 1-2.
