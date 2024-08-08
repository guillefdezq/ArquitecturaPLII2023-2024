
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
import compiler.semantic.symbol.SymbolParameter;
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
     protected SymbolParameter symbolParametroIdentificador;
     
     // segunda producción
     protected TypeIF tipoIdentificador;

    
      
    protected Temporal temporal;




    public Parametro(TypeSimple typeSimple, SymbolParameter symbolParametroIdentificador) {
      this();
      this.typeSimple = typeSimple;
      this.symbolParametroIdentificador = symbolParametroIdentificador;  
    }

    public Parametro(TypeIF tipoIdentificador, SymbolParameter symbolParametroIdentificador) {
      this();
      this.symbolParametroIdentificador = symbolParametroIdentificador;  
      this.tipoIdentificador = tipoIdentificador;
    }

    public Parametro() {
      super();
    }

    public Parametro(SymbolParameter symbolParametroIdentificador) {
      this();
      this.symbolParametroIdentificador = symbolParametroIdentificador;
    }

    public SymbolParameter getSymbolParametroIdentificador() {
      return symbolParametroIdentificador;
    }

    public void setSymbolParametroIdentificador(SymbolParameter symbolParametroIdentificador) {
      this.symbolParametroIdentificador = symbolParametroIdentificador;
    }

    public TypeIF getType() {
      if (symbolParametroIdentificador != null) {
        return symbolParametroIdentificador.getType();
      } else return null;
    }

  }
  