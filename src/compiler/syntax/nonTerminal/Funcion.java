
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
import compiler.semantic.symbol.SymbolFunction;
import compiler.semantic.symbol.SymbolProcedure;
import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Funcion extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected SymbolProcedure symbolProcedure;
    protected Temporal temporal;

    public Funcion() {
      super();
    }

    public Funcion(SymbolVariable symbolVariableIdentificador) {
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

    public Temporal getTemporal() {
      return temporal;
    }

    public void setTemporal(Temporal temporal) {
      this.temporal = temporal;
    }

    public SymbolProcedure getSymbolProcedure() {
      return symbolProcedure;
    }

    public void setSymbolProcedure(SymbolProcedure symbolProcedure) {
      this.symbolProcedure = symbolProcedure;
    }

  }
  