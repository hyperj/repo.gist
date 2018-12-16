package net.hyperj.gist.kettle;

import org.junit.Test;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.util.DateDetector;
import org.pentaho.di.core.util.StringEvaluationResult;
import org.pentaho.di.core.util.StringEvaluator;

import java.util.Arrays;
import java.util.List;

public class EvaluatorTest {

    @Test
    public void evaluator() {
        StringEvaluator evaluator = new StringEvaluator();
        List<String> strings = Arrays.asList("2018-11-11");
        strings.forEach(evaluator::evaluateString);
        StringEvaluationResult advicedResult = evaluator.getAdvicedResult();
        ValueMetaInterface conversionMeta = advicedResult.getConversionMeta();
        System.out.println(ValueMetaInterface.getTypeDescription(conversionMeta.getType()));
        System.out.println(conversionMeta.getDateFormat().toPattern());
        // Date Detector
        System.out.println(DateDetector.detectDateFormat(strings.get(0)));
    }
}
