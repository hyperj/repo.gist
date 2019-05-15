package net.hyperj.gist.antlr.calculator;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class CalculatoTest {

    public static void main(String[] args) {
        String expression = "1+3+5-2*2/0.1";
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
        CommonTokenStream token = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(token);
        CalculatorTestVisitor visitor = new CalculatorTestVisitor();
        Double result = visitor.visit(parser.expression());
        System.out.println(result);
    }
}


