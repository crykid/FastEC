package com.blank.art_compiler.compiler;

import com.blank.art_annotations.annotations.AppRegisterGenerator;
import com.blank.art_annotations.annotations.EntryGenerator;
import com.blank.art_annotations.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:
 */
@SuppressWarnings("unused")
@AutoService(Processor.class)
public class ArtProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generateEntryCode(roundEnv);
        generatePayEntryCode(roundEnv);
        generateRegisterEntryCode(roundEnv);
        return true;
    }

    /**
     * 获取整个类的注解类型
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }


    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }


    /**
     * 扫描每个类注解
     *
     * @param env        整个代码的环境
     * @param annotation
     * @param visitor
     */
    @SuppressWarnings("unused")
    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {

        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors
                    = typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                //获取类型键值对
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }

        }
    }

    /**
     * 生成文件
     *
     * @param env
     */
    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFilter(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    /**
     * 生成支付文件
     *
     * @param env
     */
    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setFilter(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    /**
     * 生成广播接收文件
     *
     * @param env
     */
    private void generateRegisterEntryCode(RoundEnvironment env) {
        final AppRegisterEntryVisitor appRegisterEntryVisitor = new AppRegisterEntryVisitor();
        appRegisterEntryVisitor.setFilter(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterEntryVisitor);
    }
}
