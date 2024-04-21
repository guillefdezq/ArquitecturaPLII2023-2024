
  package compiler.syntax.nonTerminal;

  import java.util.ArrayList;

import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;
 

  public class Listadoids extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected ArrayList<Id> objetos = new ArrayList<Id>();

    
    public ArrayList<Id> getObjetos() {
      return objetos;
    }

    public void setObjetos(ArrayList<Id> objetos) {
      this.objetos = objetos;
    }

    public void addObjeto(Id objeto) {
      this.objetos.add(objeto);
    }

	public Listadoids() {
      super();
    }
    
    public Listadoids(SymbolVariable symbolVariableIdentificador) {
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
  