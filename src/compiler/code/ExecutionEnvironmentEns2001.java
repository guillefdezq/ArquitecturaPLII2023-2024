package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.intermediate.Label;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.type.TypeSimple;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }

    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {      
        //TODO: Student work  // RE: oki doki!

        StringBuffer b = new StringBuffer();
        b.append("; " + quadruple.toString() + "\n");
            

        if (quadruple.getOperation().equals("MV")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("MOVE " + o1 + ", " + r);
        } else if (quadruple.getOperation().equals("ADD")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());
            b.append("ADD " + o1 + ", " + o2 + "\n");
            b.append("MOVE .A , " + r + "\n");
        } else if (quadruple.getOperation().equals("MUL")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());
            b.append("MUL " + o1 + ", " + o2 + "\n");
            b.append("MOVE .A , " + r + "\n");
        } else if (quadruple.getOperation().equals("EQ")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());

            LabelFactory labelFactory = new LabelFactory();
			LabelIF labelVerdad = labelFactory.create();
			LabelIF labelFalso = labelFactory.create();
            LabelIF labelFin = labelFactory.create();

            b.append("CMP " + o1 + ", " + o2 + "\n");
            b.append("BZ /" + labelVerdad.toString() + "\n");
            b.append("BNZ /" + labelFalso.toString() + "\n");
            b.append(labelVerdad.toString() + ": MOVE #1, " + r + "\n");
            b.append("BR /" + labelFin.toString() + "\n");
            b.append(labelFalso.toString() + ": MOVE #0, " + r + "\n");
            b.append(labelFin.toString() + ": NOP\n");

        } else if (quadruple.getOperation().equals("CMP")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());

            LabelFactory labelFactory = new LabelFactory();

            b.append("CMP " + r + ", " + o1 + "\n");

        } else if (quadruple.getOperation().equals("NEG")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());

            LabelFactory labelFactory = new LabelFactory();
			LabelIF labelVerdad = labelFactory.create();
			LabelIF labelFalso = labelFactory.create();
            LabelIF labelFin = labelFactory.create();

            b.append("CMP " + o1 + ", #0\n");       // Si es igual a falso=0, pasar a verdadero, cualquier otro valor a falso.
            b.append("BZ /" + labelVerdad.toString() + "\n");
            b.append("BNZ /" + labelFalso.toString() + "\n");
            b.append(labelVerdad.toString() + ": MOVE #1, " + r + "\n");
            b.append("BR /" + labelFin.toString() + "\n");
            b.append(labelFalso.toString() + ": MOVE #0, " + r + "\n");
            b.append(labelFin.toString() + ": NOP\n");

        } else if (quadruple.getOperation().equals("LS")) {
            
            //("LS", temporal, expra.getTemporal(), exprb.getTemporal());

            String r = operacion(quadruple.getResult());  // Temporal
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());

            LabelFactory labelFactory = new LabelFactory();
			LabelIF labelVerdad = labelFactory.create();
			LabelIF labelFalso = labelFactory.create();
            LabelIF labelFin = labelFactory.create();

            // MENOR QUE debe resultar en un número negativo, entonces, la flag cero Z debe estar inactiva y signo S debe estar activa.

            b.append("CMP " + o1 + ", " + o2 + "\n");
            b.append("BNZ /" + labelVerdad.toString() + "\n");
            b.append("BN /" + labelVerdad.toString() + "\n");

            b.append("BZ /" + labelFalso.toString() + "\n");
            b.append("BP /" + labelFalso.toString() + "\n");
            b.append(labelVerdad.toString() + ": MOVE #1, " + r + "\n");
            b.append("BR /" + labelFin.toString() + "\n");
            b.append(labelFalso.toString() + ": MOVE #0, " + r + "\n");
            b.append(labelFin.toString() + ": NOP\n");

        } else if (quadruple.getOperation().equals("AND")) {
            
            //("LS", temporal, expra.getTemporal(), exprb.getTemporal());

            String r = operacion(quadruple.getResult());  // Temporal
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());

            LabelFactory labelFactory = new LabelFactory();
			
            LabelIF labelVerdadA = labelFactory.create();
			LabelIF labelFalsoA = labelFactory.create();
            LabelIF labelFinA = labelFactory.create();
            
            LabelIF labelVerdadB = labelFactory.create();
            LabelIF labelFalsoB = labelFactory.create();
            LabelIF labelFinB = labelFactory.create();

            LabelIF labelVerdad = labelFactory.create();
			LabelIF labelFalso = labelFactory.create();
            LabelIF labelFin = labelFactory.create();

            // Voy a utilizar registros para este caso, pero no sé si puedo realmente utilizarlo.

            // conversion a logica binaria interna A
            b.append("CMP " + o1 + ", #0\n");
            b.append("BZ /" + labelFalsoA.toString() + "\n");
            b.append("BNZ /" + labelVerdadA.toString() + "\n");
            b.append(labelVerdadA.toString() + ": MOVE #1, .R0\n");
            b.append("BR /" + labelFinA.toString() + "\n");
            b.append(labelFalsoA.toString() + ": MOVE #0, .R0\n");
            b.append(labelFinA.toString() + ": NOP\n");

            // conversion a logica binaria interna B
            b.append("CMP " + o2 + ", #0\n");
            b.append("BZ /" + labelFalsoB.toString() + "\n");
            b.append("BNZ /" + labelVerdadB.toString() + "\n");
            b.append(labelVerdadB.toString() + ": MOVE #1, .R1\n");
            b.append("BR /" + labelFinB.toString() + "\n");
            b.append(labelFalsoB.toString() + ": MOVE #0, .R1\n");
            b.append(labelFinB.toString() + ": NOP\n");

            b.append("AND .R0, .R1\n");

            b.append("CMP .A, #1\n");
            b.append("BZ /" + labelVerdad.toString() + "\n");
            b.append("BNZ /" + labelFalso.toString() + "\n");
            b.append(labelVerdad.toString() + ": MOVE #1, " + r + "\n");
            b.append("BR /" + labelFin.toString() + "\n");
            b.append(labelFalso.toString() + ": MOVE #0, " + r + "\n");
            b.append(labelFin.toString() + ": NOP\n");

        } else if (quadruple.getOperation().equals("AXIOM")) {
            //b.append("AXIOM: NOP");
        } else if (quadruple.getOperation().equals("CADENA")) {
                    String r = operacion(quadruple.getResult());
                String o1 = operacion(quadruple.getFirstOperand());
                b.append(o1 + ": DATA \""+ r +"\"");
                return b.toString();
        } else if (quadruple.getOperation().equals("PRINCIPAL")) {
            b.append("PRINCIPAL: NOP\n");
            b.append("; Por implementar correctamente, relacionado con subprogramas\n");
            b.append(";MOVE .SP, .IX  ;guarda el valor de SP en IX\n");
            b.append(";PUSH #-1       ;valor de retorno\n");
            b.append(";PUSH .IX       ;enlace de control\n");
            b.append(";PUSH .SR       ;estado máquina\n");
            b.append(";PUSH .IX       ;enlace de acceso\n");
        } else if (quadruple.getOperation().equals("INICIA_FUNCION")) {
            b.append("MOVE .SP, .R0  ;guarda el valor de SP en R0\n");
            b.append("PUSH #-1       ;valor de retorno\n");
            b.append("PUSH .R0       ;enlace de control\n");
            b.append("PUSH .SR       ;estado máquina\n");
            b.append("PUSH .IX       ;enlace de acceso\n");
        } else if (quadruple.getOperation().equals("PARAM")) {
            String r = operacion(quadruple.getResult());
            b.append("PUSH " + r + "\n");
        } else if (quadruple.getOperation().equals("LLAMA_FUNCION")) {
            String r = operacion(quadruple.getResult());
            b.append("MOVE .R0, .IX ;inicio registro activación llamada\n");
            b.append("CALL /"+r+" ;guarda el contador de programa en la pila\n");
            b.append("MOVE .IX, .SP ;recuperar el SP\n");
            b.append("MOVE #0[.IX], .R1 ;guardar retorno en R1\n");
            b.append("MOVE #-3[.IX], .R0 ;recuperar dirección enlace acceso\n");
            b.append("MOVE .R0, .IX ; restaurar enlace acceso\n");
        } else if (quadruple.getOperation().equals("FUNCION")) {
            String r = operacion(quadruple.getResult());
            b.append(r + ": NOP\n");
        } else if (quadruple.getOperation().equals("VARLOCAL")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("MOVE " + o1 + ", " + r);        
        } else if (quadruple.getOperation().equals("VARLOCAL") || quadruple.getOperation().equals("PARAM")) {
            b.append("; sin declarar\n");
        } else if (quadruple.getOperation().equals("PUNTEROFINAL")) {
            // String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            /// FALTA USAR R, temporal
            b.append("SUB .IX, " + o1+ "\n");
            b.append("MOVE .A, .SP\n");
        } else if (quadruple.getOperation().equals("FIN_FUNCION")) {
            // String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            /// FALTA USAR R, LABEL
            b.append("SUB .IX, " + o1+ ";posiciona el puntero de pila SP para recuperar el contador del programa\n"); 
            b.append("MOVE .A, .SP\n");
            b.append("RET ;rescata de la pila el contenido del contador de programa\n\n\n\n");
        } else if (quadruple.getOperation().equals("INL")) {
            String r = operacion(quadruple.getResult());
            b.append(r.toString() + ": NOP\n");
        } else if (quadruple.getOperation().equals("BR")) {
            String r = operacion(quadruple.getResult());
            b.append("BR /" + r.toString() + "\n");
        } else if (quadruple.getOperation().equals("BRF")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("CMP " + r + ", #0\n");   /// CUIDADO, me da la sensación de que faltan ciertos parámetros para que sea exacto.
            b.append("BZ /" + o1.toString() + "\n");
        } else if (quadruple.getOperation().equals("BNZ")) {
            String r = operacion(quadruple.getResult());
            b.append("BZ /" + r.toString() + "\n");
        } else if (quadruple.getOperation().equals("DEVUELTO")) {       /// Este es el conflicto
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());  /// En qué situación se usa esto?
            // b.append("MOVE .R1, "+ r +" ;inicio registro activación llamada\n"); // En qué línea va esto?
            if(r!=null)
                b.append("MOVE " + r + ", #-0[.IX]\n");
            //una función cualquiera
            if(quadruple.getFirstOperand()!=null) {
                //el pc está guardado detrás de los parámetros en la pila, se guarda con instrucción call en SP
                b.append("SUB .IX, " + o1+ ";posiciona el puntero de pila SP para recuperar el contador del programa\n"); 
                b.append("MOVE .A, .SP\n");
                b.append("RET;rescata de la pila el contenido del contador de programa\n\n\n\n");
            }
            //la función main
            else {
                b.append("HALT\n");
            }

        } else if (quadruple.getOperation().equals("WRSTR")) {
            // String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("WRSTR /" + o1 + " \n");
        } else if (quadruple.getOperation().equals("WRINT")) {
            String r = operacion(quadruple.getResult());
            b.append("WRINT " + r + " \n");
            b.append("WRCHAR #10 \n");
        } else {
            b.append("; sin declarar?\n");
        }
        return b.toString();
    }

    private String operacion(OperandIF o) {
        if (o instanceof Variable) {
            if (((Variable) o).getScope().getLevel() == 0) 
                return "/" + ((Variable) o).getAddress() + "";
            else return "#-" + ((Variable) o).getAddress() + "[.IX]";
        } else if (o instanceof Value) {
            return "#" + ((Value) o).getValue() + "";
        } else if (o instanceof Temporal) {
            return "#-" + ((Temporal) o).getAddress() + "[.IX]";
        } else if (o instanceof Label) {
            return "" + ((Label) o).getName() + "";  // tendría que ponerle / para que no tenga que repetirlo... algo en esta función está mal
        } return null;
    }
}
