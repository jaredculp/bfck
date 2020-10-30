# bfck

A [brainfuck](https://en.wikipedia.org/wiki/Brainfuck) interpreter written in java.

## Build

Hello world:
```
mvn clean install
java -cp target/*.jar dev.culp.Bfck $(cat src/main/resources/hello-world.bf)
```
