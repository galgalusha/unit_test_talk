package com.google.adwords;

public class KeywordMutateCommand {

    public final Operator operator;
    public final GoogleKeyword operand;

    public KeywordMutateCommand(Operator operator, GoogleKeyword operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
