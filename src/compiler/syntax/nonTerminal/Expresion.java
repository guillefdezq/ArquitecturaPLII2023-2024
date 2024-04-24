
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.SemanticErrorManager;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expresion extends NonTerminal {
  
  protected SymbolVariable symbolVariableIdentificador; // Utilizaré string, pero quiero cambiarlo
  protected String identificador;
  protected int valor;
  
  protected TypeIF tipo;
  protected TemporalIF temporal;
  
  public int getValor() {
    return valor;
  }

  public void setValor(int valor) {
    this.valor = valor;
  }
  
  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public TypeIF getTipo() {
    return tipo;
  }

  public void setTipo(TypeIF tipo) {
    this.tipo = tipo;
  }

  public TemporalIF getTemporal() {
    return temporal;
  }

  public void setTemporal(TemporalIF temporal) {
    this.temporal = temporal;
  }


  public Expresion() {
    super();
  }

  public Expresion(String identificador) {
      super();
      this.identificador = identificador;
  }

  public Expresion(TypeIF tipo) {
      super();
      this.tipo = tipo;
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
