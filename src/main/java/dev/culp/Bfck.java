package dev.culp;

import java.nio.file.Files;
import java.nio.file.Paths;

/** Interpreter for: https://en.wikipedia.org/wiki/Brainfuck */
public final class Bfck {

  private final char[] program;
  private final char[] mem;
  private String in;
  private int dp;
  private int ip;

  public static void main(String[] args) {
    System.out.println(new Bfck(args).run());
  }

  private Bfck(String[] args) {
    program = args[0].toCharArray();
    in = args.length == 2 ? args[1] : "";
    dp = 0;
    ip = 0;
    mem = new char[30_000];
  }

  public static Bfck fromFile(String filename) {
    final String program;
    try {
      program = Files.readString(Paths.get(Bfck.class.getClassLoader().getResource(filename).toURI()));
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not load file: " + filename, e);
    }

    return new Bfck(new String[]{program});
  }

  public String run() {
    final StringBuilder out = new StringBuilder();
    while (ip < program.length) {
      char inst = program[ip];

      if (inst == '>') dp++;
      if (inst == '<') dp--;
      if (inst == '+') mem[dp]++;
      if (inst == '-') mem[dp]--;
      if (inst == '.') out.append(mem[dp]);

      if (inst == ',') {
        mem[dp] = in.charAt(0);
        in = in.substring(1);
      }

      if (inst == '[' && mem[dp] == 0) {
        int seek = 1;
        while (seek > 0) {
          switch (program[++ip]) {
            case '[': seek++; break;
            case ']': seek--; break;
          }
        }
      }

      if (inst == ']' && mem[dp] > 0) {
        int seek = 1;
        while (seek > 0) {
          switch (program[--ip]) {
            case ']': seek++; break;
            case '[': seek--; break;
          }
        }
      }

      ip++;
    }

    return out.toString();
  }
}