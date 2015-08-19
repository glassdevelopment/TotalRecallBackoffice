package com.fing.proygrad.totalrecallbackoffice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;






import com.fing.proygrad.totalrecallbackoffice.views.ChannelsScreen;
import com.fing.proygrad.totalrecallbackoffice.views.LoginView;
import com.fing.proygrad.totalrecallbackoffice.views.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;




import org.glassfish.jersey.servlet.ServletContainer;

@SuppressWarnings("serial")
@Theme("totalrecallbackoffice")
public class TotalrecallbackofficeUI extends UI {

	//@VaadinServletConfiguration(productionMode = false, ui = TotalrecallbackofficeUI.class)
	@WebServlet(value = "/rest/*", asyncSupported = true,  
			initParams = {
		    	@WebInitParam(name = "jersey.config.server.provider.packages", value = "com.fing.proygrad.totalrecallbackoffice.rest")}
	)
	public static class RestServlet extends ServletContainer {
	}
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TotalrecallbackofficeUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		BaseLayout lay = new BaseLayout();
		
		
		setContent(lay);
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	//@Override
	protected void init2(VaadinRequest request) {
		
		//
        // Create a new instance of the navigator. The navigator will attach
        // itself automatically to this view.
        //
        new Navigator(this, this);

        //
        // The initial log view where the user can login to the application
        //
        getNavigator().addView(LoginView.NAME, LoginView.class);//

        //
        // Add the main view of the application
        //
        getNavigator().addView(MainView.NAME, MainView.class);

        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof LoginView;

                if (!isLoggedIn && !isLoginView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(LoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
	}

}