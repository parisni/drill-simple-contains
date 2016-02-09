package org.apache.drill.contrib.function;

import io.netty.buffer.DrillBuf;
import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.holders.NullableVarCharHolder;
import org.apache.drill.exec.expr.holders.VarCharHolder;
import org.apache.drill.exec.expr.holders.BitHolder;
import javax.inject.Inject;

@FunctionTemplate(
        name = "contains",
        scope = FunctionTemplate.FunctionScope.SIMPLE,
        nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class SimpleContains implements DrillSimpleFunc {

    @Param
    NullableVarCharHolder input;

    @Param(constant = true)
    VarCharHolder pattern;

    @Output
    BitHolder out;

    @Inject
    DrillBuf buffer;

    public void setup() {
    }

    public void eval() {

        // get the value and replace with
        String patternValue = org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers.getStringFromVarCharHolder(pattern);
        String stringValue = org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers.toStringFromUTF8(input.start, input.end, input.buffer);

        // does it matches ?
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(patternValue);
        java.util.regex.Matcher m = p.matcher(stringValue);
        boolean b = m.find();
        int result = b ? 1 : 0;

        // put the output value in the out buffer
        out.value = result;
    }

}
