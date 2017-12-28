package filtro;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;


@SuppressWarnings("serial")
public class Controle implements Serializable{


		protected void addMsgInfo(String msg){
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		
		protected void addMsgWarning(String msg){
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		
		protected void addMsgError(String msg){
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		
		protected void tratamentoErroPadrao(Exception e){
			e.printStackTrace();
			notificarErro(e);
			addMsgError("Ocorreu um erro, tente novamente mais tarde.");
		}
		
		
		protected void notificarErro(Exception e){
			
		}
		
		private static String getStackTrace(Throwable t){
			String msg = t.toString() + "<br/>";
			
			if (t.getStackTrace() != null){
				for (StackTraceElement s : t.getStackTrace()){
					msg += s.toString() + "<br/>";
				}
			}
			
			if (t.getCause() != null){
				msg += "Caused by: " + t.getCause().toString() + "<br/>";
				
				if (t.getCause().getStackTrace() != null){
					for (StackTraceElement s : t.getCause().getStackTrace()){
						msg += s.toString() + "<br/>";
					}
				}
			}
			
			
			return msg;
		}
		
		
		public HttpServletRequest getCurrentRequest() {
			return (HttpServletRequest) getExternalContext().getRequest();
		}

		
		public HttpServletResponse getCurrentResponse() {
			return (HttpServletResponse) getExternalContext().getResponse();
		}

		
		public HttpSession getCurrentSession() {
			return getCurrentRequest().getSession(true);
		}
		
		private ExternalContext getExternalContext() {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
		
		public String getParameter(String param) {
			return getCurrentRequest().getParameter(param);
		}
		
		
		public Integer getParameterInt(String param) {
			return Integer.parseInt(getParameter(param));
		}
		
		public Integer getParameterInt(String param, int padrao) {
			String valor = getParameter(param);
			return valor != null ? Integer.parseInt(valor) : padrao;
		}
		
		public Usuario getUsuarioLogado() {
			return (Usuario) getCurrentSession().getAttribute("usuarioLogado");
		}
		
		
}

