
  package compiler.syntax.nonTerminal;

  import java.util.ArrayList;

import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;
 

  public class Listadoids extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected ArrayList<String> listado = new ArrayList<String>();

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

	public ArrayList<String> getListado() {
		return listado;
	}

	public void setListado(ArrayList<String> listado) {
		this.listado = listado;
	}
	
	public void add(String elemento) {
		this.listado.add(elemento);
	}
	
	public void add(ArrayList<String> listado) {
		for (String elemento : listado) {
			this.add(elemento);
		}
	}

  }
  