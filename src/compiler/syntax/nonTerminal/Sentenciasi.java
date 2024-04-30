
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Sentenciasi extends Sentencia {

    Expresion expresion = new Expresion();
    Sentencia sentenciasi = new Sentencia();
    Sentencia sentenciasino = new Sentencia();

    public Sentenciasi() {
      super();
    }

    public Sentenciasi(Expresion expresion, Sentencia sentenciasi, Sentencia sentenciasino) {
      super();
      this.expresion = expresion;
      this.sentenciasi = sentenciasi;
      this.sentenciasino = sentenciasino;
    }

    public Sentenciasi(SymbolVariable symbolVariableIdentificador) {
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
  