package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.intermediate.Label;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeSimple;
import compiler.syntax.nonTerminal.Expresion;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.VariableIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

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

        StringBuffer b = new StringBuffer();
        b.append("; " + quadruple.toString() + "\n");
            

        if (quadruple.getOperation().equals("MV")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("MOVE " + o1 + ", " + r);
        } else if (quadruple.getOperation().equals("STP")) {
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());
            Variable var = (Variable) quadruple.getResult();
            int rdir = var.getAddress();

            b.append("ADD #" + rdir + ", " + o2 + "\n");
            b.append("SUB .IX, .A\n");
            b.append("MOVE " + o1 + ", [.A]\n"); 
            
        } else if (quadruple.getOperation().equals("MVP")) {
            String r = operacion(quadruple.getResult());
            String o2 = operacion(quadruple.getSecondOperand());
            
            Variable var = (Variable) quadruple.getFirstOperand();
            int o1dir = var.getAddress();
            
            b.append("ADD #" + o1dir + ", " + o2 + "\n");
            b.append("SUB .IX, .A\n");
            b.append("MOVE [.A], " + r + "\n"); 

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

            b.append("CMP " + r + ", " + o1 + "\n");

        } else if (quadruple.getOperation().equals("NEG")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());

            LabelFactory labelFactory = new LabelFactory();
			LabelIF labelVerdad = labelFactory.create();
			LabelIF labelFalso = labelFactory.create();
            LabelIF labelFin = labelFactory.create();

            b.append("CMP " + o1 + ", #0\n");       
            b.append("BZ /" + labelVerdad.toString() + "\n");
            b.append("BNZ /" + labelFalso.toString() + "\n");
            b.append(labelVerdad.toString() + ": MOVE #1, " + r + "\n");
            b.append("BR /" + labelFin.toString() + "\n");
            b.append(labelFalso.toString() + ": MOVE #0, " + r + "\n");
            b.append(labelFin.toString() + ": NOP\n");

        } else if (quadruple.getOperation().equals("LS")) {
            
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            String o2 = operacion(quadruple.getSecondOperand());

            LabelFactory labelFactory = new LabelFactory();
            LabelIF labelVerdad = labelFactory.create();
			LabelIF labelFalso = labelFactory.create();
            LabelIF labelFin = labelFactory.create();

            // MENOR QUE debe resultar en un número negativo, entonces, la flag cero Z debe estar inactiva y signo S debe estar activa.

            b.append("CMP " + o1 + ", " + o2 + "\n");

            b.append("BZ /" + labelFalso.toString() + "\n");
            b.append("BP /" + labelFalso.toString() + "\n");

            b.append(labelVerdad.toString() + ": MOVE #1, " + r + "\n");
            b.append("BR /" + labelFin.toString() + "\n");
            b.append(labelFalso.toString() + ": MOVE #0, " + r + "\n");
            b.append(labelFin.toString() + ": NOP\n");

        } else if (quadruple.getOperation().equals("AND")) {
            

            String r = operacion(quadruple.getResult());
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
            b.append("AXIOM: BR /PRINCIPAL\n");
            b.append("\n");
        } else if (quadruple.getOperation().equals("CADENA")) {       
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append(o1 + ": DATA \""+ r +"\"");
            return b.toString();
        } else if (quadruple.getOperation().equals("VARLOCAL")) {        
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("MOVE " + o1 + ", " + r);        
        } else if (quadruple.getOperation().equals("PRINCIPAL")) {    
            b.append("PRINCIPAL: NOP\n");
            b.append("; Por implementar correctamente, relacionado con subprogramas\n");
            b.append("MOVE .SP, .IX  ;guarda el valor de SP en IX\n");
            b.append("MOVE .IX, .IY  ;guarda el valor de IX en IY\n");
            b.append("PUSH #-1       ;valor de retorno\n");
            b.append("PUSH .IY       ;enlace de control\n");
            b.append("PUSH .SR       ;estado maquina\n");
            b.append("PUSH .IX       ;enlace de acceso\n");
            
        } else if (quadruple.getOperation().equals("PUNTERO_PRINCIPAL")) {   
            String r = operacion(quadruple.getResult());        
            b.append("SUB .IX, " + r + "\n");
            b.append("MOVE .A, .SP\n");

        } else if (quadruple.getOperation().equals("INICIO_FUNCION")) {     
            b.append("MOVE .SP, .IY  ;guarda el valor de SP en IY para guardar EC y establecer aquí IX(nuevo EA)\n");
            b.append("PUSH #-1       ;valor de retorno\n");
            b.append("PUSH .IY       ;enlace de control\n");
            b.append("PUSH .SR       ;estado maquina\n");
            b.append("PUSH .IX       ;enlace de acceso\n");
            
        } else if (quadruple.getOperation().equals("PARAM")) {        
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("PUSH " + r + "\n");
        } else if (quadruple.getOperation().equals("CALL")) {        
            String r = operacion(quadruple.getResult());
            b.append("MOVE .IY, .IX         ; Guarda el valor de IY en IX\n");
            b.append("CALL /" + r + "            ; Guarda PC y salta\n");
            b.append("MOVE #0[.IX], .R9     ; Guarda retorno\n");
            b.append("MOVE #-1[.IX], .SP    ; Restaura enlace de control\n");
            b.append("MOVE #-3[.IX], .IX    ; Restaura enlace de acceso\n");
            b.append("; Puede que algo este mal configurado...\n");
            b.append("; Necesario: Recuperacion de la pila a su sitio original\n");
            
            
        } else if (quadruple.getOperation().equals("VARSUBPROGRAMA")) {       
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());

            b.append("MOVE " + o1 + "[.IX], " + r); 

        } else if (quadruple.getOperation().equals("PUNTERO_FUNCION")) {       
            String r = operacion(quadruple.getResult());    
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("SUB .IX, " + r + "\n");
            b.append("MOVE .A, .SP\n"); 

        } else if (quadruple.getOperation().equals("VALOR_RETORNO")) {        
            String r = operacion(quadruple.getResult());
            b.append("MOVE .R9, " + r + "\n");

        } else if (quadruple.getOperation().equals("FIN_FUNCION")) {        
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append(r.toString() + ": NOP\n");
            
            b.append("SUB .IX, " + o1 + "\n");
            b.append("MOVE .A, .SP\n");
            b.append("RET\n\n\n\n");

        } else if (quadruple.getOperation().equals("FIN_PRINCIPAL")) {
            String r = operacion(quadruple.getResult());
            b.append(r.toString() + ": HALT\n");
            
        } else if (quadruple.getOperation().equals("DEVUELTO")) {       
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            
            if (o1 != null) {
                b.append("MOVE " + o1 + ", #-0[.IX]\n");
            }
            
            b.append("BR /" + r.toString() + "    ;rescata de la pila el contenido del contador de programa\n");
        } else if (quadruple.getOperation().equals("INL")) {
            String r = operacion(quadruple.getResult());
            b.append(r.toString() + ": NOP\n");
        } else if (quadruple.getOperation().equals("BR")) {
            String r = operacion(quadruple.getResult());
            b.append("BR /" + r.toString() + "\n");
        } else if (quadruple.getOperation().equals("BZ")) {
            String r = operacion(quadruple.getResult());
            String o1 = operacion(quadruple.getFirstOperand());
            b.append("CMP " + r + ", #0\n");   
            b.append("BZ /" + o1.toString() + "\n");
        } else if (quadruple.getOperation().equals("BNZ")) {        
            String r = operacion(quadruple.getResult());
            b.append("BNZ /" + r.toString() + "\n");
        } else if (quadruple.getOperation().equals("WRSTR")) {        
            String r = operacion(quadruple.getResult());
            if (r != null) b.append("WRSTR /" + r + " \n");
        } else if (quadruple.getOperation().equals("WRINT")) {        
            String r = operacion(quadruple.getResult());
            if (r != null) b.append("WRINT " + r + " \n");
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
            return "" + ((Label) o).getName() + "";
        } return null;
    }
}
