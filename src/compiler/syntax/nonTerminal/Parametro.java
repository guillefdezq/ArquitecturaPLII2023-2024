
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Parametro extends NonTerminal {

    /*
     * ENTERO:entero IDENTIFICADOR:id
     * | IDENTIFICADOR:ident IDENTIFICADOR:id
     */

    
     // primera producción
     protected TypeSimple typeSimple;
     protected SymbolVariable symbolVariableIdentificador;
     
     // segunda producción
     protected TypeIF tipoIdentificador;

    
      
    protected Temporal temporal;




    public Parametro(TypeSimple typeSimple, SymbolVariable symbolVariableIdentificador) {
      this();
      this.typeSimple = typeSimple;
      this.symbolVariableIdentificador = symbolVariableIdentificador;  
    }

    public Parametro(TypeIF tipoIdentificador, SymbolVariable symbolVariableIdentificador) {
      this();
      this.symbolVariableIdentificador = symbolVariableIdentificador;  
      this.tipoIdentificador = tipoIdentificador;
    }

    public Parametro() {
      super();
    }

    public Parametro(SymbolVariable symbolVariableIdentificador) {
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
  