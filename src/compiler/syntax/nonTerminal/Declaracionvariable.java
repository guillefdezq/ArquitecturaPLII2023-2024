
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Declaracionvariable extends NonTerminal {

    /* Código copiado de las diapositivas */
	  
	/* Copiado del cup:
	 * 
	 * declaracionVariable ::= ENTERO listadoIDs SEMI_COLON {: RESULT = null; :}
                      | IDENTIFICADOR listadoIDs SEMI_COLON {: RESULT = null; :};
	 * 
	 */

	protected TypeSimple typeSimple; // primera producción tipo de dato
	protected Listadoids listadoIDs; // aquí se usa el no terminal creado antes	  
	  
	protected SymbolVariable symbolVariableIdentificador; // segunda producción identificador

  protected TypeIF tipoIdentificador;

    protected Temporal temporal; // por si acaso

    public Declaracionvariable() {
      super();
    }

    public Declaracionvariable(TypeSimple typeSimple, Listadoids listadoIDs) {
      this();
      this.typeSimple = typeSimple;
      this.listadoIDs = listadoIDs;  
    }

    public Declaracionvariable(TypeIF tipoIdentificador, Listadoids listadoIDs) {
      this();
      this.listadoIDs = listadoIDs;  
      this.tipoIdentificador = tipoIdentificador;
    }

    public TypeSimple getTypeSimple() {
		return typeSimple;
	}

	public void setTypeSimple(TypeSimple typeSimple) {
		this.typeSimple = typeSimple;
	}

	public Listadoids getListadoIDs() {
		return listadoIDs;
	}

	public void setListadoIDs(Listadoids listadoIDs) {
		this.listadoIDs = listadoIDs;
	}

	public Temporal getTemporal() {
		return temporal;
	}

	public void setTemporal(Temporal temporal) {
		this.temporal = temporal;
	}

	public SymbolVariable getSymbolVariableIdentificador() {
      return symbolVariableIdentificador;
    }

    public void setSymbolVariableIdentificador(SymbolVariable symbolVariableIdentificador) {
      this.symbolVariableIdentificador = symbolVariableIdentificador;
    }

    public TypeIF getType() {
      if (symbolVariableIdentificador != null) {
        return symbolVariableIdentificador.getType();
      } else return null;
    }

  }
  