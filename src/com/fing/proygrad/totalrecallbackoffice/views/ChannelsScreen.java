package com.fing.proygrad.totalrecallbackoffice.views;


import java.util.ArrayList;
import java.util.List;
















import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class ChannelsScreen extends BaseScreenLayout{

	
	private static final long serialVersionUID = 3084673053358943678L;
	private static final String TITLE = "Canales de datos";
	
	private List<JSONObject> channels;
	private HorizontalLayout container;
	private VerticalLayout content;
	private Table channelsTable;
	private Panel details;
	private Button addChannel;
	
	
	public ChannelsScreen() {
		super(TITLE);
		init();
	}
	
	private void init(){
		initComponents();
	}

	@Override
	protected void initComponents(){
		//removeAllComponents();
		removeAllComponentsExceptTitle();
		
		initChannelsTable();
		createButton();
		addComponent(addChannel);
		
		container = new HorizontalLayout();
		container.addComponent(channelsTable);
		createInfoPanel();
		addComponent(container);
		
//		addComponent(new Label("channels..."));
	}
	
	private void initChannelsTable(){
		
		channelsTable = new Table();
		//channelsTable.setSizeFull();
		channelsTable.setSelectable(true);
		channelsTable.setMultiSelect(false);
		channelsTable.setImmediate(true);
		
		channelsTable.addContainerProperty("Nombre", String.class, null);
		channelsTable.addContainerProperty("Acciones",  HorizontalLayout.class, null);
		
		channelsTable.setVisibleColumns(new Object[] { "Nombre", "Acciones" });
		channelsTable.setColumnHeaders(new String[] { "Nombre", "Acciones" });
		
		channelsTable.addItemClickListener(new ItemClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				showInfo((Integer)event.getItemId());
				
			}
		});
		
		populateTable();
	}
	
	private JSONObject createAttr(int i){
		
		JSONObject toRet = new JSONObject();
		for(int j = 0; j < 5; j++){
			toRet.put("attr" + j, "attrrr" + i + j);
		}
		
		
		return toRet;
	}
	
	private void populateTable(){
		
		channels = new ArrayList<JSONObject>();
		JSONObject obj;
		for(int i = 0; i < 10; i++){
			obj = new JSONObject();
			obj.put("Nombre", "Nombre " + i);
			obj.put("id", i);
			obj.put("Atributos", createAttr(i));
			channels.add(obj);
		}
		
		for(JSONObject elem : channels){
			channelsTable.addItem(new Object[]{elem.get("Nombre"), createActionsItem()} , new Integer((Integer)elem.get("id")));
		}
		
//		for(int i = 0; i < 10; i++){
//			channelsTable.addItem(new Object[]{"Nombre " + i, createActionsItem()} , new Integer(i));			
//		}
		
		if(channelsTable.size() > 10){
			channelsTable.setPageLength(10);
		}else{
			channelsTable.setPageLength(channelsTable.size());
		}
	}
	
	private HorizontalLayout createActionsItem(){
		Button edit;
		Button delete;
		
		edit = new Button("editar");
		edit.setStyleName(Reindeer.BUTTON_LINK);
		delete = new Button("borrar");
		delete.setStyleName(Reindeer.BUTTON_LINK);
		HorizontalLayout lay = new HorizontalLayout();
		
		lay.addComponent(edit);
		lay.addComponent(delete);
		
		return lay;
	}

	private void showInfo(int itemId){
		Label det;
		 
		if(details.isVisible()){
			content.removeAllComponents();
		}
		
		det =new Label(getFormatted(channels.get(itemId)), ContentMode.PREFORMATTED);
		det.setStyleName("details");
        content.addComponent(det);
        
 
        details.setContent(content);
        details.setVisible(true);
        
//        if(container.getComponentCount() == 1){
//        	container.addComponent(details);
//        }
	
        

		
	}
	
	private void createInfoPanel(){
		if(container.getComponentCount() == 1){
			details = new Panel("Detalles");
				
			details = new Panel("Detalles");
			details.setHeight(100.0f, Unit.PERCENTAGE);
	 
			content = new VerticalLayout();
			content.setWidth(500, Unit.PIXELS);
	        content.setMargin(true);
	        
	        details.setContent(content);
	        details.setVisible(true);
	        
	        container.addComponent(details);
	     }
	}
	
	private String getFormatted(JSONObject jsonn){
		
		String compactJson = "{\"playerID\":1234,\"name\":\"Test\",\"itemList\":[{\"itemID\":1,\"name\":\"Axe\",\"atk\":12,\"def\":0},{\"itemID\":2,\"name\":\"Sword\",\"atk\":5,\"def\":5},{\"itemID\":3,\"name\":\"Shield\",\"atk\":0,\"def\":10}]}";
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonn.toString()).getAsJsonObject();
        


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
        System.out.println(prettyJson);
		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		JsonParser jp = new JsonParser();
//		JsonElement je = jp.parse(json.toString());
//		System.out.println(jsonn.toString());
//		String a = gson.toJson(jsonn.toString());
//
//		System.out.println("hola \t hola");
//		System.out.println(a);
		return prettyJson;

	}
	
	private void createButton(){
		
		addChannel = new Button("Crear canal");
		addChannel.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//Window win = new Window("Nuevo Canal", new AddChannel());
//				win.center();
//				win.setHeight("60%");
//				win.setWidth("70%");
				getUI().addWindow(new AddChannel());
				
			}
		});
		
	}

}
