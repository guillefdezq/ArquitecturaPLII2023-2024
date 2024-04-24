
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Sentenciadevuelve extends Sentencia {

    protected boolean isDevuelveVacio = true;
    protected int valor;

    public Sentenciadevuelve() {
      super();
    }

    public Sentenciadevuelve(int valor) {
      super();
      this.valor = valor;
    }

    public boolean isDevuelveVacio() {
      return isDevuelveVacio;
    }

    public void setDevuelveVacio(boolean isDevuelveVacio) {
      this.isDevuelveVacio = isDevuelveVacio;
    }

    public int getValor() {
      return valor;
    }

    public void setValor(int valor) {
      this.valor = valor;
    }

    


  }
  