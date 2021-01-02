/*
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2020 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
 /*
 * Copyright (C) 2020 JSQLParser.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package test;

import java.io.StringReader;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;

/**
 *
 * @author dave
 */
public class Test {

    public static void main(String[] args) {
//        test("create table test (x int)");
//        test("newverb table test (x int)");
//        test("create function test() returns int return 9");
        test2("create table test (x int);/*comment*/ create /*comment*/table /*comment*/test (x /*comment*/int);");
//        main1(args);
    }

    static void test(String sql) {
        try {
            CCJSqlParserManager pm = new CCJSqlParserManager();
            Statement statement = pm.parse(new StringReader(sql));
            System.out.println(statement);
        } catch (JSQLParserException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    static void test2(String sql) {
        try {
            Statements statements = CCJSqlParserUtil.parseStatements(sql);
            System.out.println(statements);
        } catch (JSQLParserException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
