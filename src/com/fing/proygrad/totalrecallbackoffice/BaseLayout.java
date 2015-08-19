package com.fing.proygrad.totalrecallbackoffice;

import com.fing.proygrad.totalrecallbackoffice.views.BaseScreenLayout;
import com.fing.proygrad.totalrecallbackoffice.views.DevicesScreen;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BaseLayout extends VerticalLayout{

	private static final long serialVersionUID;
    static{
        serialVersionUID = -2265922916170768007L;
    }
    //initialize root & other components
    {
        initRoot();
        initComponents();
    }
    private void initRoot(){
        //setStyleName("base-layout");
        /*    set component style name    */
        //setSplitPosition(150, Unit.PIXELS, false);
        /*Moves the position of the splitter with given position and unit.
        Parameters:
        pos the new size of the first region. Fractions are only allowed when unit is percentage.
        unit the unit (from Sizeable) in which the size is given.
        reverse if set to true the split splitter position is measured by the second region else it is measured by the first region
         */
    }
    private void initComponents(){
    	setSizeFull();
        verticalMenu    = new VerticalMenu();
        horizontalMenu = new HorizontalMenu();
        splitPanel = new HorizontalSplitPanel();
        splitPanel.setStyleName("base-layout");
        splitPanel.setSplitPosition(150, Unit.PIXELS, false);
        splitPanel.setFirstComponent(verticalMenu);
        
        //the content .
        //splitPanel.setSecondComponent(new Label("[ here goes the pretty body ]"));
        //splitPanel.setSecondComponent(new DevicesScreen());
        
        addComponent(horizontalMenu);
        addComponent(splitPanel);
        
        Component comp = (Component)this.getComponent(this.getComponentCount()-1);
        setExpandRatio(comp, 1.0f);
        
    }
    
    private HorizontalMenu horizontalMenu;
    private VerticalMenu verticalMenu;
    private HorizontalSplitPanel splitPanel;
    
    public void updateSplitPanel(Component newComponent){
    	
    	if(newComponent != null){
    		splitPanel.setSecondComponent(newComponent);
    	}
    	
    }
	
}
