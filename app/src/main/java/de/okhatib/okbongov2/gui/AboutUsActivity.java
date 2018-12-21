package de.okhatib.okbongov2.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import de.okhatib.okbongov2.R;
import de.okhatib.okbongov2.logic.AboutUsActivityListener;

public class AboutUsActivity extends AppCompatActivity {
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
	private ImageView imgvCompanyLogo;
	
	private WebView wvCompanyDescription;
	
	private Button btnEmail;
	private Button btnCallHotline;
	private Button btnVisitWebside;
	
	private AboutUsActivityListener aboutUsActivityListener;
	//endregion
	
	//region 2. Lebenszyklus
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//1. Layout setzen
		this.setContentView(R.layout.about_us_activity_layout);
		
		//2. Widgets generieren
		this.imgvCompanyLogo = this.findViewById(R.id.imgvCompanyLogo);
		
		this.wvCompanyDescription = this.findViewById(R.id.wvCompanyDescription);
		
		this.btnEmail = this.findViewById(R.id.btnEmail);
		this.btnCallHotline = this.findViewById(R.id.btnCallHotline);
		this.btnVisitWebside = this.findViewById(R.id.btnVisitWebside);
		//3. Daten setzen
		String strCompanyDescriptionAsHtml =this.getString(R.string.strCompanyDescriptionAsHtml);
		String strHtmlContentType="text/html";
		String strHtmlEncoding="UTF-8";
		this.wvCompanyDescription.loadData(strCompanyDescriptionAsHtml,strHtmlContentType,strHtmlEncoding);
		//4. Listener generieren
		this.aboutUsActivityListener = new AboutUsActivityListener(this);
		
		//5. Listener zuweisen
		this.btnEmail.setOnClickListener(this.aboutUsActivityListener);
		this.btnCallHotline.setOnClickListener(this.aboutUsActivityListener);
		this.btnVisitWebside.setOnClickListener(this.aboutUsActivityListener);
		
	}
	//enregion
	
	
}

