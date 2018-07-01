package com.blank.art_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;
import javax.annotation.processing.Filer;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:
 */
public final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private Filer mFilter = null;
    private TypeMirror mTypeMirror = null;
    private String mPackagaName = null;

    public void setFilter(Filer filter) {
        this.mFilter = filter;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackagaName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void aVoid) {

        mTypeMirror = t;
        return aVoid;
    }

    /**
     * 生成模板代码
     */
    private void generateJavaCode() {
        //微信强制入口名字
        final TypeSpec targetActivity = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();
        final JavaFile javaFile = JavaFile.builder(mPackagaName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();
        try {
            javaFile.writeTo(mFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
