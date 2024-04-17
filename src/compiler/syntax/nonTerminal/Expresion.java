
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Expresion extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador; // Utilizaré string, pero quiero cambiarlo
    protected String identificador;
    protected boolean esIdentificador = false;
    protected TypeIF tipo;
    
    public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public boolean isEsIdentificador() {
		return esIdentificador;
	}

	public void setEsIdentificador(boolean esIdentificador) {
		this.esIdentificador = esIdentificador;
	}

	public TypeIF getTipo() {
		return tipo;
	}

	public void setTipo(TypeIF tipo) {
		this.tipo = tipo;
	}

	public Temporal getTemporal() {
		return temporal;
	}

	public void setTemporal(Temporal temporal) {
		this.temporal = temporal;
	}

	protected Temporal temporal;

    public Expresion() {
      super();
    }

    public Expresion(String identificador) {
        super();
        this.identificador = identificador;
        this.esIdentificador = true;
    }

    public Expresion(TypeIF tipo) {
        super();
        this.tipo = tipo;
        this.esIdentificador = false;
    }
    
    public Expresion(SymbolVariable symbolVariableIdentificador) {
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
  