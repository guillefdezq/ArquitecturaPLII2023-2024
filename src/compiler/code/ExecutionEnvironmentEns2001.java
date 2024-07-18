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
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

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
        //TODO: Student work

        // Usar un switch puede que sea más limpio

        StringBuffer b = new StringBuffer();
        String r = operacion(quadruple.getResult());
        String o1 = operacion(quadruple.getFirstOperand());
        String o2 = operacion(quadruple.getSecondOperand());

        b.append(";" + quadruple.toString() + "\n");


        if (quadruple.getOperation().equals("MV")) {
            b.append("MOVE " + o1 + ", " + r);
        } else if (quadruple.getOperation().equals("ADD")) {
            b.append("ADD " + o1 + ", " + o2 + "\n");
            b.append("MOVE .A , " + r);
        } else if (quadruple.getOperation().equals("MUL")) {
            b.append("MUL " + o1 + ", " + o2 + "\n");
            b.append("MOVE .A , " + r);
        } else if (quadruple.getOperation().equals("AXIOM")) {
            //b.append("AXIOM: NOP");
        } else if (quadruple.getOperation().equals("CADENA")) {
            b.append(o1 + ": DATA \""+ r +"\"");
        } else if (quadruple.getOperation().equals("PRINCIPAL")) {
            b.append("PRINCIPAL: NOP\n");
            b.append("; Por implementar correctamente, relacionado con subprogramas");
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
            b.append("PUSH " + r);
        } else if (quadruple.getOperation().equals("LLAMA_FUNCION")) {
            b.append("MOVE .R0, .IX ;inicio registro activación llamada\n");
            b.append("CALL /"+r+" ;guarda el contador de programa en la pila\n");
            b.append("MOVE .IX, .SP ;recuperar el SP\n");
            b.append("MOVE #0[.IX], .R1 ;guardar retorno en R1\n");
            b.append("MOVE #-3[.IX], .R0 ;recuperar dirección enlace acceso\n");
            b.append("MOVE .R0, .IX ; restaurar enlace acceso\n");
        } else if (quadruple.getOperation().equals("FUNCION")) {
            b.append(r + ": NOP");
        } else if (quadruple.getOperation().equals("VARLOCAL") || quadruple.getOperation().equals("PARAM")) {
            // a implementar?
        } else if (quadruple.getOperation().equals("PUNTEROFINAL")) {
            /// FALTA USAR R, temporal
            b.append("SUB .IX, " + o1+ "\n");
            b.append("MOVE .A, .SP\n");
        } else if (quadruple.getOperation().equals("FIN_FUNCION")) {
            /// FALTA USAR R, LABEL
            b.append("SUB .IX, " + o1+ ";posiciona el puntero de pila SP para recuperar el contador del programa\n"); 
            b.append("MOVE .A, .SP\n");
            b.append("RET ;rescata de la pila el contenido del contador de programa\n\n\n\n");
        } else if (quadruple.getOperation().equals("DEVUELTO")) {       /// Este es el conflicto
            // String o1 = operacion(quadruple.getFirstOperand());  /// En qué situación se usa esto?
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
                b.append("HALT ");
            }
        } else if (quadruple.getOperation().equals("WRSTR")) {
            b.append("WRSTR /" + o1 + " \n");
        } else if (quadruple.getOperation().equals("WRINT")) {
            b.append("WRINT " + r + " \n");
            b.append("WRCHAR #10 \n");
        } else {
            b.append("; NO IMPLEMENTADO");
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
            return "" + ((Label) o).getName() + "";
        } return null;
    }
}
