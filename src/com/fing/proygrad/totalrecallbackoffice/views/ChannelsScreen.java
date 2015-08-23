package com.fing.proygrad.totalrecallbackoffice.views;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import com.fing.proygrad.totalrecallbackoffice.storage.Channels;
import com.fing.proygrad.totalrecallbackoffice.util.Consts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class ChannelsScreen extends BaseScreenLayout{

	
	private static final long serialVersionUID = 3084673053358943678L;
	
	
	private List<JSONObject> channels;
	private HorizontalLayout container;
	private VerticalLayout content;
	private Table channelsTable;
	private Panel details;
	private Button addChannel;
	
	
	public ChannelsScreen() {
		super("channels.screen.label.title");
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

		channelsTable.addContainerProperty("Name", String.class, null);
		channelsTable.addContainerProperty("Actions",  HorizontalLayout.class, null);
		

		channelsTable.setVisibleColumns(new Object[] { "Name", "Actions" });
		channelsTable.setColumnHeaders(new String[] { config.getMessage("channels.screen.table.channelstable.columheader.name"), 
													config.getMessage("channels.screen.table.channelstable.columheader.actions") });
		
		channelsTable.addItemClickListener(new ItemClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				showInfo((Integer)event.getItemId());
				
			}
		});
		
		populateTable();
	}
	

	
	private void populateTable(){
		
		channels = generateChannels();
		
		int i = 0;
		for(JSONObject elem : channels){
			channelsTable.addItem(new Object[]{elem.get("type_name"), createActionsItem()} , i);
			i++;
		}
		
		
		if(channelsTable.size() > 10){
			channelsTable.setPageLength(10);
		}else{
			channelsTable.setPageLength(channelsTable.size());
		}
	}
	
	private List<JSONObject> generateChannels(){
		
		List<String> chans = new Channels().getUserChannels("123");
		
		List<JSONObject> aux = new ArrayList<JSONObject>();
		for(String chan : chans){
			aux.add(new JSONObject(chan));
		}
		
		List<JSONObject> toRet = new ArrayList<JSONObject>();
		for(JSONObject obj : aux){
			toRet.add(createFullJSON(obj, aux));
		}
		
		return toRet;
	}
	
	private JSONObject createFullJSON(JSONObject json, List<JSONObject> vals){
		JSONObject toRet = new JSONObject();
		
		String val;
		String key;
		JSONObject complex = null;
		Iterator<String> it = json.keys();
		while(it.hasNext()){
			key = it.next();
			val = json.getString(key);
			
			
			
			if(!key.equals("type_name") && isComplexType(val)){
				complex = getJsonWithTypeName(val, vals);
				toRet.put(key, createFullJSON(complex, vals));
			}else{
				toRet.put(key, val);
			}
		}
		
		return toRet;
		
	}
	
	private JSONObject getJsonWithTypeName(String val, List<JSONObject> vals) {
		

		for(JSONObject aux : vals){
			if(aux.getString("type_name").equals(val)){
				return aux;
			}
			
		}
		
		return null;
	}

	private boolean isComplexType(String type){
		return !(new Consts().getTypes().contains(type)) ;
	}
	
	private HorizontalLayout createActionsItem(){
		Button edit;
		Button delete;
		
		edit = new Button(config.getMessage("channels.screen.button.edit.caption"));
		edit.setStyleName(Reindeer.BUTTON_LINK);
		delete = new Button(config.getMessage("channels.screen.button.delete.caption"));
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
				
			details = new Panel(config.getMessage("channels.screen.panel.details.caption"));
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
				
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonn.toString()).getAsJsonObject();       
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
		
		return prettyJson;

	}
	
	private void createButton(){
		
		addChannel = new Button(config.getMessage("channels.screen.button.addchannel.caption"));
		addChannel.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				Window w = new AddChannelWindow();
				w.addCloseListener(new Window.CloseListener() {
					
					@Override
					public void windowClose(CloseEvent e) {
						initComponents();
					}
				});
				getUI().addWindow(w);
				
			}
		});
		
	}

}
