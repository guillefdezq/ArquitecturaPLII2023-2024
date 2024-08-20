
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.lexical.TokenIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Ref extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;
    protected Object pasarObjeto;
    protected TokenIF token;

    public Ref (Object pasarObjeto, TokenIF token) {
      this.token = token;
      this.pasarObjeto = pasarObjeto;
    }

    public TokenIF getToken() {
      return token;
    }

    public void setToken(TokenIF token) {
      this.token = token;
    }

    public Object getPasarObjeto() {
      return pasarObjeto;
    }

    public void setPasarObjeto(Object pasarObjeto) {
      this.pasarObjeto = pasarObjeto;
    }

    public Ref() {
      super();
    }

    public Ref(SymbolVariable symbolVariableIdentificador) {
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
  