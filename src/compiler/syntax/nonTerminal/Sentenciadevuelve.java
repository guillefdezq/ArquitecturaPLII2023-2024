// NO SE UTILIZA!
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Sentenciadevuelve extends Sentencia {

    protected boolean isDevuelveVacio = true;
    protected String valor;

    public Sentenciadevuelve() {
      super();
    }

    public Sentenciadevuelve(String valor) {
      super();
      this.valor = valor;
    }

    public boolean isDevuelveVacio() {
      return isDevuelveVacio;
    }

    public void setDevuelveVacio(boolean isDevuelveVacio) {
      this.isDevuelveVacio = isDevuelveVacio;
    }

    public String getValor() {
      return valor;
    }

    public void setValor(String valor) {
      this.valor = valor;
    }

    


  }
  