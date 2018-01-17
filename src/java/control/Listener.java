/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author bruno
 */
public class Listener implements PhaseListener{
    private static final Long SerialVersionUID= 1L;

    @Override
    public void afterPhase(PhaseEvent pe) {
       System.out.println("END PHASE " + pe.getPhaseId());
       FacesContext context = pe.getFacesContext();	
//comentado porem funcionando
       /*        FacesContext ctx = FacesContext.getCurrentInstance();
        if(!ctx.getViewRoot().getViewId().equals("/login.xhtml")){
            Application app = ctx.getApplication();
            CntrPessoa u = app.evaluateExpressionGet(ctx, "#{cntrPessoa}", CntrPessoa.class);
            if (u.getPessoa().getApelido() != null ){
                System.out.println("TESTE APELIDO"+u.getPessoa().getApelido());
                NavigationHandler handler = app.getNavigationHandler();
                handler.handleNavigation(ctx, "", "index");
                ctx.renderResponse();
            }
        }
*/    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        System.out.println("START PHASE " + pe.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
//        System.out.println("FASEID:"+PhaseId.VALUES.toString());
//        return PhaseId.ANY_PHASE;
        return PhaseId.RESTORE_VIEW;
    }
    
}
