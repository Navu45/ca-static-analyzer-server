package com.example.castaticanalyzer.analyzer.antlrImpl;

import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParser;
import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParserBaseVisitor;
import com.example.castaticanalyzer.code.entity.CodeReview;
import org.springframework.stereotype.Component;

@Component
public class CleanArchitectureVisitor extends JavaParserBaseVisitor<CodeReview> {

    private CodeReview review = new CodeReview();
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */

    @Override
    public CodeReview visitUnitDeclaration(JavaParser.UnitDeclarationContext ctx) {
        review.setCleanArchitectureLayer(ctx.CLEAN_ARCHITECTURE_UNIT().getText().split(" ")[1]);
        return review;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public CodeReview visitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        review.setPackageName(ctx.qualifiedName().getText());
        return review;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public CodeReview visitImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        review.addImport(ctx.qualifiedName().getText());
        return review;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public CodeReview visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        review.addField(ctx.typeType().getText());
        return review;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public CodeReview visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        visitChildren(ctx);
        return review;
    }

    public CleanArchitectureVisitor() {
    }
}
