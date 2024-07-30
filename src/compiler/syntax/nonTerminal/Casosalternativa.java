
  package compiler.syntax.nonTerminal;

  import java.util.ArrayList;
  
  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Casosalternativa extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected ArrayList<Casoalt> casos = new ArrayList<Casoalt>();


    public ArrayList<Casoalt> getCasos() {
      return casos;
    }

    public void setCasos(ArrayList<Casoalt> casos) {
      this.casos = casos;
    }

    public void addCaso(Casoalt caso) {
      // Añadir al inicio (más fácil a la hora de hacer el for each en el parser)
      this.casos.add(0, caso);
    }

    public Casosalternativa() {
      super();
    }

    public Casosalternativa(SymbolVariable symbolVariableIdentificador) {
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
  