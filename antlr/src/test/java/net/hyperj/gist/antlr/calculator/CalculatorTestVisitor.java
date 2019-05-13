package net.hyperj.gist.antlr.calculator;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CalculatorTestVisitor extends CalculatorBaseVisitor<Double> {

    @Override
    public Double visitExpression(CalculatorParser.ExpressionContext ctx) {
        Double result = visit(ctx.multiplyingExpression(0));
        if (ctx.getChildCount() > 1) {
            for (int i = 1; i < ctx.getChildCount() - 1; i += 2) {
                ParseTree child = ctx.getChild(i);
                if (child instanceof TerminalNode) {
                    TerminalNode tnode = (TerminalNode) child;
                    Token symbol = tnode.getSymbol();
                    if (symbol.getType() == CalculatorParser.PLUS) {
                        result += visit(ctx.multiplyingExpression((i + 1) / 2));
                    } else if (symbol.getType() == CalculatorParser.MINUS) {
                        result -= visit(ctx.multiplyingExpression((i + 1) / 2));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Double visitMultiplyingExpression(CalculatorParser.MultiplyingExpressionContext ctx) {
        if (ctx.DIV().size() > 0) {
            return visit(ctx.powExpression(0)) / visit(ctx.powExpression(1));
        }
        if (ctx.TIMES().size() > 0) {
            return visit(ctx.powExpression(0)) * visit(ctx.powExpression(1));
        }
        return super.visitMultiplyingExpression(ctx);
    }

    @Override
    public Double visitPowExpression(CalculatorParser.PowExpressionContext ctx) {
        if (ctx.POW().size() > 0) {
            return Math.pow(visit(ctx.signedAtom(0)), visit(ctx.signedAtom(1)));
        }
        return super.visitPowExpression(ctx);
    }

    @Override
    public Double visitSignedAtom(CalculatorParser.SignedAtomContext ctx) {
        if (ctx.PLUS() != null)
            System.out.println("PLUS");
        if (ctx.MINUS() != null)
            System.out.println("MINUS");
        if (ctx.func() != null)
            System.out.println("func");
        if (ctx.atom() != null)
            return visit(ctx.atom());
        return super.visitSignedAtom(ctx);
    }

    @Override
    public Double visitAtom(CalculatorParser.AtomContext ctx) {
        if (ctx.scientific() != null)
            return visit(ctx.scientific());
        if (ctx.constant() != null)
            System.out.println("constant");
        if (ctx.LPAREN() != null && ctx.RPAREN() != null)
            System.out.println("LPAREN & RPAREN");
        return super.visitAtom(ctx);
    }

    @Override
    public Double visitScientific(CalculatorParser.ScientificContext ctx) {
        return Double.valueOf(ctx.SCIENTIFIC_NUMBER().getSymbol().getText());
    }

    @Override
    public Double visitConstant(CalculatorParser.ConstantContext ctx) {
        if (ctx.PI() != null)
            return 3.14;
        return -1.0;
    }

    @Override
    public Double visitFunc(CalculatorParser.FuncContext ctx) {
        return super.visitFunc(ctx);
    }

    @Override
    public Double visitFuncname(CalculatorParser.FuncnameContext ctx) {
        return super.visitFuncname(ctx);
    }

}
