# Tensorflow_Java 1.1 Test Code

Tesorflow 2.0 Java API Test

Directly run

```
mvn -q compile exec:java
```

Package and run with Java

```
mvn clean package -DskipTests
java -cp target/test-tensorflow-1.1-SNAPSHOT-jar-with-dependencies.jar HelloTensorFlow
```

Based on [TensorFlow for Java](https://www.tensorflow.org/jvm/install).

