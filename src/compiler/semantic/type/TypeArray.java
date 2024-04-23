package compiler.semantic.type;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeArray.
 */

// TODO: Student work
//       Include properties to characterize array type

public class TypeArray
    extends TypeBase
{   
    
    private int size;
    private int[] vector;

	/**
     * Constructor for TypeArray.
     * @param scope The declaration scope.
     */
    public TypeArray (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeArray.
     * @param scope The declaration scope.
     * @param name The name of the type.
     */
    public TypeArray (ScopeIF scope, String name)
    {
        super (scope, name);
    }

    public TypeArray (ScopeIF scope, String name, int size)
    {
        super (scope, name);
        this.size = size;
        this.vector = new int[size];
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return size;
    }

    public void setSize (int size)
    {
        this.size = size;
    }

    public int[] getVector ()
    {
        return vector;
    }

    public void setVector (int[] vector)
    {
        this.vector = vector;
    }

    public void setElement (int index, int value)
    {
        this.vector[index] = value;
    }     

    public int getElement (int index)
    {
        return this.vector[index];
    }
}
