/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.util.deparser;

import java.util.Iterator;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.Index;
import net.sf.jsqlparser.statement.create.table.NewVerb;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

public class NewVerbDeParser extends AbstractDeParser<NewVerb> {

    private StatementDeParser statementDeParser;

    public NewVerbDeParser(StringBuilder buffer) {
        super(buffer);
    }

    public NewVerbDeParser(StatementDeParser statementDeParser, StringBuilder buffer) {
        super(buffer);
        this.statementDeParser = statementDeParser;
    }

    @Override
    public void deParse(NewVerb newVerb) {
        buffer.append("NEWVERB ");
        if (newVerb.isUnlogged()) {
            buffer.append("UNLOGGED ");
        }
        String params = PlainSelect.getStringList(newVerb.getCreateOptionsStrings(), false, false);
        if (!"".equals(params)) {
            buffer.append(params)
                    .append(' ');
        }

        buffer.append("TABLE ");
        if (newVerb.isIfNotExists()) {
            buffer.append("IF NOT EXISTS ");
        }
        buffer.append(newVerb.getTable()
                .getFullyQualifiedName());

        if (newVerb.getColumnDefinitions() != null) {
            buffer.append(" (");
            for (Iterator<ColumnDefinition> iter = newVerb.getColumnDefinitions()
                    .iterator(); iter.hasNext();) {
                ColumnDefinition columnDefinition = iter.next();
                buffer.append(columnDefinition.getColumnName());
                buffer.append(" ");
                buffer.append(columnDefinition.getColDataType()
                        .toString());
                if (columnDefinition.getColumnSpecs() != null) {
                    for (String s : columnDefinition.getColumnSpecs()) {
                        buffer.append(" ");
                        buffer.append(s);
                    }
                }

                if (iter.hasNext()) {
                    buffer.append(", ");
                }
            }

            if (newVerb.getIndexes() != null) {
                for (Index index : newVerb.getIndexes()) {
                    buffer.append(", ");
                    buffer.append(index.toString());
                }
            }

            buffer.append(")");
        }

        params = PlainSelect.getStringList(newVerb.getTableOptionsStrings(), false, false);
        if (!"".equals(params)) {
            buffer.append(' ')
                    .append(params);
        }

        if (newVerb.getRowMovement() != null) {
            buffer.append(' ')
                    .append(newVerb.getRowMovement()
                            .getMode()
                            .toString())
                    .append(" ROW MOVEMENT");
        }
        if (newVerb.getSelect() != null) {
            buffer.append(" AS ");
            if (newVerb.isSelectParenthesis()) {
                buffer.append("(");
            }
            Select sel = newVerb.getSelect();
            sel.accept(this.statementDeParser);
            if (newVerb.isSelectParenthesis()) {
                buffer.append(")");
            }
        }
        if (newVerb.getLikeTable() != null) {
            buffer.append(" LIKE ");
            if (newVerb.isSelectParenthesis()) {
                buffer.append("(");
            }
            Table table = newVerb.getLikeTable();
            buffer.append(table.getFullyQualifiedName());
            if (newVerb.isSelectParenthesis()) {
                buffer.append(")");
            }
        }
    }

}
