/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bruno
 */
public class Listener implements PhaseListener{
    private static final Long SerialVersionUID= 1L;
    String paginacomlogin[]= new String[]{"/pessoa.xhtml","/curso.xhtml"};

    @Override
    public void afterPhase(PhaseEvent pe) {
        System.out.println("END PHASE " + pe.getPhaseId());
        FacesContext context = pe.getFacesContext();
        String viewId = context.getViewRoot().getViewId();
        System.out.println("VIEWID:"+viewId);
        System.out.println("PAGINA:"+cntrUsuario.pagina);
        try {
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();			
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Boolean conectado = (Boolean) session.getAttribute("autenticado");			
            if (conectado == null) {
                   System.out.println("CONECTADO NULO");
                   for (String pag : paginacomlogin ){
                       if (pag.equals(viewId)){
                           cntrUsuario.pagina=viewId.replace("/","");
                           FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                           
                       }
                   }
                   //return;
            } else {
                if (!conectado) {
                    if (!viewId.equals("/login.xhtml")) {
                       System.out.println("desconectado");
                       FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                    }
                } else {
                        System.out.println("conectado");
                        //return;
                       // FacesContext.getCurrentInstance().getExternalContext().redirect("curso.xhtml");
                }
            }
            response.setHeader("Expires", "-1");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidade, proxy-revalidade, private, post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
//            return;
        } catch (IOException e) {			
        }        
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
//                    HttpServletRequest objHttpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//objHttpServletRequest.getRequestURI() //url corrente