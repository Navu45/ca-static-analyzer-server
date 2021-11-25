// Generated from CleanArchitecture.g4 by ANTLR 4.7.1
package com.example.castaticanalyzer.analyzer.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CleanArchitectureParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CleanArchitectureVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CleanArchitectureParser#unit_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit_declaration(CleanArchitectureParser.Unit_declarationContext ctx);
}