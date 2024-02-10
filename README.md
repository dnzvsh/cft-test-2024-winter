# cft-test-2024-winter

#### Building requirements
- Java 17.0.10
- Gradle 8.4 (gradlew used)

#### Libraries
[Apache Commons CLI](https://commons.apache.org/proper/commons-cli/) v1.6.0

### Build project
```
./gradlew build
```
### Run examples
```
java -jar ./build/libs/cft-test-2024-winter-1.0.jar -s -a -p sample- -o /result examples/in1.txt examples/in2.txt
```

### arguments
Аргументы:

| Короткая опция | Длинная опция | Аргумент | Описание                               |
|:--------------:|:-------------:|:--------:|:---------------------------------------|
|       -a       |   --append    |    -     | Добавляет данные к существующему файлу |
|       -s       |    --short    |    -     | Вывод короткой статистки               |
|       -f       |    --full     |    -     | Вывод полной статистки                 |
|       -o       | --outputPath  |    +     | Путь для выхожных файлов               | 
|       -p       |   --prefix    |    +     | Префикс выходных файлов                |

