package com.example.castaticanalyzer.analyzer.antlrImpl;

import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParser;
import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParserBaseVisitor;
import com.example.castaticanalyzer.analyzer.parsing.CleanArchitectureLayer;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import com.example.castaticanalyzer.analyzer.parsing.DependencyType;
import org.springframework.stereotype.Component;

@Component
public class CleanArchitectureVisitor extends JavaParserBaseVisitor<ParsedCode> {

    private ParsedCode review = new ParsedCode();
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

        CleanArchitectureLayer layer;
        switch (ctx.CLEAN_ARCHITECTURE_UNIT().getText().split(" ")[1]) {
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
            default:
            {
                layer = CleanArchitectureLayer.NOT_DEFINED;
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
        review.addDependency(DependencyType.FIELD,ctx.typeType().getText());
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
        review.addDependency(DependencyType.TYPE,ctx.classOrInterfaceType().getText());
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
        visitChildren(ctx);
        return review;
    }

    public CleanArchitectureVisitor() {
    }
}
