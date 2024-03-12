
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Epsilon extends NonTerminal {

	  /*
    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
*/
	 
	  // Entiendo que este no terminal no tiene nada de especial, ¿no?... ¡¿NO?!
	  
    public Epsilon() {
      super();
    }

    /*
    
    public Epsilon(SymbolVariable symbolVariableIdentificador) {
      this();
      this.symbolVariableIdentificador = symbolVariableIdentificador;
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
     */
  }
  