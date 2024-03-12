
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Declaracionconstante extends NonTerminal {

	  /*
	   * declaracionConstante ::= CONSTANTE IDENTIFICADOR NUMERO SEMI_COLON {: RESULT = null; :};
	   */
		  
	  	protected TypeSimple numero;	// es esto correcto 1
		protected SymbolVariable symbolVariableIdentificador; // segunda producción identificador
	    protected Temporal temporal; // por si acaso

	    public Declaracionconstante() {
	      super();
	    }

	    public Declaracionconstante(TypeSimple numero, SymbolVariable symbolVariableIdentificador) {
	      this();
	      this.numero = numero;	// es esto correcto 1
	      this.symbolVariableIdentificador = symbolVariableIdentificador;      
	    }

	    public TypeSimple getNumero() {
			return numero;
		}

		public void setNumero(TypeSimple numero) {
			this.numero = numero;
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
	  