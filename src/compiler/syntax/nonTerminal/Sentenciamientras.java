
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Sentenciamientras extends Sentencia {

    Expresion expresion = new Expresion();
    Sentencia sentenciamientras = new Sentencia();

    public Sentenciamientras() {
      super();
    }

    public Sentenciamientras(Expresion expresion, Sentencia sentenciamientras) {
      super();
      this.expresion = expresion;
      this.sentenciamientras = sentenciamientras;
    }

    public Sentenciamientras(SymbolVariable symbolVariableIdentificador) {
      super();
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
  