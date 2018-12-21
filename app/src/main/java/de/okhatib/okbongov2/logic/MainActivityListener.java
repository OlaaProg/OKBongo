package de.okhatib.okbongov2.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.okhatib.okbongov2.R;
import de.okhatib.okbongov2.gui.AboutUsActivity;
import de.okhatib.okbongov2.gui.BongoNoteCrudActivity;
import de.okhatib.okbongov2.gui.MainActivity;
import de.okhatib.okbongov2.gui.listview.ListViewAdapter;
import de.okhatib.okbongov2.testdata.TestData;

public class MainActivityListener extends ABaseListener implements AdapterView.OnItemClickListener,
																   MenuItem.OnMenuItemClickListener {
	
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
	
	/**
	 *
	 */
	private ListView lvBongoNotes;
	
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * Standardkonstruktor zum direkten setzen
	 * der Arbeitsreferenz auf die Aktuelle Activity diese koennen sein:
	 * {@link MainActivity}
	 * <ul>
	 * <li>{@link MainActivity}</li>
	 * <li>{@link BongoNoteCrudActivity}</li>
	 * <li>{@link AboutUsActivity}</li>
	 * </ul>
	 *
	 * @param currentActivity :{@link AppCompatActivity}
	 */
	public MainActivityListener(AppCompatActivity currentActivity) {
		super(currentActivity);
		
		//1. Widgets generieren
		generateWidgetReferences();
		
		//2. ListView updaten
		updateListView();
	}
	//endregion
	
	//region 3. ListView Click Handling
	
	
	/**
	 * Springt jedes mal an wenn ein ListViewItem innerhalb der {@link android.widget.ListView} geklickt wird.

	 */
	@Override
	public void onItemClick(AdapterView<?> parentListView, View clickedListViewItem, int index, long lngId) {
		
		//Explizites Intent generieren
		Intent intentStartBongoNoteCrudActivity = new Intent(this.currentActivity, BongoNoteCrudActivity.class);
		
		//Name des Extras aus der res/values/strings.xml auslesen, muss vorher definiert werden
		String strExtraNameId = this.currentActivity.getString(R.string.strExtraNameId);
		
		//Id des Objektes von long zu int EXPLICIT CASTEN!!!
		int iId = (int) lngId;
		
		//Id mit namen an Intent geben.
		intentStartBongoNoteCrudActivity.putExtra(strExtraNameId, iId);
		
		
		//Activity starten
		this.currentActivity.startActivity(intentStartBongoNoteCrudActivity);
	}
	
	//endregion
	
	/**
	 * Zum auswerten welches MenuItem geklickt wurde.
	 *
	 * @param mnuItem : {@link MenuItem} : Geklicktes menu item
	 *
	 * @return true : Richtiges Klickhandling
	 */
	@Override
	public boolean onMenuItemClick(MenuItem mnuItem) {
		Intent intentActivityToStart = null;
		
		switch (mnuItem.getItemId()) {
			case R.id.mnuItemAdd:
				/*
				 *  Explizites Intent
				 *  Das Intent ist ein Nachrichtenobjekt von Android
				 *  welches die Information kapselt/entahelt welche Activity welche startet.
				 *  1. Parameter Ausgangsactivity
				 *  2. Parameter: Zielactivity sprich die Activity die gestartet werden soll
				 */
				intentActivityToStart = new Intent(this.currentActivity, BongoNoteCrudActivity.class);
				
				
				break;
			case R.id.mnuItemInfo:
				//AboutUs Activity
				intentActivityToStart = new Intent(this.currentActivity, AboutUsActivity.class);
				break;
		}
		
		if (intentActivityToStart != null) {
			//Activity Starten
			this.currentActivity.startActivity(intentActivityToStart);
		}
		return true;
	}
	
	
	//region Hilfsmethoden und Funktionen
	
	
	@Override
	public void generateWidgetReferences() {
		this.lvBongoNotes = this.currentActivity.findViewById(R.id.lvBongoNotes);
	}
	
	/**
	 * Updated die {@link ListView}
	 * mit den neusten Daten
	 */
	public void updateListView() {
		//TODO momentan Testdatan spaeter durch DB-Aufruf ersetzen
		ListViewAdapter listViewAdapter = new ListViewAdapter(this.currentActivity,
				TestData.getTestBongoNoteList());
		
		//Daten akutalisieren
		this.lvBongoNotes.setAdapter(listViewAdapter);
	}
	//endregion
}

