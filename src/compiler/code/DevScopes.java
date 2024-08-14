package compiler.code;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;

/*
 * N O T A    I M P O R T A N T E:
 * Esta clase se ha creado únicamente para hacer desarrollo.
 * Se deberá eliminar antes de entregar la práctica para más limpieza
 * y no tener que justificar esto (me da pereza).
 */

public class DevScopes {

    public DevScopes (ScopeManagerIF scopeManager) {
        System.out.println("------[ D E V   S C O P E M A N A G E R ]------");
        
            if (scopeManager != null) {
                System.out.println("ScopeManager recibido: " + scopeManager);
            } else {
                System.out.println("No se ha recibido ScopeManager!");
            }

            for (ScopeIF scope : scopeManager.getAllScopes()) {

                System.out.println("Scope ID" + scope.getId() + " : " + scope.getName());
                if (scope.getParentScope() != null) {
                System.out.println("    Padre: " + scope.getParentScope().getId() + " : " + scope.getParentScope().getName());
                } else { 
                    System.out.println("    No tiene padre");
                }

            }




        System.out.println("-----------------------------------------------");
    }
}