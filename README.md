# Как запустить тесты

### Для запуска проекта необходимо:
- Java 22
- Android SDK
- Maven
- Appium
- Устройство Android с включенным режимом отладки через USB

### Шаги для запуска тестов:

- Склонировать этот репозиторий к себе  `git clone https://github.com/s1yf0x/advocallAssigment.git`
- Подключить устройство Android по USB кабелю и убедиться что оно доступно через команду `adb devices`
- Запустить appium и установить в него драйвер `UiAutomator2` с помощью команды `appium driver install uiautomator2  `
- Запустить тест с помощью команды `mvn test` из директории проекта