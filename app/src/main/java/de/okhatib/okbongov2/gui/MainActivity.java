package de.okhatib.okbongov2.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import de.okhatib.okbongov2.R;
import de.okhatib.okbongov2.logic.MainActivityListener;

public class MainActivity extends AppCompatActivity {
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
	
	/**
	
	 */
	private ListView lvBongoNotes;
	
	/**
	 * Handelt alle Klickevents
	 */
	private MainActivityListener mainActivityListener;
	//endregion
	
	//region 2. Lebenszyklus
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//1. Layout gesetzt
		setContentView(R.layout.main_activity_layout);
		
		//2. Widgets generiert
		this.lvBongoNotes = this.findViewById(R.id.lvBongoNotes);
		
		//3. Listener generieren
		this.mainActivityListener = new MainActivityListener(this);
		
		//4. Listener zuweisen
		this.lvBongoNotes.setOnItemClickListener(this.mainActivityListener);
	}
	//endregion
	
	//region 3. Menu
	
	
	/**
	 * Baut das Menu auf
	 *
	 * @param menu : {@link Menu} : Menu was generiert werden soll
	 *
	 * @return true zum anzeigen des Menus false um es nicht anzuzeigen
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main_activity_menu, menu);
		return true;
	}
	
	/**
	 * Zum auswerten welches MenuItem geklickt wurde.
	 * Dies geschiet im {@link MainActivityListener#onMenuItemClick(MenuItem)}
	 *
	 * @param mnuItem : {@link MenuItem} : Geklicktes menu item
	 *
	 * @return true : Richtiges Klickhandling
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem mnuItem) {
		return this.mainActivityListener.onMenuItemClick(mnuItem);
	}
	
	//endregion
}
