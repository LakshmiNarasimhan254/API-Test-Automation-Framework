package org.mln.listeners;


import org.mln.utils.ExcelDataProviderUtil;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * The AnnotationTransformer class implements the IAnnotationTransformer interface.
 *
 * The IAnnotationTransformer interface has one method, transform, which is overridden in the AnnotationTransformer class.
 *
 * The transform method takes four parameters:
 *
 * 1. iTestAnnotation - the annotation that is being transformed
 * 2. aClass - the class that the annotation is being transformed on
 * 3. constructor - the constructor that the annotation is being transformed on
 * 4. method - the method that the annotation is being transformed on
 *
 * The transform method is called for every annotation that is being transformed.
 *
 * The transform method is called before the test is run.
 *
 * The transform method is called for every annotation that is being transformed.
 *
 * The transform method is called before the test is run.
 *
 * The transform method is called for every annotation that is being transformed.
 *
 * The transform method is called before the
 */
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    // A method that is called before every test method.
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
       iTestAnnotation.setDataProvider("getExcelData");
       iTestAnnotation.setDataProviderClass(ExcelDataProviderUtil.class);
      // iTestAnnotation.setRetryAnalyzer(RetryFailedTests.class);
    }
}
