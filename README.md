Гейтвей для сайта ресторана

### Сборка:

#### Локальный запуск:
1. ./gradlew bootRun

#### Запуск из контейнера
1. ./gradlew bootJar
2. docker build -t restaurant-gateway .
3. docker run -it --rm -p 8080:8080 restaurant-gateway