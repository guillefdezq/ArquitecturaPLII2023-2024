def generar_archivos_java(lista_tokens):
  """
  Genera un archivo Java por cada token en la lista.

  Parámetros:
    lista_tokens (list): Lista de tokens.

  Retorno:
    None.
  """
  for token in lista_tokens:
    nombre_archivo = f"{token.capitalize()}.java"
    with open(nombre_archivo, "w") as archivo:
      contenido_archivo = generar_contenido_java(token.capitalize())
      archivo.write(contenido_archivo)

def generar_contenido_java(token):
  """
  Genera el contenido del archivo Java para un token.

  Parámetros:
    token (str): Token.

  Retorno:
    str: Contenido del archivo Java.
  """
  contenido = """
  package compiler.syntax.nonTerminal;

  import compiler.intermediate.Temporal;
  import compiler.semantic.symbol.SymbolVariable;
  import es.uned.lsi.compiler.semantic.type.TypeIF;

  public class Epsilon extends NonTerminal {

    protected SymbolVariable symbolVariableIdentificador;
    protected Temporal temporal;

    public Epsilon() {
      super();
    }

    public Epsilon(SymbolVariable symbolVariableIdentificador) {
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
  """
  contenido = contenido.replace("Epsilon", token.capitalize())
  return contenido

# Ejemplo de uso
lista_tokens = ["program", "funcionPrincipal", "epsilon", "seccionFunciones", "funcion", "seccionParametros", "declaracionParametros", "parametro", "seccionConstantes", "declaracionConstante", "seccionTipos", "declaracionTipo", "tamTipo", "seccionVariables", "declaracionVariable", "listadoIDs", "id", "expresion", "accesoVector", "llamadaFuncion", "bloque", "listadoSentencias", "sentencia", "sentenciaIncremento", "sentenciaAsignacion", "ref", "sentenciaAsignacionSuma", "sentenciaSi", "sentenciaAlternativas", "casosAlternativa", "casoAlt", "porDefecto", "sentenciaMientras", "sentenciaLlamadaFuncion", "parametros", "sentenciaSalida", "opcionesEscribe", "opcionesEscribeEnt", "sentenciaDevuelve"]
generar_archivos_java(lista_tokens)
