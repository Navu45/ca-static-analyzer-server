package com.example.castaticanalyzer.analyzer.antlrImpl;

import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParser;
import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParserBaseVisitor;
import com.example.castaticanalyzer.analyzer.parsing.CleanArchitectureLayer;
import com.example.castaticanalyzer.analyzer.parsing.DependencyType;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import org.springframework.stereotype.Component;

/** @DomainEntity */

@Component
public class CleanArchitectureVisitor extends JavaParserBaseVisitor<ParsedCode> {

    private ParsedCode review;
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */

    @Override
    public ParsedCode visitUnitDeclaration(JavaParser.UnitDeclarationContext ctx) {
        String declaration = ctx.CLEAN_ARCHITECTURE_UNIT().getText().split(" ")[1];
        CleanArchitectureLayer layer = null;
        switch (declaration) {
            case "@DomainEntity": {
                layer = CleanArchitectureLayer.DOMAIN;
                break;
            }
            case "@UseCase": {
                layer = CleanArchitectureLayer.USE_CASE;
                break;
            }
            case "@InterfaceAdapter": {
                layer = CleanArchitectureLayer.INTERFACE_ADAPTER;
                break;
            }
            case "@Framework": {
                layer = CleanArchitectureLayer.FRAMEWORK;
                break;
            }
        }
        review.setCleanArchitectureLayer(layer);
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
    public ParsedCode visitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
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
    public ParsedCode visitImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        review.addDependency(DependencyType.IMPORT,ctx.qualifiedName().getText());
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
    public ParsedCode visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        review.addDependency(DependencyType.CLASS_FIELD,
                ctx.typeType().getText() + " " + ctx.variableDeclarators().getText() + ";");

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
    public ParsedCode visitTypeType(JavaParser.TypeTypeContext ctx) {
        JavaParser.ClassOrInterfaceTypeContext context = ctx.classOrInterfaceType();
        if (context != null)
            review.addDependency(DependencyType.VARIABLE_TYPE, context.getText());
        return super.visitTypeType(ctx);
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
    public ParsedCode visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        review = new ParsedCode();
        visitChildren(ctx);
        if (review.getLayer() == null) {
            review.setCleanArchitectureLayer(CleanArchitectureLayer.NOT_DEFINED);
        }
        return review;
    }

    public CleanArchitectureVisitor() {
    }
}
