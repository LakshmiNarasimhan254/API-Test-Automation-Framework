package org.mln.listeners;

import org.mln.constants.FrameworkConstants;
import org.mln.utils.ExcelUtil;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The above class implements the IMethodInterceptor interface and overrides the intercept method. The intercept method
 * takes in a list of IMethodInstance objects and an ITestContext object. The IMethodInstance object contains the test
 * method name and the ITestContext object contains the test context. The intercept method returns a list of
 * IMethodInstance objects
 */
public class MethodInterceptor implements IMethodInterceptor {
    /**
     * The function takes in a list of methods and a test context. It then creates a new list of methods to be executed. It
     * then iterates through the list of methods and checks if the method name matches the test name in the run manager. If
     * it does, it sets the invocation count and description of the method to the values in the run manager. It then adds
     * the method to the list of methods to be executed
     *
     * @param methods This is the list of all the methods that are to be executed.
     * @param context The context of the test.
     * @return List of IMethodInstance
     */
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance>executionList = new ArrayList<>();

            List<Map<String,String>> batchedata = ExcelUtil.getExcelRowDataAsMapList(FrameworkConstants.getRUNMANAGERPATH(),FrameworkConstants.getRUNMANAGERSHEET());
            for(int methodIndex=0;methodIndex<methods.size();methodIndex++){
                for (Map<String,String>bData: batchedata) {
                    if(bData.get("TestName").equalsIgnoreCase(methods.get(methodIndex).getMethod().getMethodName())&&
                        (bData.get("Execute").equalsIgnoreCase("YES"))){
                            methods.get(methodIndex).getMethod().setInvocationCount(Integer.parseInt(bData.get("InvocationCount")));
                            methods.get(methodIndex).getMethod().setDescription(bData.get("TestDescription"));
                            executionList.add(methods.get(methodIndex));
                        }

                }
            }
        return executionList;
    }
}
