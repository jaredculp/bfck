package dev.culp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BfckTest {

  @Test
  void helloWorld() {
    assertEquals("Hello World!\n", Bfck.fromFile("hello-world.bf").run());
  }
}