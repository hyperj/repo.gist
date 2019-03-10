package net.hyperj.gist.udf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredJavaObject;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredObject;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.Text;
import org.junit.Test;

public class TestGenericUDFJsonFormat {

  @Test(expected = UDFArgumentException.class)
  public void testArgCnt1() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector valueOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
      ObjectInspector[] arguments = { valueOI };
      udf.initialize(arguments);
    }
  }

  @Test(expected = UDFArgumentException.class)
  public void testArgCnt3() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector valueOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
      ObjectInspector[] arguments = { valueOI, valueOI };
      udf.initialize(arguments);
    }
  }

  @Test(expected = UDFArgumentException.class)
  public void testArgInvalidType() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("__invalid__type__");
      udf.initialize(arguments);
    }
  }

  @Test
  public void testList() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("array<string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("[\"a\",\"b\",null]"));
      assertTrue(res instanceof List<?>);
      List<?> l = (List<?>) res;
      assertEquals(3, l.size());
      assertEquals(new Text("a"), l.get(0));
      assertEquals(new Text("b"), l.get(1));
      assertEquals(null, l.get(2));
    }
  }

  @Test
  public void testListNull() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("array<string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("null"));
      assertNull(res);
    }
  }

  @Test
  public void testSimpleStruct() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("struct<a:string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("{\"a\":\"b\"}"));
      assertTrue(res instanceof Object[]);
      Object o[] = (Object[]) res;
      assertEquals(new Text("b"), o[0]);
    }
  }

  @Test
  public void testStructNullField() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("struct<a:string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("{\"a\":null}"));
      assertTrue(res instanceof Object[]);
      Object o[] = (Object[]) res;
      assertEquals(null, o[0]);
    }
  }

  @Test
  public void testStructEmptyString() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("struct<a:string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs(""));
      assertNull(res);
    }
  }

  @Test
  public void testStructNull() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("struct<a:string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(new DeferredObject[] { new DeferredJavaObject(null), null });
      assertNull(res);
    }
  }

  @Test
  public void testStructNullComplexField() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("struct<a:struct<x:string>>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("{\"a\":null}"));
      assertTrue(res instanceof Object[]);
      Object o[] = (Object[]) res;
      assertEquals(null, o[0]);
    }
  }

  @Test(expected = HiveException.class)
  public void testUndeclaredStructField() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("struct<a:int>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("{\"b\":null}"));
      assertTrue(res instanceof Object[]);
      Object o[] = (Object[]) res;
      assertEquals(null, o[0]);
    }
  }

  @Test(expected = HiveException.class)
  public void testUnexpectedStruct() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("array<int>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("[1,22,2,{\"b\":null}]"));
      assertTrue(res instanceof Object[]);
      Object o[] = (Object[]) res;
      assertEquals(null, o[0]);
    }
  }

  @Test
  public void testMap() throws Exception {
    try (GenericUDFJsonFormat udf = new GenericUDFJsonFormat()) {
      ObjectInspector[] arguments = buildArguments("map<string,string>");
      udf.initialize(arguments);

      Object res = udf.evaluate(evalArgs("{\"a\":\"v\"}"));
      assertTrue(res instanceof Map);
      Map o = (Map) res;
      assertEquals(1, o.size());
      assertEquals(new Text("v"), o.get(new Text("a")));
    }
  }

  private DeferredObject[] evalArgs(String string) {
    return new DeferredObject[] { new DeferredJavaObject(new Text(string)), null };
  }

  private ObjectInspector[] buildArguments(String typeStr) {
    ObjectInspector valueOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
    ObjectInspector[] arguments = { valueOI, PrimitiveObjectInspectorFactory
        .getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.stringTypeInfo, new Text(typeStr)) };
    return arguments;
  }

}