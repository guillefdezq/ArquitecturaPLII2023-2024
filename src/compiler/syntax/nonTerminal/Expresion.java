
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
  protected String valor;

  // para vectores
  protected int indice;
  protected String nombreVector;
  // ----------

  // para funciones
  protected int numeroParametros;
  protected String nombreFuncion;
  // ----------
  
  protected TypeIF tipo;
  protected TemporalIF temporal;
  
  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
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

  public int getIndice() {
      return indice;
  }

  public void setIndice(int indice) {
      this.indice = indice;
  }

  public String getNombreVector() {
    return nombreVector;
  }

  public void setNombreVector(String nombreVector) {
    this.nombreVector = nombreVector;
  }

  
  

}
