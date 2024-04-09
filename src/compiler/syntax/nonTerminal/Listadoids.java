
  package compiler.syntax.nonTerminal;

  import java.util.ArrayList;

import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;
 

  public class Listadoids extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected ArrayList<String> identificadores = new ArrayList<String>();

    
    public ArrayList<String> getIdentificadores() {
		return identificadores;
	}

	public void setIdentificadores(ArrayList<String> identificadores) {
		this.identificadores = identificadores;
	}
	
	public void addElement(String identificador) {
		identificadores.add(identificador);
	}
    
	public void addList(Listadoids identificadores) {
		ArrayList<String> listado = identificadores.getIdentificadores();
		this.identificadores.addAll(listado);
	}
	
    public String debugOutput() {
    	String salida = "";
    	for (String string : identificadores) {
			salida = salida + " " +  string;
		}
    	return salida;
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
  