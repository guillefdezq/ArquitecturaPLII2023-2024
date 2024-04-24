
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolConstant;
import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Declaracionconstante extends NonTerminal {

	  /*
	   * declaracionConstante ::= CONSTANTE IDENTIFICADOR NUMERO SEMI_COLON {: RESULT = null; :};
	   */
		  
	  	protected TypeSimple numero;	// es esto correcto 1
		protected SymbolConstant SymbolConstantIdentificador; // segunda producción identificador
	    protected Temporal temporal; // por si acaso

	    public Declaracionconstante() {
	      super();
	    }

		public Declaracionconstante( SymbolConstant SymbolConstantIdentificador) {
			this();
			this.SymbolConstantIdentificador = SymbolConstantIdentificador;      
		  }

	    public Declaracionconstante(TypeSimple numero, SymbolConstant SymbolConstantIdentificador) {
	      this();
	      this.numero = numero;	// es esto correcto 1
	      this.SymbolConstantIdentificador = SymbolConstantIdentificador;      
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

		public SymbolConstant getSymbolConstantIdentificador() {
	      return SymbolConstantIdentificador;
	    }

	    public void setSymbolConstantIdentificador(SymbolConstant SymbolConstantIdentificador) {
	      this.SymbolConstantIdentificador = SymbolConstantIdentificador;
	    }

	    public TypeIF getType() {
	      if (SymbolConstantIdentificador != null) {
	        return SymbolConstantIdentificador.getType();
	      } else return null;
	    }

  }
	  