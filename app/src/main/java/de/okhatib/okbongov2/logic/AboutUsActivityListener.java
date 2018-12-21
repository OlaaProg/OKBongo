package de.okhatib.okbongov2.logic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import de.okhatib.okbongov2.R;
import de.okhatib.okbongov2.gui.AboutUsActivity;
import de.okhatib.okbongov2.gui.BongoNoteCrudActivity;
import de.okhatib.okbongov2.gui.MainActivity;

public class AboutUsActivityListener extends ABaseListener implements View.OnClickListener {
	
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
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
	public AboutUsActivityListener(AppCompatActivity currentActivity) {
		super(currentActivity);
		
		generateWidgetReferences();
	}
	//endregion
	
	//region 3. Klickhandling
	/**
	 * Wird aufgerufen wenn ein Widget
	 * welches diesen Listener in der
	 * {@link AboutUsActivity} zugewiesen
	 * bekommen hat, geklickt wird
	 *
	 * @param v : {@link View} : Geklickte Widget
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btnEmail:
				//Intent intentEmail =new Intent(Intent.ACTION_MAIL)
				Toast.makeText(this.currentActivity,
						"Email coming soon", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnCallHotline:
				//1. Nummer aus der res/values/strings.xml auslesen, muss vordefiniert werden.
				String strHotlineNumber = this.currentActivity.getString(R.string.strHotlineNumber);
				
				//2. Implizites Intent generieren
				Intent intentCallDialog = new Intent(Intent.ACTION_DIAL, Uri.parse(strHotlineNumber));
				
				//3. Starten des Dialogs
				this.currentActivity.startActivity(intentCallDialog);
				break;
			case R.id.btnVisitWebside:
				Toast.makeText(this.currentActivity,
						"Webside coming soon", Toast.LENGTH_SHORT).show();
				break;
		}
	}
	
	//region Hilfsmethoden und funktionen
	@Override
	public void generateWidgetReferences() {
		//TODO spaeter
	}
	//endregion
}
