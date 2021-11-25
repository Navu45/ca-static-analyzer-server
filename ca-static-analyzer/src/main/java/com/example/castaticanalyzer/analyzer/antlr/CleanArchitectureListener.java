// Generated from CleanArchitecture.g4 by ANTLR 4.7.1
package com.example.castaticanalyzer.analyzer.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CleanArchitectureParser}.
 */
public interface CleanArchitectureListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CleanArchitectureParser#unit_declaration}.
	 * @param ctx the parse tree
	 */
	void enterUnit_declaration(CleanArchitectureParser.Unit_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CleanArchitectureParser#unit_declaration}.
	 * @param ctx the parse tree
	 */
	void exitUnit_declaration(CleanArchitectureParser.Unit_declarationContext ctx);
}