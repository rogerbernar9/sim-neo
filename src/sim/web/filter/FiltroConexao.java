package sim.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import sim.persistence.HibernateUtil;

@WebFilter("/conexaoFilter")
public class FiltroConexao implements Filter {
	
	private SessionFactory sessionFactory;

    public FiltroConexao() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			this.sessionFactory = HibernateUtil.getSessionFactory();

			this.sessionFactory.getCurrentSession().beginTransaction();
			
			chain.doFilter(request, response);
			
			this.sessionFactory.getCurrentSession().getTransaction().commit();
			this.sessionFactory.getCurrentSession().close();
			

			
		} catch (Throwable tab) {
			try {
				if(this.sessionFactory.getCurrentSession().getTransaction().isActive())	{
					this.sessionFactory.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable ta) {
				ta.printStackTrace();
			}
			tab.printStackTrace();
			throw new ServletException();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
