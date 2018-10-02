package sim.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sim.manager.LoginMB;

public class RedirecionaPagina implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//Captura o ManagedBean chamado
		 LoginMB login = (LoginMB) ((HttpServletRequest) request).getSession().getAttribute("loginMB");
		//Verifica se nosso ManagedBean ainda não //foi instanciado ou caso a 
		//variável loggedIn seja false, assim saberemos que // o usuário não está logado 
		 
		if (login != null && login.isLogado()) { 
			System.out.println(login);
			String contextPath = ((HttpServletRequest) request).getContextPath(); 
			//Redirecionamos o usuário imediatamente 
			//para a página de login.xhtml
			System.out.println(contextPath);
			((HttpServletResponse) response).sendRedirect(contextPath + "/faces/restrict/pedido.xhtml"); 
			
			}

			chain.doFilter(request, response); 
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
