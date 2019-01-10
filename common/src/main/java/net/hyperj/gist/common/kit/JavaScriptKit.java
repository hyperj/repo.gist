package net.hyperj.gist.common.kit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public enum JavaScriptKit {
    Instance;

    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public Object eval(String expression) throws ScriptException {
        return jse.eval(expression);
    }
}