package net.hyperj.gist.antlr.calculator;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class CalculatoTest {

    public static void main(String[] args) {

        String expression = "(1 + 1) * 1";
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        System.out.printf(parser.expression().getText());

    }
}
