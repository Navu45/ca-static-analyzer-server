// Generated from CleanArchitecture.g4 by ANTLR 4.7.1
package com.example.castaticanalyzer.analyzer.antlr;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link CleanArchitectureVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class CleanArchitectureBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements CleanArchitectureVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitUnit_declaration(CleanArchitectureParser.Unit_declarationContext ctx) { return visitChildren(ctx); }
}