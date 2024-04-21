
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Id extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected String nombre;
    protected String valor;

    public String getValor() {
      return valor;
    }

    public void setValor(String valor) {
      this.valor = valor;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Id() {
      super();
    }
	
	public Id(String nombre) {
	  super();
	  this.nombre = nombre;
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
  