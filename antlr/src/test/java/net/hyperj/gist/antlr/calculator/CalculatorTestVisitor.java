package net.hyperj.gist.antlr.calculator;

public class CalculatorTestVisitor extends CalculatorBaseVisitor<Object> {
    @Override
    public Object visitEquation(CalculatorParser.EquationContext ctx) {
        return super.visitEquation(ctx);
    }

    @Override
    public Object visitExpression(CalculatorParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public Object visitMultiplyingExpression(CalculatorParser.MultiplyingExpressionContext ctx) {
        return super.visitMultiplyingExpression(ctx);
    }

    @Override
    public Object visitPowExpression(CalculatorParser.PowExpressionContext ctx) {
        return super.visitPowExpression(ctx);
    }

    @Override
    public Object visitSignedAtom(CalculatorParser.SignedAtomContext ctx) {
        return super.visitSignedAtom(ctx);
    }

    @Override
    public Object visitAtom(CalculatorParser.AtomContext ctx) {
        return super.visitAtom(ctx);
    }

    @Override
    public Object visitScientific(CalculatorParser.ScientificContext ctx) {
        return super.visitScientific(ctx);
    }

    @Override
    public Object visitConstant(CalculatorParser.ConstantContext ctx) {
        return super.visitConstant(ctx);
    }

    @Override
    public Object visitVariable(CalculatorParser.VariableContext ctx) {
        return super.visitVariable(ctx);
    }

    @Override
    public Object visitFunc(CalculatorParser.FuncContext ctx) {
        return super.visitFunc(ctx);
    }

    @Override
    public Object visitFuncname(CalculatorParser.FuncnameContext ctx) {
        return super.visitFuncname(ctx);
    }

    @Override
    public Object visitRelop(CalculatorParser.RelopContext ctx) {
        return super.visitRelop(ctx);
    }
}
