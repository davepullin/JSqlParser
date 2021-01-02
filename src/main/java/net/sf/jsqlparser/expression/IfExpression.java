/*
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2013 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression;

import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

/**
 * IF conditions in SELECT clause.
 * Example: SELECT IF(a = 0, 5, 6), columnB, columnC from table1;
 *
 * @author Tomer Shay (Shimshi)
 */
public class IfExpression extends ASTNodeAccessImpl implements Expression {

    private Expression ifExpression;
    private Expression thenExpression;
    private Expression elseExpression;

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public Expression getIfExpression() {
        return ifExpression;
    }

    public void setIfExpression(Expression ifExpression) {
        this.ifExpression = ifExpression;
    }

    public Expression getThenExpression() {
        return thenExpression;
    }

    public void setThenExpression(Expression thenExpression) {
        this.thenExpression = thenExpression;
    }

    public Expression getElseExpression() {
        return elseExpression;
    }

    public void setElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
    }

    @Override
    public String toString() {
        return "if(" + ifExpression + ", " + thenExpression + ", " + elseExpression + ")";
    }
}
