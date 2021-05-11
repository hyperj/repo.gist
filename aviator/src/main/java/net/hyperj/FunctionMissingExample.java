package net.hyperj;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.FunctionMissing;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Arrays;
import java.util.Map;

public class FunctionMissingExample {

  private static class TestFunctionMissing implements FunctionMissing {

    @Override
    public AviatorObject onFunctionMissing(final String name, final Map<String, Object> env,
                                           final AviatorObject... args) {
      // Returns the function name.
      System.out.println(
          "Function not found, name=" + name + ", env=" + env + ", args=" + Arrays.toString(args));
      return FunctionUtils.wrapReturn(name);
    }

  }

  public static void main(final String[] args) {
    // Set function missing handler.
    AviatorEvaluator.setFunctionMissing(new TestFunctionMissing());
    System.out.println(AviatorEvaluator.execute("test(1,2,3)"));
    System.out.println(AviatorEvaluator.execute("not_found(a=1,n=\"asd\",3)"));
  }
}