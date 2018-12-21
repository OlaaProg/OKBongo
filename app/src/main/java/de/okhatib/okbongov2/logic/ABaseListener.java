package de.okhatib.okbongov2.logic;

import android.support.v7.app.AppCompatActivity;

public abstract class ABaseListener {
	
	//region 0. Konstanten
	
	//endregion
	
	//region 1. Decl. and Init Attribute
	
	/**
	 * Arbeitsreferenz auf die aktuelle Activity
	 * Diese koennen sein:
	 * <ul>
	
	 * </ul>
	 */
	protected AppCompatActivity currentActivity;
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * Standardkonstruktor zum direkten setzen
	 * der Arbeitsreferenz auf die Aktuelle Activity diese koennen sein:
	 
	 * @param currentActivity :{@link AppCompatActivity}
	 */
	public ABaseListener(AppCompatActivity currentActivity) {
		this.currentActivity = currentActivity;
	}
	
	//endregion
	
	//region abstrakte Methoden / Funktionen
	public abstract void generateWidgetReferences();
	//endregion
}

