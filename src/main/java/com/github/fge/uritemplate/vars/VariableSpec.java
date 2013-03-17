package com.github.fge.uritemplate.vars;

import com.github.fge.uritemplate.URITemplateException;
import com.github.fge.uritemplate.expression.URITemplateExpression;

import java.util.List;
import java.util.Map;

/*
 * TODO: intelligence here
 *
 * A varspec has all the necessary elements to render according to the needs. It
 * needs two elements to operate:
 *
 * - the type of the value;
 * - the type of the expression.
 *
 * According to its own, internal information, its rendering method (name to be
 * decided) should be able to return a String to the caller with the appropriate
 * contents.
 */
public abstract class VariableSpec
{
    protected final VariableSpecType type;
    protected final String name;

    protected VariableSpec(final VariableSpecType type, final String name)
    {
        this.type = type;
        this.name = name;
    }

    public VariableSpecType getType()
    {
        return type;
    }

    public final String getName()
    {
        return name;
    }

    public final String render(final URITemplateExpression expression,
        final VariableValue value)
        throws URITemplateException
    {
        switch (value.getType()) {
            case SCALAR:
                return renderScalar(expression, value.getScalarValue());
            case ARRAY:
                return renderList(expression, value.getListValue());
            case MAP:
                return renderMap(expression, value.getMapValue());
        }

        throw new RuntimeException("How did I get there?");
    }

    protected abstract String renderScalar(
        final URITemplateExpression expression, final String value)
        throws URITemplateException;

    protected abstract String renderList(final URITemplateExpression expression,
        final List<String> value)
        throws URITemplateException;

    protected abstract String renderMap(final URITemplateExpression expression,
        final Map<String, String> map)
        throws URITemplateException;

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(final Object obj);

    @Override
    public abstract String toString();
}
