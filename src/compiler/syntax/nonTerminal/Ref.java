
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.lexical.TokenIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Ref extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    
    protected SymbolIF symbol;
    protected Expresion expresion;
    protected boolean isExpresion = false;
    
    
    public boolean isExpresion() {
      return isExpresion;
    }

    public void setExpresion(boolean isExpresion) {
      this.isExpresion = isExpresion;
    }

    public Expresion getExpresion() {
      return expresion;
    }

    public void setExpresion(Expresion expresion) {
      this.isExpresion = true;
      this.expresion = expresion;
    }
    
    public SymbolIF getSymbol() {
      return symbol;
    }

    public void setSymbol(SymbolIF symbol) {
      this.symbol = symbol;
    }

    public Ref() {
      super();
    }

    public Ref(Expresion expresion) {
      this();
      this.expresion = expresion;
      this.isExpresion = true;
    }

    public Ref(SymbolIF symbol) { 
      this();
      this.symbol = symbol;
    }

    public Ref(SymbolVariable symbolVariableIdentificador) {
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
  