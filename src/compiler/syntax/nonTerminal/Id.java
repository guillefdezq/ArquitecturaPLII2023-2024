
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Id extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected String cadenaID;

    public String getCadenaID() {
		return cadenaID;
	}

	public void setCadenaID(String cadenaID) {
		this.cadenaID = cadenaID;
	}

	public Id() {
      super();
    }
	
	public Id(String nombre) {
	  super();
	  this.cadenaID = nombre;
    }

    public Id(SymbolVariable symbolVariableIdentificador) {
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

  }
  